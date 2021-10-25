import { Component, OnInit } from '@angular/core';
import {Shop} from '../../../models/shop.model';
import {ShopService} from '../../../services/shop.service';

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.scss']
})
export class ShopListComponent implements OnInit {
  shopList: Shop[] = [];

  constructor(private shopService: ShopService) { this.getShops(); }

  ngOnInit(): void {
  }

  private getShops(): void {
    this.shopService
      .getShops()
      .subscribe((shops: Shop[]) => {
        this.shopList = shops;
      });
  }
}
