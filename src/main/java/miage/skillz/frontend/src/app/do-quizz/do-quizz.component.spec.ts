import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoQuizzComponent } from './do-quiz.component';

describe('DoQuizzComponent', () => {
  let component: DoQuizzComponent;
  let fixture: ComponentFixture<DoQuizzComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoQuizzComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoQuizzComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
