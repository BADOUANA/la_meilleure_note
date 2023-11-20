import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionFilmRoutingModule } from './gestion-film-routing.module';
import { GestionFilmComponent } from './gestion-film.component';
import { ListComponent } from './list/list.component';
import { EditComponent } from './edit/edit.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    GestionFilmComponent,
    ListComponent,
    EditComponent
  ],
  imports: [
    CommonModule,
    GestionFilmRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule,
  ]
})
export class GestionFilmModule { }
