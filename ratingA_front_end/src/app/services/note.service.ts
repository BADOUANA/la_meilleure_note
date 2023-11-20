import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  readonly API_URL = "http://localhost:8080"
  readonly ENDPOINT_note ='/note';
  readonly ENDPOINT_Onenote='/note/{id}';
  readonly ENDPOINT_OneDelete ='/note/delete/';
  readonly ENDPOINT_OneUpd ='/note/update/';
  readonly ENDPOINT_Paging ='/note/paging';


    constructor(private httpClient: HttpClient) { }

    getListnote(): Observable<any> {
      return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_note)

    }
    Postnote(data: any): Observable<any> {
      return this.httpClient.post<any>(this.API_URL + this.ENDPOINT_note,data)

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

