// Application.
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';

// Communication.
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

// Models and services.
import { Parking } from '../models/parking.model';

@Injectable({
  providedIn: 'root'
})
export class ParkingService {
  /**
   * The list of parking.
   */
  private parkings: Parking[];

  /**
   * The observable list of parking.
   */
  private parkings$: BehaviorSubject<Parking[]>;

  /**
   * The connected shop.
   */
  private connectedParkingId: number;

  /**
   * Constructs the parking service.
   *
   * @param http The HTTP client.
   * @param router The application router.
   */
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.parkings = [];
    this.parkings$ = new BehaviorSubject(this.parkings);
    this.connectedParkingId = -1;
    this.loadAllParkings();
  }

  /**
   * Returns the observable list of parking.
   */
  getParkings(): Observable<Parking[]> {
    return this.parkings$.asObservable();
  }

  /**
   * Returns an observable parking.
   *
   * @param id The parking identifier.
   */
  getParking(id: number): Observable<Parking | undefined> {
    return this.getParkings()
      .pipe(
        map((parkings) => parkings.find(
          (parking) => parking.id === id)
        )
      );
  }

  /**
   * Loads all parking.
   */
  loadAllParkings(): void {
    this.http
      .get<Parking[]>(`${environment.serverURL}/parking`)
      .subscribe(
        data => {
          this.parkings = data;
          this.parkings$.next(this.parkings);
        },
        () => console.error('Could not load all the parking.')
      );
  }

  /**
   * Loads a parking.
   *
   * @param id The parking identifier.
   */
  loadParking(id: number): void {
    this.http
      .get<Parking>(`${environment.serverURL}/parking/${id}`)
      .subscribe(
        data => {
          // Update the parking.
          let newItem = true;
          this.parkings.forEach((item, index) => {
            if (item.id === id) {
              this.parkings[index] = data;
              newItem = false;
            }
          });

          // Add the parking.
          if (newItem) {
            this.parkings.push(data);
          }

          // Notify the observers.
          this.parkings$.next(this.parkings);
        },
        () => console.error('Could not load the parking.')
      );
  }

  /**
   * Creates a parking.
   *
   * @param parking The parking to create.
   */
  createParking(parking: Parking): Observable<Parking> {
    const strMapToObj = (strMap: any) =>{
      const obj = Object.create(null);
      for (const [k,v] of strMap) {
        obj[k] = v;
      }
      return obj;
    };
    parking.price = strMapToObj(parking.price);
    return this.http
      .post<Parking>(
        `${environment.serverURL}/parking`,
        JSON.stringify(parking),
        environment.serverOptions
      )
      .pipe(
        map((createdParking: Parking) => {
          // Add the parking.
          this.parkings.push(createdParking);

          // Notify the observers.
          this.parkings$.next(this.parkings);

          return createdParking;
        }),
        catchError(() =>
          throwError('Could not create the parking.')
        )
      );
  }

  /**
   * Updates a parking.
   *
   * @param parking The parking to update.
   */
  updateParking(parking: Parking): Observable<Parking> {
    return this.http
      .put<Parking>(
        `${environment.serverURL}/parking/${parking.id}`,
        JSON.stringify(parking),
        environment.serverOptions
      )
      .pipe(
        map((createdParking: Parking) => {
          // Update the parking.
          this.parkings.forEach((item, index) => {
            if (item.id === createdParking.id) {
              this.parkings[index] = createdParking;
            }
          });

          // Notify the observers.
          this.parkings$.next(this.parkings);

          return createdParking;
        }),
        catchError(() =>
          throwError('Could not update the parking.')
        )
      );
  }

  /**
   * Deletes a parking.
   *
   * @param id The parking identifier.
   */
  deleteParking(id: number): Observable<void> {
    return this.http
      .delete(`${environment.serverURL}/parking/${id}`)
      .pipe(
        map(() => {
          // Delete the parking.
          this.parkings.forEach((item, index) => {
            if (item.id === id) {
              this.parkings.splice(index, 1);
            }
          });

          // Notify the observers.
          this.parkings$.next(this.parkings);
        }),
        catchError(() =>
          throwError('Could not delete the parking.')
        )
      );
  }

  /**
   * Returns the connected parking identifier.
   */
  getConnectedParkingId(): number {
    // Redirect to login page.
    if (this.connectedParkingId === -1) {
      this.router.navigate(['/log-parking']);
    }
    return this.connectedParkingId;
  }

  /**
   * Connects a parking.
   *
   * @param id The parking identifier.
   */
  connectParking(id: number): Parking | undefined {
    const connectedParking = this.parkings.find((parking) => parking.id === id);
    if (connectedParking) {
      this.connectedParkingId = id;
    }
    return connectedParking;
  }

  /**
   * Disconnects a parking.
   */
  disconnectParking(): void {
    this.connectedParkingId = -1;
  }
}
