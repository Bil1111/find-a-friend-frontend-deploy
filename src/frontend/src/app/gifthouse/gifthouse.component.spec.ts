import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GifthouseComponent } from './gifthouse.component';

describe('GifthouseComponent', () => {
  let component: GifthouseComponent;
  let fixture: ComponentFixture<GifthouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GifthouseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GifthouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
