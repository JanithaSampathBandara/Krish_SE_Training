import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { NgModel } from '@angular/forms';
import { TokenService } from '../service/token.service';
import { Token } from '../model/token';
import { TokenInfo } from '../model/token-info';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

userName = '';
password = '';
public token: Token = new Token();
public tokenInfo = new TokenInfo();

constructor(private router: Router, private tokenService: TokenService, private toastr: ToastrService ) { }

  // tslint:disable-next-line:typedef
  login(){
    this.tokenService.getToken(this.userName, this.password)
    .subscribe((data: Token) => {
      console.log(data);
      this.token.access_token = data.access_token;
      this.token.refresh_token = data.refresh_token;
      this.token.token_type = data.token_type;
      this.token.scope = data.scope;
      this.token.expires_in = data.expires_in;

      localStorage.setItem('accessToken', this.token.access_token);
      localStorage.setItem('userName', this.userName);
      localStorage.setItem('password', this.password);

    //  this.token = data;
      console.log(this.token);
      console.log(this.token.access_token);
      this.tokenService.accessToken = this.token.access_token;
      console.log(this.token.access_token);
      console.log(this.tokenService.accessToken);

      this.tokenService.getTokenInfo(this.token.access_token)
      .subscribe((info: TokenInfo) => {
        this.tokenInfo.aud = info.aud;
        this.tokenInfo.user_name = info.user_name;
        this.tokenInfo.scope = info.scope;
        this.tokenInfo.active = info.active;
        this.tokenInfo.exp = info.exp;
        this.tokenInfo.authorities = info.authorities;
        this.tokenInfo.client_id = info.client_id;

        console.log(this.tokenInfo.authorities);

        if (this.tokenInfo.authorities.includes('ROLE_admin', 0)){
            console.log('ROLE_admin');
            this.tokenService.role = 'ROLE_admin';
            localStorage.setItem('role', 'ROLE_admin');
            console.log('localstorage : ', localStorage.getItem('role'));
        }
        else if (this.tokenInfo.authorities.includes('ROLE_officer', 0)){
          console.log('ROLE_officer');
          this.tokenService.role = 'ROLE_officer';
          localStorage.setItem('role', 'ROLE_officer');
        }
        else{
          console.log('ROLE_driver');
          this.tokenService.role = 'ROLE_driver';
          localStorage.setItem('role', 'ROLE_driver');
        }
    /*    // tslint:disable-next-line:prefer-for-of
        for (let i = 0; i < this.tokenInfo.authorities.length; i++){
          if(this.tokenInfo.authorities[i].)
        }

*/      this.toastr.success('Logged in successfully!!');
        location.href = '/home';
      //  this.router.navigateByUrl('/home');

      });

    },
    error => {
      console.log(error);
      // tslint:disable-next-line:prefer-const
      // alert('User is not authenticated');
      this.toastr.error('User not authenticated!! : ' + error.message, error.title);
      this.router.navigate(['/home']);
      }

    );
  }


// tslint:disable-next-line:typedef
signup(){
  this.router.navigate(['/signup']);
}

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  loginSubmit(loginform: NgForm){
console.log(loginform.value.nic);
console.log(loginform.value.password);
console.log(this.userName);
console.log(this.password);

  }

// tslint:disable-next-line:typedef
change(nic: NgModel){

  console.log(nic);
  console.log(this.userName);
}



}
