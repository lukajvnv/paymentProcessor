import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTxComponent } from './user-tx.component';

describe('UserTxComponent', () => {
  let component: UserTxComponent;
  let fixture: ComponentFixture<UserTxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserTxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserTxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
