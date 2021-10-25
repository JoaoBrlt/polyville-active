import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CityEventComponent } from './city-event.component';

describe('CityEventComponent', () => {
  let component: CityEventComponent;
  let fixture: ComponentFixture<CityEventComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CityEventComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CityEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
