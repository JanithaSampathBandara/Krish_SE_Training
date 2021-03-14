import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Offence } from '../model/offence';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class OffenceService {

  private baseUrl = 'http://localhost:8989/services/offences'; // 8191
  headersForApi = null;
  constructor(private http: HttpClient, private tokenService: TokenService) { }

  // tslint:disable-next-line:typedef
  setHeaders(){
    this.headersForApi = new HttpHeaders({
      // tslint:disable-next-line:object-literal-key-quotes
      'Authorization': `Bearer ${this.tokenService.accessToken}`
      });
  }

  // tslint:disable-next-line:typedef
  public getOffenceById(offenceId){
    this.setHeaders();
    return this.http.get(`${this.baseUrl}/${offenceId}`, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  public createOffence(offence){
    //  return this.http.post<Officer>(`${this.baseUrl}`, officer);
    this.setHeaders();
    return this.http.post<Offence>(`${this.baseUrl}`, offence, { headers: this.headersForApi });
    }

    // tslint:disable-next-line:typedef
  updateOffence(offenceId: number, offence: Offence): Observable<Offence>{
    this.setHeaders();
    return this.http.put<Offence>(`${this.baseUrl}/${offenceId}`, offence, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  deleteOffence(offenceId: number){
    this.setHeaders();
    return this.http.delete(`${this.baseUrl}/${offenceId}`, { headers: this.headersForApi });
  }

}
