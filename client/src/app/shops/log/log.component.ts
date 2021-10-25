import { Component, OnInit } from '@angular/core';
import { Shop } from '../../../models/shop.model';
import { ShopService } from '../../../services/shop.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
})
export class LogComponent implements OnInit {
  shops: Shop[];
  selectedId!: number;

  constructor(
    private shopService: ShopService,
    private router: Router
  ) {
    this.shops = [];
    this.shopService
      .getShops()
      .subscribe((shops: Shop[]) => {
        this.shops = shops;
      });
  }

  ngOnInit(): void {
  }

  idExists(): boolean {
    return this.shops.find((shop) => this.selectedId === shop.id) !== undefined;
  }

  selectShop(e: KeyboardEvent): void {
    if (!e.target) {
      return;
    }
    this.selectedId = parseInt((e.target as HTMLTextAreaElement).value, 10);
  }

  connectShop(): void {
    if (this.shopService.connectShop(this.selectedId)) {
      this.router.navigate(['/shop']);
    }
  }

  createShop(): void {
    this.shopService
      .createShop({
        latitude: 43.123456,
        longitude: 7.123456,
        id: this.selectedId,
        name: 'Shop',
        type: 'shop',
        timeTable: [{
          startingHour: '8h00',
          endingHour: '19h00'
        }]
      })
      .subscribe((shop) => {
        this.selectedId = shop.id;
        this.connectShop();
      });
  }
}
