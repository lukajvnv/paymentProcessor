import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubcriptionComponent } from './subcription.component';

describe('SubcriptionComponent', () => {
  let component: SubcriptionComponent;
  let fixture: ComponentFixture<SubcriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubcriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubcriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
