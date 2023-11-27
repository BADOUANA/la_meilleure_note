import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  readonly API_URL = "http://localhost:8080"
  readonly ENDPOINT_note ='/api/rates/list';
  readonly ENDPOINT_notes ='/api/rates';
  readonly ENDPOINT_Onenote ='/api/rates/{id}';
  readonly ENDPOINT_OneDelete ='/api/rates/delete/';
  readonly ENDPOINT_OneUpd ='/api/rates/update/';
  readonly ENDPOINT_Paging ='/api/rates/paging';


    constructor(private httpClient: HttpClient) { }

    getListnote(): Observable<any> {
      return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_note)

    }
    Postnote(data: any): Observable<any> {
      return this.httpClient.post<any>(this.API_URL + this.ENDPOINT_notes,data)

    }
    getOnenote(id: number): Observable<any> {
      return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_Onenote)
    }
    getListnoteByPagination(params: any): Observable<any> {
      return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_Paging,{params});

    }
    deletenote(id: number): Observable<any> {
      return this.httpClient.delete<any>(this.API_URL +this.ENDPOINT_OneDelete+id);

    }
    Updatenote(data:any ,  id:number): Observable<any> {
      return this.httpClient.put<any>(this.API_URL + this.ENDPOINT_OneUpd+id,{data});

    }
  }

