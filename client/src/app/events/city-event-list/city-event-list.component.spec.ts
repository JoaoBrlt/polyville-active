import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CityEventListComponent } from './city-event-list.component';

describe('CityEventListComponent', () => {
  let component: CityEventListComponent;
  let fixture: ComponentFixture<CityEventListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CityEventListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CityEventListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
