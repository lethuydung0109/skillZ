import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreerCompetenceComponent } from './creer-competence.component';

describe('CreerCompetenceComponent', () => {
  let component: CreerCompetenceComponent;
  let fixture: ComponentFixture<CreerCompetenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreerCompetenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreerCompetenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
