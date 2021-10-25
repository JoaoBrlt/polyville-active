// Application.
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../environments/environment';

// Communication.
import { HttpClient, } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, filter, map } from 'rxjs/operators';

// Models and services.
import { Promotion, Shop } from '../models/shop.model';
import { PublicationService } from './publication.service';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  /**
   * The list of shops.
   */
  private shops: Shop[];

  /**
   * The observable list of shops.
   */
  private shops$: BehaviorSubject<Shop[]>;

  /**
   * The connected shop.
   */
  private connectedShopId: number;

  /**
   * The list of discounts.
   */
  private promotions: Promotion[];

  /**
   * The observable list of discounts.
   */
  private promotions$: BehaviorSubject<Promotion[]>;

  /**
   * Constructs a shop service.
   *
   * @param http The HTTP client.
   * @param router The application router.
   * @param publicationService The publication service
   */
  constructor(
    private http: HttpClient,
    private router: Router,
    private publicationService: PublicationService
  ) {
    this.shops = [];
    this.shops$ = new BehaviorSubject(this.shops);
    this.connectedShopId = -1;
    this.loadAllShops();

    this.promotions = [];
    this.promotions$ = new BehaviorSubject(this.promotions);
    this.loadAllPromotions();
  }

  /**
   * Returns the observable list of shops.
   */
  getShops(): Observable<Shop[]> {
    return this.shops$.asObservable();
  }

  /**
   * Returns an observable shop.
   *
   * @param id The shop identifier.
   */
  getShop(id: number): Observable<Shop | undefined> {
    return this.getShops()
      .pipe(
        map((shops) => shops.find(
          (shop) => shop.id === id)
        )
      );
  }

  /**
   * Loads all shops.
   */
  loadAllShops(): void {
    this.http
      .get<Shop[]>(`${environment.serverURL}/shops`)
      .subscribe(
        shops => {
          this.shops = shops;
          this.shops$.next(this.shops);
        },
        () => console.error('Could not load all the shop.')
      );
  }

  /**
   * Loads a shop.
   *
   * @param id The shop identifier.
   */
  loadShop(id: number): void {
    this.http
      .get<Shop>(`${environment.serverURL}/shops/${id}`)
      .subscribe(
        shop => {
          // Update the shop.
          let newItem = true;
          this.shops.forEach((item, index) => {
            if (item.id === id) {
              this.shops[index] = shop;
              newItem = false;
            }
          });

          // Add the shop.
          if (newItem) {
            this.shops.push(shop);
          }

          // Notify the observers.
          this.shops$.next(this.shops);
        },
        () => console.error('Could not load the shop.')
      );
  }

  /**
   * Creates a shop.
   *
   * @param shop The shop to create.
   */
  createShop(shop: Shop): Observable<Shop> {
    return this.http
      .post<Shop>(
        `${environment.serverURL}/shops`,
        JSON.stringify(shop),
        environment.serverOptions
      )
      .pipe(
        map((createdShop: Shop) => {
          // Add the shop.
          this.shops.push(createdShop);

          // Notify the observers.
          this.shops$.next(this.shops);

          return createdShop;
        }),
        catchError(() =>
          throwError('Could not create the shop.')
        )
      );
  }

  /**
   * Updates a shop.
   *
   * @param shop The shop to update.
   */
  updateShop(shop: Shop): Observable<Shop> {
    return this.http
      .put<Shop>(
        `${environment.serverURL}/shops/${shop.id}`,
        JSON.stringify(shop),
        environment.serverOptions
      )
      .pipe(
        map((updatedShop: Shop) => {
          // Update the shop.
          this.shops.forEach((item, index) => {
            if (item.id === updatedShop.id) {
              this.shops[index] = updatedShop;
            }
          });

          // Notify the observers.
          this.shops$.next(this.shops);

          return updatedShop;
        }),
        catchError(() =>
          throwError('Could not update the shop.')
        )
      );
  }

  /**
   * Deletes a shop.
   *
   * @param id The shop identifier.
   */
  deleteShop(id: number): Observable<void> {
    return this.http
      .delete(`${environment.serverURL}/shops/${id}`)
      .pipe(
        map(() => {
          // Delete the shop.
          this.shops.forEach((item, index) => {
            if (item.id === id) {
              this.shops.splice(index, 1);
            }
          });

          // Notify the observers.
          this.shops$.next(this.shops);
        }),
        catchError(() =>
          throwError('Could not delete the shop.')
        )
      );
  }

  /**
   * Returns the connected shop identifier.
   */
  getConnectedShopId(): number {
    // Redirect to login page.
    if (this.connectedShopId === -1) {
      this.router.navigate(['/log-shop']);
    }
    return this.connectedShopId;
  }

  /**
   * Connects a shop.
   *
   * @param id The shop identifier.
   */
  connectShop(id: number): Shop | undefined {
    const connectedShop = this.shops.find((shop) => shop.id === id);
    if (connectedShop) {
      this.connectedShopId = id;
    }
    return connectedShop;
  }

  /**
   * Disconnects a shop.
   */
  disconnectShop(): void {
    this.connectedShopId = -1;
  }

  //********************************* DISCOUNT PART ******************************************//

  getPromotions(): Observable<Promotion[]> {
    return this.promotions$.asObservable();
  }

  getPromotion(shopId: string): Observable<Promotion[]> {
    return this.promotions$
      .asObservable()
      .pipe(
        map((discounts: Promotion[])=>
          discounts.filter(
            (discount: Promotion) => (
              discount.owner !== undefined &&
              discount.owner.id !== undefined &&
              discount.owner.id.toString() === shopId
            )
          )
        )
      );
  }

  createPromotion(promotionToCreate: Promotion): Observable<Promotion> {
    return this.publicationService
      .createPublication(promotionToCreate,`/shops/${this.connectedShopId}/discounts`)
      .pipe(
        map((discount) => {
          this.promotions.push(discount);
          this.promotions$.next(this.promotions);
          return discount;
        }),
        catchError(() =>
          throwError('Could not create the publication.')
        ));
  }

  private loadAllPromotions(): void {
    this.http
      .get<Promotion[]>(`${environment.serverURL}/discounts`)
      .subscribe(
        discounts => {
          this.promotions = discounts;
          this.promotions$.next(this.promotions);
        },
        () => console.error('Could not load all the discounts.')
      );
  }
}
