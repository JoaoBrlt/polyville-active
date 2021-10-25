// Application.
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';

// Communication.
import { HttpClient, } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';

// Models and services.
import { Place } from '../models/place.model';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {
  /**
   * The list of places.
   */
  private places: Place[];

  /**
   * The observable list of places.
   */
  private places$: BehaviorSubject<Place[]>;

  /**
   * The connected place.
   */
  private connectedPlaceId: number;


  /**
   * Constructs a place service.
   *
   * @param http The HTTP client.
   * @param router The application router.
   */
  constructor(
    private http: HttpClient,
    private router: Router,
  ) {
    this.places = [];
    this.places$ = new BehaviorSubject(this.places);
    this.connectedPlaceId = -1;
    this.loadAllPlaces();
  }

  /**
   * Returns the observable list of places.
   */
  getPlaces(): Observable<Place[]> {
    return this.places$.asObservable();
  }


  /**
   * Loads all places.
   */
  loadAllPlaces(): void {
    this.http
      .get<Place[]>(`${environment.serverURL}/establishments`)
      .subscribe(
        places => {
          this.places = places;
          this.places$.next(this.places);
        },
        () => console.error('Could not load all the places.')
      );
  }
}
