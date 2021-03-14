import { TestBed } from '@angular/core/testing';

import { OfficerService } from './officer.service';

describe('OfficerService', () => {
  let service: OfficerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfficerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
