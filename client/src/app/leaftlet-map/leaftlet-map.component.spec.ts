import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaftletMapComponent } from './leaftlet-map.component';

describe('LeaftletMapComponent', () => {
  let component: LeaftletMapComponent;
  let fixture: ComponentFixture<LeaftletMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeaftletMapComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaftletMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
