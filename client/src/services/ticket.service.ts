// Application.
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';

// Communication.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Ticket } from '../models/ticket.model';

// Models and services.


@Injectable({
  providedIn: 'root'
})
export class TicketService {
  /**
   * The list of tickets.
   */
  private ticket!: Ticket;

  /**
   * The observable list of tickets.
   */
  private ticket$: BehaviorSubject<Ticket>;

  /**
   * The connected ticket identifier.
   */
  private connectedTicketId: number;

  /**
   * Constructs the ticket service.
   *
   * @param http The HTTP client.
   * @param router The application router.
   */
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.ticket$ = new BehaviorSubject(this.ticket);
    this.connectedTicketId = -1;
  }

  /**
   * Returns an observable ticket.
   *
   * @param id The ticket identifier.
   */
  getTicket(id: string): Observable<Ticket | undefined> {
    return this.http
      .get<Ticket>(`${environment.serverURL}/innovation/inside-parking/tickets/${id}`)
      .pipe(map(
        data => {
          // Update the ticket.
          this.ticket = data;

          // Notify the observers.
          this.ticket$.next(this.ticket);
          return data;
        }
      ));
  }

  /**
   * Loads an ticket.
   *
   * @param id The ticket identifier.
   */
  loadTicket(id: number): void {
    this.http
      .get<Ticket>(`${environment.serverURL}/innovation/inside-parking/tickets/${id}`)
      .pipe(map(
        data => {
          // Update the ticket.
          this.ticket = data;

          // Notify the observers.
          this.ticket$.next(this.ticket);
          return data;
        }
      ));
  }

  /**
   * Generates an ticket.
   */
  generateTicket(): Observable<Ticket> {
    return this.http
      .post<Ticket>(
        `${environment.serverURL}/innovation/inside-parking/generate`,
        null,
        {
          // eslint-disable-next-line
          headers: new HttpHeaders({ 'Content-Type': '0' })
        }
      )
      .pipe(
        map((createdTicket: Ticket) => {
          // Add the event.
          this.ticket = createdTicket;

          // Notify the observers.
          this.ticket$.next(this.ticket);

          return createdTicket;
        }),
        catchError(() =>
          throwError('Could not generate the ticket.')
        )
      );
  }

  /**
   * Validates an ticket.
   *
   * @param ticketId The ticket to validate.
   */
  validateTicket(ticketId: string): Observable<Ticket> {
    return this.http
      .put<Ticket>(
        `${environment.serverURL}/innovation/inside-parking/validate/${ticketId}`,
        environment.serverOptions
      )
      .pipe(
        map((updatedTicket: Ticket) => {
          // Update the ticket.
          this.ticket = updatedTicket;

          // Notify the observers.
          this.ticket$.next(this.ticket);

          return updatedTicket;
        }),
        catchError(() =>
          throwError('Could not update the ticket.')
        )
      );
  }

  /**
   * Saves the parking space of a user.
   *
   * @param ticketId The ticket to update and put the parking lot.
   */
  saveParkingSpace(ticketId: string, parkingSpace: string): Observable<Ticket> {
    //TODO
    console.log('put parking space');
    console.log(parkingSpace);
    console.log(ticketId);
    return this.http
      .put<Ticket>(
        `${environment.serverURL}/innovation/tickets/${ticketId}`,
        {placeInfo: parkingSpace},
        environment.serverOptions
      )
      .pipe(
        map((updatedTicket: Ticket) => {
          // Update the ticket.
          this.ticket = updatedTicket;

          // Notify the observers.
          this.ticket$.next(this.ticket);

          return updatedTicket;
        }),
        catchError(() =>
          throwError('Could not update the ticket.')
        )
      );
  }

  /**
   * Deletes an ticket.
   *
   * @param id The ticket identifier.
   */
  /*
  deleteTicket(id: number): void {
    this.http
      .delete(`${environment.serverURL}/tickets/${id}`)
      .pipe(
        map(() => {
          // Delete the ticket.
          this.tickets.forEach((item, index) => {
            if (item.id === id) {
              this.tickets.splice(index, 1);
            }
          });

          // Notify the observers.
          this.tickets$.next(this.tickets);
        }),
        catchError(() =>
          throwError('Could not delete the ticket.')
        )
      ).subscribe();
  }
   */

  /**
   * Returns the connected ticket identifier.
   */
  getConnectedTicketId(): number {
    // Redirect to login page.
    if (this.connectedTicketId === -1) {
      this.router.navigate(['/log-event']);
    }
    return this.connectedTicketId;
  }

  /**
   * Disconnects the ticket.
   */
  disconnectTicket(): void {
    this.connectedTicketId = -1;
  }

  pay(ticketId: string): Observable<Ticket> {
    return this.http.put<Ticket>(
      `${environment.serverURL}/innovation/inside-parking/payment/${ticketId}`,
      environment.serverOptions
    );
  }

  registerOutsideTicket(carNumber: string, payment: any): Observable<Ticket> {
    return this.http.post<Ticket>(
      `${environment.serverURL}/innovation/outside-parking/register/${carNumber}`,
      JSON.stringify(payment),
      environment.serverOptions
    );
  }

  addtionnalPayment(ticketId: string, payment: any): Observable<Ticket> {
    return this.http.put<Ticket>(
      `${environment.serverURL}/innovation/outside-parking/additional-payment/${ticketId}`,
      JSON.stringify(payment),
      environment.serverOptions
    );
  }
}
