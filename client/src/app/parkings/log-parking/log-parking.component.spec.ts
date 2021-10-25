import { ComponentFixture, TestBed } from '@angular/core/testing';
import {LogParkingComponent} from './log-parking.component';


describe('LogParkingComponent', () => {
  let component: LogParkingComponent;
  let fixture: ComponentFixture<LogParkingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogParkingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LogParkingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
