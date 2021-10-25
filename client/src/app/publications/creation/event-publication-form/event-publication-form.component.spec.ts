import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventPublicationFormComponent } from './event-publication-form.component';

describe('EventPublicationFormComponent', () => {
  let component: EventPublicationFormComponent;
  let fixture: ComponentFixture<EventPublicationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventPublicationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EventPublicationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
