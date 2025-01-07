import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ThignsComponent } from './thigns.component';

describe('ThignsComponent', () => {
  let component: ThignsComponent;
  let fixture: ComponentFixture<ThignsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ThignsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ThignsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
