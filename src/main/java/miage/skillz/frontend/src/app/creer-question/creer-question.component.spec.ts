import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreerQuestionComponent } from './creer-question.component';

describe('CreerQuestionComponent', () => {
  let component: CreerQuestionComponent;
  let fixture: ComponentFixture<CreerQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreerQuestionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreerQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
