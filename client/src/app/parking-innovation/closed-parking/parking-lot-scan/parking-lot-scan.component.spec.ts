import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkingLotScanComponent } from './parking-lot-scan.component';

describe('ParkingLotScanComponent', () => {
  let component: ParkingLotScanComponent;
  let fixture: ComponentFixture<ParkingLotScanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParkingLotScanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkingLotScanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
