// Application.
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';

// Communication.
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

// Models and services.
import { Plugin } from '../models/plugin.model';

const BASE_PATH = '/sbp/admin';

@Injectable({
  providedIn: 'root'
})
export class PluginService {
  /**
   * The list of plugins.
   */
  private plugins: Plugin[];

  /**
   * The observable list of plugins.
   */
  private plugins$: BehaviorSubject<Plugin[]>;

  /**
   * Constructs the plugin service.
   *
   * @param http The HTTP client.
   * @param router The application router.
   */
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.plugins = [];
    this.plugins$ = new BehaviorSubject(this.plugins);

    // Load the plugins.
    this.loadAllPlugins();
  }

  /**
   * Returns the observable list of plugins.
   */
  getPlugins(): Observable<Plugin[]> {
    return this.plugins$.asObservable();
  }

  /**
   * Returns an observable plugin.
   *
   * @param id The plugin identifier.
   */
  getPlugin(id: string): Observable<Plugin | undefined> {
    return this.getPlugins()
      .pipe(
        map((plugins) => plugins.find(
          (plugin) => plugin.pluginId === id)
        )
      );
  }

  /**
   * Loads all plugins.
   */
  loadAllPlugins(): void {
    this.http
      .get<Plugin[]>(`${environment.serverURL}${BASE_PATH}/list`)
      .subscribe(
        data => {
          this.plugins = data;
          this.plugins$.next(this.plugins);
        },
        () => console.error('Could not load all the plugins.')
      );
  }

  /**
   * Starts a plugin.
   *
   * @param pluginId The plugin to start.
   */
  startPlugin(pluginId: string): Observable<void> {
    return this.http
      .post(
        `${environment.serverURL}${BASE_PATH}/start/${pluginId}`,
        null,
        {
          // eslint-disable-next-line
          headers: new HttpHeaders({ 'Content-Type': '0' }),
          responseType: 'text'
        }
      )
      .pipe(
        map(() => this.loadAllPlugins()),
        catchError(() =>
          throwError('Could not stop the plugin.')
        )
      );
  }

  /**
   * Stops a plugin.
   *
   * @param pluginId The plugin to stop.
   */
  stopPlugin(pluginId: string): Observable<void> {
    return this.http
      .post(
        `${environment.serverURL}${BASE_PATH}/stop/${pluginId}`,
        null,
        {
          // eslint-disable-next-line
          headers: new HttpHeaders({ 'Content-Type': '0' }),
          responseType: 'text'
        }
      )
      .pipe(
        map(() => this.loadAllPlugins()),
        catchError(() =>
          throwError('Could not stop the plugin.')
        )
      );
  }

  /**
   * Resolves a new plugin.
   */
  resolvePlugin(pluginId: string): Observable<void> {
    return this.http
      .post(
        `${environment.serverURL}${BASE_PATH}/resolve/${pluginId}`,
        null,
        {
          // eslint-disable-next-line
          headers: new HttpHeaders({ 'Content-Type': '0' }),
          responseType: 'text'
        }
      )
      .pipe(
        map(() => this.loadAllPlugins()),
        catchError(() =>
          throwError('Could not resolve the new plugin.')
        )
      );
  }

  /**
   * Resolves new plugins.
   */
  resolveAllPlugins(): Observable<void> {
    return this.http
      .post(
        `${environment.serverURL}${BASE_PATH}/resolve-all`,
        null,
        {
          // eslint-disable-next-line
          headers: new HttpHeaders({ 'Content-Type': '0' }),
          responseType: 'text'
        }
      )
      .pipe(
        map(() => this.loadAllPlugins()),
        catchError(() =>
          throwError('Could not resolve all new plugins.')
        )
      );
  }

  /**
   * Reloads a plugin.
   */
  reloadPlugin(pluginId: string): Observable<void> {
    return this.http
      .post(
        `${environment.serverURL}${BASE_PATH}/reload/${pluginId}`,
        null,
        {
          // eslint-disable-next-line
          headers: new HttpHeaders({ 'Content-Type': '0' }),
          responseType: 'text'
        }
      )
      .pipe(
        map(() => this.loadAllPlugins()),
        catchError(() =>
          throwError('Could not reload all the plugins.')
        )
      );
  }

  /**
   * Reloads all the plugins.
   */
  reloadAllPlugins(): Observable<void> {
    return this.http
      .post(
        `${environment.serverURL}${BASE_PATH}/reload-all`,
        null,
        {
          // eslint-disable-next-line
          headers: new HttpHeaders({ 'Content-Type': '0' }),
          responseType: 'text'
        }
      )
      .pipe(
        map(() => this.loadAllPlugins()),
        catchError(() =>
          throwError('Could not reload all the plugins.')
        )
      );
  }
}
