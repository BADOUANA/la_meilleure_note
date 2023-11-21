import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
readonly API_URL = "http://localhost:8080/"
readonly ENDPOINT_user ='users';
readonly ENDPOINT_OneUser='users/{id}';
readonly ENDPOINT_OneDelete ='users/delete/';
readonly ENDPOINT_OneUpd ='users/update/';
readonly ENDPOINT_Paging ='users';


  constructor(private httpClient: HttpClient) { }

  getListUsers(): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_user)

  }
  Postusers(data: any): Observable<any> {
    return this.httpClient.post<any>(this.API_URL + this.ENDPOINT_user,data)

  }
  getOneuser(id: number): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_OneUser)
  }
  getListusersByPagination(params: any): Observable<any> {
    return this.httpClient.get<any>(this.API_URL + this.ENDPOINT_Paging,{params});

  }
  deleteusers(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.API_URL +this.ENDPOINT_OneDelete+id);

  }
  Updateusers(data:any ,  id:number): Observable<any> {
    return this.httpClient.put<any>(this.API_URL + this.ENDPOINT_OneUpd+id,{data});

  }
}
