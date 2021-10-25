import { Component, OnInit } from '@angular/core';
import { ShopService } from '../../../../services/shop.service';
import { Shop } from '../../../../models/shop.model';

@Component({
  selector: 'app-shop-publication-form',
  templateUrl: './shop-publication-form.component.html',
  styleUrls: ['./shop-publication-form.component.scss']
})
export class ShopPublicationFormComponent implements OnInit {
  shop!: Shop;

  constructor(
    private shopService: ShopService
  ) { }

  ngOnInit(): void {
    this.getShop();
  }

  private getShop(): void {
    const id = this.shopService.getConnectedShopId();
    this.shopService.getShop(id).subscribe((shop)=>{
      if ( shop ) {
        this.shop=shop;
      }
    });
  }
}
