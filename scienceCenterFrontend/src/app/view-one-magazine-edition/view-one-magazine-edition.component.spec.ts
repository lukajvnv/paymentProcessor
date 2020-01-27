import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOneMagazineEditionComponent } from './view-one-magazine-edition.component';

describe('ViewOneMagazineEditionComponent', () => {
  let component: ViewOneMagazineEditionComponent;
  let fixture: ComponentFixture<ViewOneMagazineEditionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewOneMagazineEditionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewOneMagazineEditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
