import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutsideParkingSimulationComponent } from './outside-parking-simulation.component';

describe('OutsideParkingSimulationComponent', () => {
  let component: OutsideParkingSimulationComponent;
  let fixture: ComponentFixture<OutsideParkingSimulationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OutsideParkingSimulationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OutsideParkingSimulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
