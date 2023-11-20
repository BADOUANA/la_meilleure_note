import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrincipaleComponent } from './principale.component';

const routes: Routes = [
  {
    path: '',
    component: PrincipaleComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrincipaleRoutingModule { }
