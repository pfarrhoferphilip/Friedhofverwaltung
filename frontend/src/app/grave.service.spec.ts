import { TestBed } from '@angular/core/testing';

import { GraveService } from './grave.service';

describe('GraveService', () => {
  let service: GraveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GraveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
