import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewClientHtmlComponent } from './new-client-html.component';

describe('NewClientHtmlComponent', () => {
  let component: NewClientHtmlComponent;
  let fixture: ComponentFixture<NewClientHtmlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewClientHtmlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewClientHtmlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
