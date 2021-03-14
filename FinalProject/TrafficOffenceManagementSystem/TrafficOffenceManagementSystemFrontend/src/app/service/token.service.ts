import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

// import { Observable } from 'rxjs';
import { Observable } from 'rxjs';
// tslint:disable-next-line:import-blacklist

// import { Observable } from 'rxjs/internal/Observable';
// tslint:disable-next-line:import-spacing
// import { Headers , Http , HttpModule }  from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private baseUrl = 'http://localhost:9191/oauth/token';
  accessToken = localStorage.getItem('accessToken');
  headersForApi = null;
  data = null;
  usrnme = 'mobile';
  pwd = 'pin';
  role = '';
  // userName = '';
  /*
  jwt = null;
  jwtData = null;
  decodedJwtJsonData = null;
  decodedJwtData = null;
  isAdmin = null;
*/
  constructor(private http: HttpClient) { }


  // tslint:disable-next-line:typedef
  public getTokenInfo(token: string){

          this.headersForApi = new HttpHeaders({
      //  'Content-Type':  'application/json',
         // tslint:disable-next-line:object-literal-key-quotes
         'Authorization': 'Basic ' + btoa(this.usrnme + ':' + this.pwd)

        });

        // tslint:disable-next-line:align
        return this.http.get('http://localhost:9191/oauth/check_token?token=' + this.accessToken, { headers: this.headersForApi });

 /*   this.jwt = this.accessToken;

    // tslint:disable-next-line:align
    this.jwtData = this.jwt.split('.')[1];
     this.decodedJwtJsonData = window.atob(this.jwtData);
     this.decodedJwtData = JSON.parse(this.decodedJwtJsonData);
  //  this.isAdmin = this.decodedJwtData.
    // tslint:disable-next-line:align
    console.log('jwtData: ' + this.jwtData);
    // tslint:disable-next-line:align
    console.log('decodedJwtJsonData: ' + this.decodedJwtJsonData);
    // tslint:disable-next-line:align
    console.log('decodedJwtData: ' + this.decodedJwtData);
  //  console.log('Is admin: ' + isAdmin)
*/

 /*   this.headersForApi = new HttpHeaders({
      //  'Content-Type':  'application/json',
         // tslint:disable-next-line:object-literal-key-quotes
         'Authorization': 'Basic ' + btoa(this.usrnme + ':' + this.pwd)

        });

        return this.http.get('http://localhost:9191/oauth/check_token?token=' + accessToken, this.data , { headers: this.headersForApi });
  */
      }

  // tslint:disable-next-line:typedef
  public getToken(userName: string, password: string): Observable<any>{
     // tslint:disable-next-line:max-line-length
     this.headersForApi = new HttpHeaders({
    //  'Content-Type':  'application/json',
    'content-type': 'application/x-www-form-urlencoded',
       // tslint:disable-next-line:object-literal-key-quotes
       'Authorization': 'Basic ' + btoa(this.usrnme + ':' + this.pwd)
      }); // {'content-type': 'application/form-data'} x-www-form-urlencoded
     this.data = 'grant_type=password&username=' + userName + '&password=' + password;
    // tslint:disable-next-line:align
    return this.http.post(this.baseUrl, this.data , { headers: this.headersForApi });
  }

}
