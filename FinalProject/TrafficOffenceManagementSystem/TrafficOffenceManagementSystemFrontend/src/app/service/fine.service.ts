import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Fine } from '../model/fine';
import { TokenService } from '../service/token.service';
import { OffenceService } from '../service/offence.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FineService {

  private baseUrl = 'http://localhost:8989/services/fines'; // 8191
  headersForApi = null;
  constructor(private http: HttpClient, private tokenService: TokenService, private offenceService: OffenceService) { }

  // tslint:disable-next-line:typedef
  setHeaders(){
    this.headersForApi = new HttpHeaders({
      // tslint:disable-next-line:object-literal-key-quotes
      'Authorization': `Bearer ${this.tokenService.accessToken}`
      });
  }

  // tslint:disable-next-line:typedef
  public createFine(fine){
    //  return this.http.post<Officer>(`${this.baseUrl}`, officer);
    this.setHeaders();
    return this.http.post<Fine>(`${this.baseUrl}`, fine, { headers: this.headersForApi });
    }

  // tslint:disable-next-line:typedef
  public getFineByLicenseNo(licenseNo){
    this.setHeaders();
    return this.http.get(`${this.baseUrl}/${licenseNo}`, { headers: this.headersForApi });
    }

    // tslint:disable-next-line:typedef
  updateFine(fineId: number, fine: Fine): Observable<Fine>{
    this.setHeaders();
    return this.http.put<Fine>(`${this.baseUrl}/${fineId}`, fine, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  updateFineStatus(licenseNo, fine){
    this.setHeaders();
    return this.http.put<Fine>(`${this.baseUrl}/status/${licenseNo}`, fine, { headers: this.headersForApi });
  }

}
