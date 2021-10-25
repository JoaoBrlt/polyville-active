import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingOwnerAccessComponent } from './parking-owner-access.component';

describe('ParkingOwnerAccessComponent', () => {
  let component: ParkingOwnerAccessComponent;
  let fixture: ComponentFixture<ParkingOwnerAccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParkingOwnerAccessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingOwnerAccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
