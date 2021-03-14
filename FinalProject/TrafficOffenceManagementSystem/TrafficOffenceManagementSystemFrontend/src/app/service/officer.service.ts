import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Officer } from 'src/app/model/officer';
import { Response } from 'src/app/model/response';
import { OfficerComponent } from '../officer/officer.component';
import { TokenService } from '../service/token.service';



@Injectable({
  providedIn: 'root'
})
export class OfficerService {

  private baseUrl = 'http://localhost:8989/services/officers'; // 8193
  headersForApi = null;

  constructor(private http: HttpClient, private tokenService: TokenService ){ }

  // tslint:disable-next-line:typedef
  setHeaders(){
    this.headersForApi = new HttpHeaders({
      // tslint:disable-next-line:object-literal-key-quotes
      'Authorization': `Bearer ${this.tokenService.accessToken}`
      });
  }

  getOfficers(): Observable<Officer[]>{

    this.setHeaders();
    return this.http.get<Officer[]>(`${this.baseUrl}`, { headers: this.headersForApi } );
  }
/*
  getOfficer(officerId: number): Observable<Officer>{
    return this.http.get<Officer>(`${this.baseUrl}/${officerId}`);
  }
*/

getOfficerById(officerId: number): Observable<Response>{

  this.setHeaders();

  console.log('header' , this.tokenService.accessToken);
  console.log(this.headersForApi);
  return this.http.get<Response>(`${this.baseUrl}/${officerId}`, { headers: this.headersForApi });
}

  // tslint:disable-next-line:typedef
  public getOfficer(officerId){
    this.setHeaders();
    return this.http.get(`${this.baseUrl}/` + officerId, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  public createOfficer(officer){
    this.setHeaders();
  //  return this.http.post<Officer>(`${this.baseUrl}`, officer);
    return this.http.post<Officer>(this.baseUrl, officer , { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  updateOfficer(officerId: number, officer: Officer): Observable<Response>{
    this.setHeaders();
    return this.http.put<Response>(`${this.baseUrl}/${officerId}`, officer, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  deleteOfficer(officerId: number){
    this.setHeaders();
    return this.http.delete(`${this.baseUrl}/${officerId}`, { headers: this.headersForApi });
  }

}
