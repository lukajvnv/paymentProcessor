import { TestBed } from '@angular/core/testing';

import { PayServiceService } from './pay-service.service';

describe('PayServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PayServiceService = TestBed.get(PayServiceService);
    expect(service).toBeTruthy();
  });
});
