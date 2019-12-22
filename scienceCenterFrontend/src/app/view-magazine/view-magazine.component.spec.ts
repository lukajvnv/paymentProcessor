import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMagazineComponent } from './view-magazine.component';

describe('ViewMagazineComponent', () => {
  let component: ViewMagazineComponent;
  let fixture: ComponentFixture<ViewMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
