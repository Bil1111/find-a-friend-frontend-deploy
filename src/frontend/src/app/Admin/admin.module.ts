import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { AdmindashbordComponent } from './admindashbord/admindashbord.component';
import { HeaderComponent } from './header/header.component';
import { ShelteradminComponent } from './shelteradmin/shelteradmin.component';


@NgModule({
  declarations: [
    AdmindashbordComponent,
    HeaderComponent,
    ShelteradminComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule
  ]
})
export class AdminModule { }
