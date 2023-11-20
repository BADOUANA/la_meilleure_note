import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionUsersComponent } from './gestion-users.component';

const routes: Routes = [
  {
    path: '',
    component: GestionUsersComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionUsersRoutingModule { }
