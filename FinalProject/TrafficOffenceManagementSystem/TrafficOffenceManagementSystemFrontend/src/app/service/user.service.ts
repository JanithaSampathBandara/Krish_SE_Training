import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:9191/oauthuser';

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  public createUser(user, role){

    return this.http.post(`${this.baseUrl}/${role}`, user);
  }

  // tslint:disable-next-line:typedef
  public updateUser(user){

    return this.http.put(`${this.baseUrl}`, user);
  }

  // tslint:disable-next-line:typedef
  public deleteUser(userName){

    return this.http.delete(`${this.baseUrl}/${userName}`);
  }

}
