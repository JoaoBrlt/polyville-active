import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingInnovationTicketComponent } from './parking-innovation-ticket.component';

describe('ParkingInnovationTicketComponent', () => {
  let component: ParkingInnovationTicketComponent;
  let fixture: ComponentFixture<ParkingInnovationTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParkingInnovationTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingInnovationTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
