import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmindashbordComponent } from './admindashbord/admindashbord.component';
import { HeaderComponent } from './header/header.component';
import { ShelteradminComponent } from './shelteradmin/shelteradmin.component';

const routes: Routes = [
  {
    path: '', component: AdmindashbordComponent, children: [
      { path: 'header', component: HeaderComponent },
      { path: 'shelteradmin', component: ShelteradminComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }