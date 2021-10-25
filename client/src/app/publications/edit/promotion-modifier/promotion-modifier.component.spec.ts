import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionModifierComponent } from './promotion-modifier.component';

describe('PromotionModifierComponent', () => {
  let component: PromotionModifierComponent;
  let fixture: ComponentFixture<PromotionModifierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PromotionModifierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionModifierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
