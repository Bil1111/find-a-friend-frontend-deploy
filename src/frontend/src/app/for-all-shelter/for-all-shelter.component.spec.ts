import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForAllShelterComponent } from './for-all-shelter.component';

describe('ForAllShelterComponent', () => {
  let component: ForAllShelterComponent;
  let fixture: ComponentFixture<ForAllShelterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ForAllShelterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForAllShelterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
