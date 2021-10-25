import { Component, Input, OnInit } from '@angular/core';
import { Shop, Promotion } from '../../../models/shop.model';
import { ShopService } from '../../../services/shop.service';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.scss']
})
export class PromotionComponent implements OnInit {
  @Input()
  promotion!: Promotion;

  constructor(private shopService: ShopService) {
  }

  ngOnInit(): void {
  }

  getReduction() {
    if (this.promotion.isPercentage){
      return this.promotion.value+'%';
    }
    return '-'+(this.promotion.value)+'€';
  }
}
