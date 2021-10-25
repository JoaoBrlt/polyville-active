import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Shop } from '../../../models/shop.model';
import { ShopService } from '../../../services/shop.service';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { WeekDay } from '@angular/common';

@Component({
  selector: 'app-shop-owner-access',
  templateUrl: './shop-owner-access.component.html',
  styleUrls: ['./shop-owner-access.component.scss']
})
export class ShopOwnerAccessComponent implements OnInit {
  public days = Object.keys(WeekDay).filter(k => typeof WeekDay[k as any] === 'number');
  shopId!: number;
  shop!: Shop;
  shopForm!: FormGroup;

  private lastSub!: Subscription;

  constructor(
    public formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private shopService: ShopService
  ) {
  }

  ngOnInit(): void {
    this.shopId = this.shopService.getConnectedShopId();
    if (!this.shopId) {
      return;
    }
    this.getShop();
  }

  get timeTable(): FormArray {
    return this.shopForm.get('timeTable') as FormArray;
  }

  updateShop(): void {
    this.shop = {...this.shop, ...this.shopForm.getRawValue()} as Shop;
    this.shopService
      .updateShop(this.shop)
      .subscribe();

    // @ts-ignore
    document.getElementById('msg-shop-form').innerHTML = 'Enregistré !';
  }

  private getShop(): void {
    if (this.lastSub) {
      this.lastSub.unsubscribe();
    }

    this.debug('request shop', this.shopId);

    this.lastSub = this.shopService.getShop(this.shopId).subscribe((shop) => {
      if (!shop) {
        return;
      }
      this.shop = shop;

      this.debug('shop got', this.shop);

      this.initFormBuilder();
    });
  }

  private initFormBuilder(): void {
    this.shopForm = this.formBuilder.group({
      name: [this.shop.name],
      latitude: [this.shop.latitude],
      longitude: [this.shop.longitude],
      type: [this.shop.type],
      timeTable: this.formBuilder.array([])
    });
    if ( this.shop.timeTable ) {
      for (const day of this.shop.timeTable) {
        this.timeTable.push(this.formBuilder.group({
          startingHour: day.startingHour,
          endingHour: day.endingHour,
        }));
      }
    }
  }

  private debug(...data: any[]): void {
    if (!this.days) {
      return;
    }
    console.log(data);
  }
}
