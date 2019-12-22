import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FailedPageComponent } from './failed-page.component';

describe('FailedPageComponent', () => {
  let component: FailedPageComponent;
  let fixture: ComponentFixture<FailedPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FailedPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FailedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
