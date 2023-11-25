import { NO_ERRORS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrincipaleRoutingModule } from './principale-routing.module';
import { PrincipaleComponent } from './principale.component';
import { ListComponent } from './list/list.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { NoteComponent } from './note/note.component';


@NgModule({
  declarations: [
    PrincipaleComponent,
    ListComponent,
    NoteComponent
  ],
  imports: [
    CommonModule,
    PrincipaleRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule,
  ],
  schemas: [
    NO_ERRORS_SCHEMA
  ],
})
export class PrincipaleModule { }
