import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionFilmComponent } from './gestion-film.component';

const routes: Routes = [
  {
    path: '',
    component: GestionFilmComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionFilmRoutingModule { }
