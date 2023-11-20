import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionNoteRoutingModule } from './gestion-note-routing.module';
import { GestionNoteComponent } from './gestion-note.component';
import { ListComponent } from './list/list.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    GestionNoteComponent,
    ListComponent
  ],
  imports: [
    CommonModule,
    GestionNoteRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule,
  ]
})
export class GestionNoteModule { }
