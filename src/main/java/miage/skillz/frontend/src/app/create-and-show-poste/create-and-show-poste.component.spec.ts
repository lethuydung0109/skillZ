import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAndShowPosteComponent } from './create-and-show-poste.component';

describe('CreateAndShowPosteComponent', () => {
  let component: CreateAndShowPosteComponent;
  let fixture: ComponentFixture<CreateAndShowPosteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateAndShowPosteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAndShowPosteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
