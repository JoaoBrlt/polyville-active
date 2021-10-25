import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutsideParkingTerminalComponent } from './outside-parking-terminal.component';

describe('OutsideParkingTerminalComponent', () => {
  let component: OutsideParkingTerminalComponent;
  let fixture: ComponentFixture<OutsideParkingTerminalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OutsideParkingTerminalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OutsideParkingTerminalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
