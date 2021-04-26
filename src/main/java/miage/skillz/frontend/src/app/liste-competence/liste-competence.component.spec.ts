import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeCompetenceComponent } from './liste-competence.component';

describe('ListeCompetenceComponent', () => {
  let component: ListeCompetenceComponent;
  let fixture: ComponentFixture<ListeCompetenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeCompetenceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeCompetenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
