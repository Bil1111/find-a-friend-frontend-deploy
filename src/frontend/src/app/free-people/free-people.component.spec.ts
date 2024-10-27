import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreePeopleComponent } from './free-people.component';

describe('FreePeopleComponent', () => {
  let component: FreePeopleComponent;
  let fixture: ComponentFixture<FreePeopleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FreePeopleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FreePeopleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
