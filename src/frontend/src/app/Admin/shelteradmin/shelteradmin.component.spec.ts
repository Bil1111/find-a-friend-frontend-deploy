import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelteradminComponent } from './shelteradmin.component';

describe('ShelteradminComponent', () => {
  let component: ShelteradminComponent;
  let fixture: ComponentFixture<ShelteradminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShelteradminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelteradminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
