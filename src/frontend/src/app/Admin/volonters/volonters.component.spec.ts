import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VolontersComponent } from './volonters.component';

describe('VolontersComponent', () => {
  let component: VolontersComponent;
  let fixture: ComponentFixture<VolontersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VolontersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VolontersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
