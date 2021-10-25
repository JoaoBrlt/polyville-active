// Application.
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';

// Communication.
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

// Models and services.
import { CityEvent } from '../models/event.model';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  /**
   * The list of events.
   */
  private events: CityEvent[];

  /**
   * The observable list of events.
   */
  private events$: BehaviorSubject<CityEvent[]>;

  /**
   * The connected event identifier.
   */
  private connectedEventId: number;

  /**
   * Constructs the event service.
   *
   * @param http The HTTP client.
   * @param router The application router.
   */
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.events = [];
    this.events$ = new BehaviorSubject(this.events);
    this.connectedEventId = -1;
    this.loadAllEvents();
  }

  /**
   * Returns the observable list of events.
   */
  getEvents(): Observable<CityEvent[]> {
    return this.events$.asObservable();
  }

  /**
   * Returns an observable event.
   *
   * @param id The event identifier.
   */
  getEvent(id: number): Observable<CityEvent | undefined> {
    return this.getEvents()
      .pipe(
        map((events) => events.find(
          (event) => event.id === id)
        )
      );
  }

  /**
   * Loads all events.
   */
  loadAllEvents(): void {
    this.http
      .get<CityEvent[]>(`${environment.serverURL}/events`)
      .subscribe(
        data => {
          this.events = data;
          this.events$.next(this.events);
        },
        () => console.error('Could not load all the events.')
      );
  }

  /**
   * Loads an event.
   *
   * @param id The event identifier.
   */
  loadEvent(id: number): void {
    this.http
      .get<CityEvent>(`${environment.serverURL}/events/${id}`)
      .subscribe(
        data => {
          // Update the event.
          let newItem = true;
          this.events.forEach((item, index) => {
            if (item.id === id) {
              this.events[index] = data;
              newItem = false;
            }
          });

          // Add the event.
          if (newItem) {
            this.events.push(data);
          }

          // Notify the observers.
          this.events$.next(this.events);
        },
        () => console.error('Could not load the event.')
      );
  }

  /**
   * Creates an event.
   *
   * @param event The event to create.
   */
  createEvent(event: CityEvent): Observable<CityEvent> {
    return this.http
      .post<CityEvent>(
        `${environment.serverURL}/events`,
        JSON.stringify(event),
        environment.serverOptions
      )
      .pipe(
        map((createdEvent: CityEvent) => {
          // Add the event.
          this.events.push(createdEvent);

          // Notify the observers.
          this.events$.next(this.events);

          return createdEvent;
        }),
        catchError(() =>
          throwError('Could not create the event.')
        )
      );
  }

  /**
   * Updates an event.
   *
   * @param event The event to update.
   */
  updateEvent(event: CityEvent): Observable<CityEvent> {
    return this.http
      .put<CityEvent>(
        `${environment.serverURL}/events/${event.id}`,
        JSON.stringify(event),
        environment.serverOptions
      )
      .pipe(
        map((updatedEvent: CityEvent) => {
          // Update the event.
          this.events.forEach((item, index) => {
            if (item.id === updatedEvent.id) {
              this.events[index] = updatedEvent;
            }
          });

          // Notify the observers.
          this.events$.next(this.events);

          return updatedEvent;
        }),
        catchError(() =>
          throwError('Could not update the event.')
        )
      );
  }

  /**
   * Deletes an event.
   *
   * @param id The event identifier.
   */
  deleteEvent(id: number): void {
    this.http
      .delete(`${environment.serverURL}/events/${id}`)
      .pipe(
        map(() => {
          // Delete the event.
          this.events.forEach((item, index) => {
            if (item.id === id) {
              this.events.splice(index, 1);
            }
          });

          // Notify the observers.
          this.events$.next(this.events);
        }),
        catchError(() =>
          throwError('Could not delete the event.')
        )
    ).subscribe();
  }

  /**
   * Returns the connected event identifier.
   */
  getConnectedEventId(): number {
    // Redirect to login page.
    if (this.connectedEventId === -1) {
      this.router.navigate(['/log-event']);
    }
    return this.connectedEventId;
  }

  /**
   * Connects an event.
   *
   * @param id The event identifier.
   */
  connectEvent(id: number): CityEvent | undefined {
    const connectedEvent = this.events.find((event) => event.id === id);
    if (connectedEvent) {
      this.connectedEventId = id;
    }
    return connectedEvent;
  }

  /**
   * Disconnects the event.
   */
  disconnectEvent(): void {
    this.connectedEventId = -1;
  }
}
