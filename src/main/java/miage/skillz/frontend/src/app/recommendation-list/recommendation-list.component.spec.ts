import { ComponentFixture, TestBed } from '@angular/core/testing';

import { recommendationListComponent } from './recommendation-list.component';

describe('recommendationListComponent', () => {
  let component: recommendationListComponent;
  let fixture: ComponentFixture<recommendationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ recommendationListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(recommendationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
