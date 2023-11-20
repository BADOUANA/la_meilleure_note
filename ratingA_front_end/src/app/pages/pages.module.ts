import { NO_ERRORS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { PagesRoutingModule } from './pages-routing.module';
import { PagesComponent } from './pages.component';

import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';





@NgModule({
  declarations: [
    PagesComponent,
    HeaderComponent,
    FooterComponent


  ],
  imports: [
    CommonModule,
    PagesRoutingModule
  ],
  schemas: [
    NO_ERRORS_SCHEMA
  ],
})
export class PagesModule { }
