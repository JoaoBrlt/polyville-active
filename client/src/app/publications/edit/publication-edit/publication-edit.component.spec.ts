import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationEditComponent } from './publication-edit.component';

describe('PublicationEditComponent', () => {
  let component: PublicationEditComponent;
  let fixture: ComponentFixture<PublicationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicationEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
