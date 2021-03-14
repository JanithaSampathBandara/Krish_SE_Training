import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
// import { AppComponent } from './app.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { HomeComponent } from './home/home.component';
import { UserSignupComponent } from './user-signup/user-signup.component';
import { OfficerComponent } from './officer/officer.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { OffenceComponent } from './offence/offence.component';
import { DriverComponent } from './driver/driver.component';
import { FineComponent } from './fine/fine.component';


const routes: Routes = [
  { path : 'login' , component : UserLoginComponent},
  { path : '' , component : HomeComponent},
  { path : 'signup' , component : UserSignupComponent},
  { path : 'officer' , component : OfficerComponent},
  { path : 'about-us' , component : AboutUsComponent},
  { path : 'home' , component : HomeComponent},
  { path : 'offence' , component : OffenceComponent},
  { path : 'driver' , component : DriverComponent},
  { path : 'fine' , component : FineComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
