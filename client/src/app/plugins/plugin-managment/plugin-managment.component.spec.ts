import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PluginManagmentComponent } from './plugin-managment.component';

describe('PluginManagmentComponent', () => {
  let component: PluginManagmentComponent;
  let fixture: ComponentFixture<PluginManagmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PluginManagmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PluginManagmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
