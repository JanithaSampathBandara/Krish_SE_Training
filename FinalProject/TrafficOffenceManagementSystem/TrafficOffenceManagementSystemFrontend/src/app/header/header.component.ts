import { Component, OnInit, HostListener } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})


export class HeaderComponent implements OnInit {

  userName = localStorage.getItem('userName');

  // role = localStorage.getItem('role');
  role = '';

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
   // this.role = this.role.replace('ROLE_', '');

  }

  // tslint:disable-next-line:typedef
  Login(){
    this.router.navigate(['/login']);
  }

  // tslint:disable-next-line:typedef
  Signup(){
    this.router.navigate(['/signup']);
  }

   // tslint:disable-next-line:typedef
   click(){
    console.log('janz');
    const name = document.getElementsByTagName('h1');
    console.log(name);
}

// tslint:disable-next-line:typedef
logout(){
  console.log('logging out');
  localStorage.removeItem('role');
  localStorage.removeItem('accessToken');
  localStorage.removeItem('userName');
  localStorage.removeItem('password');
  localStorage.clear();
  this.router.navigate(['/home']);
}

  // tslint:disable-next-line:typedef
  @HostListener('document:scroll') onScrollEvent(){
  //  console.log($event);
  if (document.body.scrollTop > 480 || document.documentElement.scrollTop > 480){
    console.log('s');
    document.getElementById('janznav').classList.add('back');
  }
  else{
    document.getElementById('janznav').classList.remove('back');
  }
  console.log('scrolling');
  console.log(document.documentElement.scrollTop);
  console.log(document.body.scrollTop);
  }

}
