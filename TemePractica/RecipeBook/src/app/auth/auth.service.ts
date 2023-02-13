import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly serverUrl = 'http://localhost:3000';
  constructor(private httpClient: HttpClient) { }

  login(body:any) : Observable <any>
  {
  return this.httpClient.post(this.serverUrl + "/login",body);
  }
  register(body:any):Observable<any>
  {
    return this.httpClient.post(this.serverUrl + "/register",body);
  }

}
