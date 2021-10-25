import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClosedParkingSimulationComponent } from './closed-parking-simulation.component';

describe('ClosedParkingSimulationComponent', () => {
  let component: ClosedParkingSimulationComponent;
  let fixture: ComponentFixture<ClosedParkingSimulationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClosedParkingSimulationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClosedParkingSimulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
