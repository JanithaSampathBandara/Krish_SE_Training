import { TestBed } from '@angular/core/testing';

import { OffenceService } from './offence.service';

describe('OffenceService', () => {
  let service: OffenceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OffenceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
