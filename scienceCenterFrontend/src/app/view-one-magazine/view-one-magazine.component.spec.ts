import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOneMagazineComponent } from './view-one-magazine.component';

describe('ViewOneMagazineComponent', () => {
  let component: ViewOneMagazineComponent;
  let fixture: ComponentFixture<ViewOneMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewOneMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewOneMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
