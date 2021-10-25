// Application.
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

// Communication.
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

// Models and services.
import { Publication } from '../models/publication.model';

@Injectable({
  providedIn: 'root'
})
export class PublicationService {
  /**
   * The list of publications.
   */
  private publications: Publication[];

  /**
   * The observable list of publications.
   */
  private publications$: BehaviorSubject<Publication[]>;

  /**
   * Constructs the publication service.
   *
   * @param http The HTTP client.
   */
  constructor(private http: HttpClient) {
    this.publications = [];
    this.publications$ = new BehaviorSubject(this.publications);
    this.loadAllPublications();
  }

  /**
   * Returns the observable list of publications.
   */
  getPublications(): Observable<Publication[]> {
      return this.publications$.asObservable();
  }

  /**
   * Returns an observable publication.
   *
   * @param id The publication identifier.
   */
  getPublication(id: number): Observable<Publication | undefined> {
    return this.publications$
      .pipe(
        map((publications: Publication[]) => publications.find(
          (publication: Publication) => publication.id === id
        ))
      );
  }

  /**
   * Loads all publications.
   */
  loadAllPublications(): void {
    this.http
      .get<Publication[]>(`${environment.serverURL}/publications`)
      .subscribe(
        data => {
          this.publications = data;
          this.publications$.next(this.publications);
        },
        () => console.log('Could not load all the publications.')
      );
  }

  /**
   * Loads a publication.
   *
   * @param id The publication identifier.
   */
  loadPublication(id: number): void {
    this.http
      .get<Publication>(`${environment.serverURL}/publications/${id}`)
      .subscribe(
        data => {
          // Update the publication.
          let newItem = true;
          this.publications.forEach((item, index) => {
            if (item.id === id) {
              this.publications[index] = data;
              newItem = false;
            }
          });

          // Add the publication.
          if (newItem) {
            this.publications.push(data);
          }

          // Notify the observers.
          this.publications$.next(this.publications);
        },
        () => console.log('Could not load the publication.')
      );
  }

  /**
   * Creates a publication.
   *
   * @param publication The publication to create.
   * @param postRoute the route where post it
   */
  createPublication<T extends Publication>(publication: T, postRoute: string): Observable<T> {
    console.log('creation de ', JSON.stringify(publication), 'vers ', `${environment.serverURL}${postRoute}` );
    return this.http
      .post<T>(
        `${environment.serverURL}${postRoute}`,
        JSON.stringify(publication),
        environment.serverOptions
      )
      .pipe(
        map((createdPublication: T) => {
          // Add the publication.
          this.publications.push(createdPublication);

          // Notify the observers.
          this.publications$.next(this.publications);

          return createdPublication;
        }),
        catchError(() =>
          throwError('Could not create the publication.')
        )
      );
  }

  /**
   * Updates a publication.
   *
   * @param publication The publication to update.
   */
  updatePublication(publication: Publication): Observable<Publication> {
    return this.http
      .put<Publication>(
        `${environment.serverURL}/publications/${publication.id}`,
        JSON.stringify(publication),
        environment.serverOptions
      )
      .pipe(
        map((updatedPublication: Publication) => {
          // Update the publication.
          this.publications.forEach((item, index) => {
            if (item.id === updatedPublication.id) {
              this.publications[index] = updatedPublication;
            }
          });

          // Notify the observers.
          this.publications$.next(this.publications);

          return updatedPublication;
        }),
        catchError(() =>
          throwError('Could not update the publication.')
        )
      );
  }

  /**
   * Deletes a publication.
   *
   * @param id The publication identifier.
   */
  deletePublication(id: number): Observable<void> {
    return this.http
      .delete(`${environment.serverURL}/publications/${id}`)
      .pipe(
        map(() => {
          // Delete the publication.
          this.publications.forEach((item, index) => {
            if (item.id === id) {
              this.publications.splice(index, 1);
            }
          });

          // Notify the observers.
          this.publications$.next(this.publications);
        }),
        catchError(() =>
          throwError('Could not delete the publication.')
        )
      );
  }
}
