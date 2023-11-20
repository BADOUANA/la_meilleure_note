import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

readonly API_URL = "http://localhost:8080"
readonly ENDPOINT_film ='/film';
readonly ENDPOINT_Onefilm='/film/{id}';
readonly ENDPOINT_OneDelete ='/film/delete/';
readonly ENDPOINT_OneUpd ='/film/update/';
readonly ENDPOINT_Paging ='/film/paging';


  constructor(private httpClient: HttpClient) { }

  getListfilm(): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_film)

  }
  Postfilm(data: any): Observable<any> {
    return this.httpClient.post<any>(this.API_URL + this.ENDPOINT_film,data)

  }
  getOnefilm(id: number): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_Onefilm)
  }
  getListfilmByPagination(params: any): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_Paging,{params});

  }
  deletefilm(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.API_URL +this.ENDPOINT_OneDelete+id);

  }
  Updatefilm(data:any ,  id:number): Observable<any> {
    return this.httpClient.put<any>(this.API_URL + this.ENDPOINT_OneUpd+id,{data});

  }
}
