import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FineComponent } from './fine.component';

describe('FineComponent', () => {
  let component: FineComponent;
  let fixture: ComponentFixture<FineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
