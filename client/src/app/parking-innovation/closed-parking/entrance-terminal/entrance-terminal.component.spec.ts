import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntranceTerminalComponent } from './entrance-terminal.component';

describe('EntranceTerminalComponent', () => {
  let component: EntranceTerminalComponent;
  let fixture: ComponentFixture<EntranceTerminalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntranceTerminalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EntranceTerminalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
