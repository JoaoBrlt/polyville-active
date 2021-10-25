import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutsideParkingAdditionalPaymentComponent } from './outside-parking-additional-payment.component';

describe('OutsideParkingAdditionalPaymentComponent', () => {
  let component: OutsideParkingAdditionalPaymentComponent;
  let fixture: ComponentFixture<OutsideParkingAdditionalPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OutsideParkingAdditionalPaymentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OutsideParkingAdditionalPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
