import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private baseUrl = 'http://localhost:8989/services/payments'; // 8888
  headersForApi = null;

  constructor(private tokenService: TokenService, private http: HttpClient) { }

// tslint:disable-next-line:typedef
  setHeaders(){
  this.headersForApi = new HttpHeaders({
    // tslint:disable-next-line:object-literal-key-quotes
    'Authorization': `Bearer ${this.tokenService.accessToken}`
    });
}

  // tslint:disable-next-line:typedef
  getTotalAmount(licenseNo){
    this.setHeaders();
    return this.http.get(`${this.baseUrl}/` + licenseNo, { headers: this.headersForApi });
  }
}
