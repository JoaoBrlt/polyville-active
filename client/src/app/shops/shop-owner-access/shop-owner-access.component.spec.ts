import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopOwnerAccessComponent } from './shop-owner-access.component';

describe('ShopOwnerAccessComponent', () => {
  let component: ShopOwnerAccessComponent;
  let fixture: ComponentFixture<ShopOwnerAccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopOwnerAccessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopOwnerAccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
