import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayMagazineFormComponent } from './pay-magazine-form.component';

describe('PayMagazineFormComponent', () => {
  let component: PayMagazineFormComponent;
  let fixture: ComponentFixture<PayMagazineFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayMagazineFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayMagazineFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
