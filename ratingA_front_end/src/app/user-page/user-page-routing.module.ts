import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserPageComponent } from './user-page.component';

const routes: Routes = [
  {
    path: '',
    component: UserPageComponent,
    children:[
      {

          path: 'principale',
          loadChildren: () => import("./principale/principale.module").then((m) => m.PrincipaleModule)
      },
       {

          path: 'list',
          loadChildren: () => import("./principale/list/list.module").then((m) => m.ListModule)
      },
      {

        path: 'note',
        loadChildren: () => import("./principale/note/note.module").then((m) => m.NoteModule)
    }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserPageRoutingModule { }
