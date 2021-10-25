import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentTerminalComponent } from './payment-terminal.component';

describe('PaymentTerminalComponent', () => {
  let component: PaymentTerminalComponent;
  let fixture: ComponentFixture<PaymentTerminalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentTerminalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentTerminalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
