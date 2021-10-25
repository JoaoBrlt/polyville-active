import { Component, OnInit } from '@angular/core';
import { Promotion } from '../../../../models/shop.model';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PublicationService } from '../../../../services/publication.service';
import { ShopService } from '../../../../services/shop.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-promotion-form',
  templateUrl: './promotion-form.component.html',
  styleUrls: ['./promotion-form.component.scss']
})
export class PromotionFormComponent implements OnInit {
  promotion: Promotion = {
    title: '',
    content: '',
    type: '',
    date: new Date(),
    isPercentage: false,
    value: 0,
    condition: ''
  };
  promotionForm!: FormGroup;
  private shopId!: number;
  private lastSub!: Subscription;

  constructor(
    public formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private shopService: ShopService
  ) {
  }

  ngOnInit(): void {
    this.shopId = this.shopService.getConnectedShopId();
    this.initFormBuilder();
  }

  createPromotion(): void {
    const promotionToCreate: Promotion = this.promotionForm.getRawValue() as Promotion;
    this.shopService.createPromotion(promotionToCreate).subscribe();
  }

  log(): void {
    console.log('test', this.promotionForm.getRawValue().isPercentage);
  }

  private initFormBuilder(): void {
    this.promotionForm = this.formBuilder.group({
      title: [this.promotion.title],
      condition: [this.promotion.condition],
      isPercentage: [false],
      value: [this.promotion.value]
    });
  }
}
