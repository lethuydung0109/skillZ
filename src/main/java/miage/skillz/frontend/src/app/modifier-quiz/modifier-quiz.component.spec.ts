import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierQuizComponent } from './modifier-quiz.component';

describe('ModifierQuizComponent', () => {
  let component: ModifierQuizComponent;
  let fixture: ComponentFixture<ModifierQuizComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModifierQuizComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifierQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
