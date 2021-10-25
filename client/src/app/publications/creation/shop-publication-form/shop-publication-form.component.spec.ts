import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopPublicationFormComponent } from './shop-publication-form.component';

describe('ShopPublicationFormComponent', () => {
  let component: ShopPublicationFormComponent;
  let fixture: ComponentFixture<ShopPublicationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopPublicationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopPublicationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
