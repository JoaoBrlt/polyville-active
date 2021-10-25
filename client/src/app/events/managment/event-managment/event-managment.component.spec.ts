import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventManagmentComponent } from './event-managment.component';

describe('EventManagmentComponent', () => {
  let component: EventManagmentComponent;
  let fixture: ComponentFixture<EventManagmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventManagmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EventManagmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
