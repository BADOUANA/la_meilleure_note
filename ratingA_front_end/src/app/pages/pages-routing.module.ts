
import { RouterModule, Routes } from '@angular/router';
import { PagesComponent } from './pages.component';
import { NgModule } from '@angular/core';




const routes: Routes = [
  {
  path: '',
  component: PagesComponent,
  children: [
    
    {
      path: 'users',
      loadChildren: () => import("./gestion-users/gestion-users.module").then((m) => m.GestionUsersModule
      )

    },
    {
      path: 'users/add',
      loadChildren: () => import("./gestion-users/add/add.module").then((m) => m.AddModule
      )

    },
    {
     path: 'film',
     loadChildren: () => import("./gestion-film/gestion-film.module").then((m)=>m.GestionFilmModule)
    },
    {
      path: 'film/add',
      loadChildren: () => import("./gestion-film/add/add.module").then((m)=>m.AddModule)
     },
     {
      path: 'film',
      loadChildren: () => import("./gestion-film/gestion-film.module").then((m)=>m.GestionFilmModule)
     },
     {
       path: 'film/add',
       loadChildren: () => import("./gestion-film/add/add.module").then((m)=>m.AddModule)
      },
      {
        path: 'note',
        loadChildren: () => import("./gestion-note/gestion-note.module").then((m)=>m.GestionNoteModule)
       },
       {
        path: '',
        loadChildren: () => import("./chart/chart.module").then((m)=>m.ChartModule)
       }


  ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule { }
