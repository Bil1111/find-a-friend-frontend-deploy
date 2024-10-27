import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegestComponent } from './regest.component';

describe('RegestComponent', () => {
  let component: RegestComponent;
  let fixture: ComponentFixture<RegestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
