import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserQuestionAndQuiZComponent } from './user-question-and-quiz.component';

describe('UserQuestionAndQuiZComponent', () => {
  let component: UserQuestionAndQuiZComponent;
  let fixture: ComponentFixture<UserQuestionAndQuiZComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserQuestionAndQuiZComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserQuestionAndQuiZComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
