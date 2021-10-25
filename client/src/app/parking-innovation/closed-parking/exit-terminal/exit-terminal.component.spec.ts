import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExitTerminalComponent } from './exit-terminal.component';

describe('ExitTerminalComponent', () => {
  let component: ExitTerminalComponent;
  let fixture: ComponentFixture<ExitTerminalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExitTerminalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExitTerminalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
