import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelAnimalsComponent } from './tabel-animals.component';

describe('TabelAnimalsComponent', () => {
  let component: TabelAnimalsComponent;
  let fixture: ComponentFixture<TabelAnimalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TabelAnimalsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TabelAnimalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
