
import { RouterModule, Routes } from '@angular/router';

import { PagesComponent } from './pages/pages.component';

import { NO_ERRORS_SCHEMA, NgModule } from '@angular/core';


const routes: Routes = [

      {
        path: 'pages',
        loadChildren:() =>import("./pages/pages.module").then((p) =>p.PagesModule),

      },
      {
        path: 'userpages',
        loadChildren:() =>import("./user-page/user-page.module").then((p) =>p.UserPageModule)
      }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  schemas: [
    NO_ERRORS_SCHEMA
  ]

})
export class AppRoutingModule { }
