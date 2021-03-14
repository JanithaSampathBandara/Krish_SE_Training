import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Driver } from '../model/driver';
import { DriverResponse } from '../model/driver-response';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  private baseUrl = 'http://localhost:8989/services/drivers'; // 8192
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
  public getDriverByLicense(driverId: string){
    this.setHeaders();
    return this.http.get(`${this.baseUrl}/${driverId}`, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  public createDriver(driver){
    //  return this.http.post<Officer>(`${this.baseUrl}`, officer);
    this.setHeaders();
    return this.http.post<Driver>(`${this.baseUrl}`, driver, { headers: this.headersForApi });
    }

    // tslint:disable-next-line:typedef
  updateDriver(driverId: string, driver: Driver): Observable<DriverResponse>{
    this.setHeaders();
    return this.http.put<DriverResponse>(`${this.baseUrl}/${driverId}`, driver, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  deleteDriver(driverId: string){
    this.setHeaders();
    return this.http.delete(`${this.baseUrl}/${driverId}`, { headers: this.headersForApi });
  }

  // tslint:disable-next-line:typedef
  public getDriverByNic(nic: string){
    this.setHeaders();
    return this.http.get(`${this.baseUrl}/nic/${nic}`, { headers: this.headersForApi });
  }


}
