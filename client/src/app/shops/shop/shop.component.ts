import {Component, Input, OnInit} from '@angular/core';
import {Shop} from '../../../models/shop.model';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {
  @Input()
  shop!: Shop;

  constructor() { }

  ngOnInit(): void {
  }

}
