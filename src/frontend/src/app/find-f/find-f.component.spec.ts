import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindFComponent } from './find-f.component';

describe('FindFComponent', () => {
  let component: FindFComponent;
  let fixture: ComponentFixture<FindFComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FindFComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FindFComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
