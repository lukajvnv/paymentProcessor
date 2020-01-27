import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllMagazinesComponent } from './view-all-magazines.component';

describe('ViewAllMagazinesComponent', () => {
  let component: ViewAllMagazinesComponent;
  let fixture: ComponentFixture<ViewAllMagazinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAllMagazinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAllMagazinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
