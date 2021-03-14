import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Driver } from '../model/driver';
import { DriverResponse } from '../model/driver-response';
import { DriverService } from '../service/driver.service';
import { User } from 'src/app/model/user';
import { UserService } from '../service/user.service';
import { DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-driver',
  templateUrl: './driver.component.html',
  styleUrls: ['./driver.component.css']
})
export class DriverComponent implements OnInit {

  public driverResponse: DriverResponse = new DriverResponse(0, 'Initial', new Driver());
  public driver: Driver = new Driver();
  public user: User = new User();
  role = 3;

  a = 'a';
  b = 'b';
  val = (this.a === this.b);
  gender: any;
  option: any;
  today = new Date();
  datepipe: DatePipe = new DatePipe('en-US');
  ctoday = this.datepipe.transform(this.today, 'yyyy-MM-dd');
  // tslint:disable-next-line:typedef

  // tslint:disable-next-line:max-line-length
  constructor(private driverService: DriverService, private router: Router, private userService: UserService, private toastr: ToastrService ) { }

  ngOnInit(): void {

  }

 // tslint:disable-next-line:align
 // tslint:disable-next-line:typedef
 // if(role == 'ROLE_admin')

  // tslint:disable-next-line:typedef
  onSubmit(driverForm: NgForm){
    console.log(driverForm);
  //  driverForm.reset();

}

// tslint:disable-next-line:typedef
createDriver(){

  if (this.gender === '1'){
    console.log('janz');
    this.driver.gender = 'Male';
  }
  if ((this.gender) === '2'){
    console.log('lopezz');
    this.driver.gender = 'Female';
  }
  if ((this.gender) === '3'){
    console.log('bandara');
    this.driver.gender = 'Other';
  }

  this.driverService.createDriver(this.driver).subscribe((data: Driver) => {
    console.log('response', data);


    // create user
    this.user.username = this.driver.name;
    this.user.password = this.driver.password;
    this.user.email = this.driver.email;
    this.user.enabled = true;
    this.user.accountNonExpired = true;
    this.user.credentialsNonExpired = true;
    this.user.accountNonLocked = true;

    console.log('User Data', this.user);

    this.userService.createUser(this.user, this.role).subscribe((userdata) => {
      console.log('User response : ', userdata);
      this.toastr.success('Driver Registered Successfully', 'Driver Registration');
    },
    error => {
      this.toastr.error(error.message, error.title);
    });
   // this.reset();
    this.router.navigateByUrl('/driver');
  //  location.href = '/driver';

  },
  error => {
    this.toastr.error(error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
getDriverByLicense(driverId: string){
  this.driverService.getDriverByLicense(this.driver.licenseNo).subscribe((data: DriverResponse) => {
    console.log(data);
    this.driverResponse = data;

    if (this.driverResponse.statusCode === -99){
      this.driverService.getDriverByNic(this.driver.licenseNo).subscribe((nicdata: DriverResponse) => {
        console.log(nicdata);
        this.driverResponse = nicdata;
        if (this.driverResponse.statusCode === -99){
          this.toastr.warning('No driver found for this ID!!');
        }
        else{
          this.driver.licenseNo = this.driverResponse.data.licenseNo;
          this.driver.name = this.driverResponse.data.name;
          this.driver.address = this.driverResponse.data.address;
          this.driver.nic = this.driverResponse.data.nic;
          this.driver.phone = this.driverResponse.data.phone;
          this.driver.gender = this.driverResponse.data.gender;
          this.driver.email = this.driverResponse.data.email;

          if (this.driver.gender === 'Male'){
            this.gender = 1;
          }
          else if (this.driver.gender === 'Female'){
            this.gender = 2;
          }
          else{
            this.gender = 3;
          }
          this.driver.dob = this.driverResponse.data.dob;
          this.driver.password = this.driverResponse.data.password;

          this.router.navigateByUrl('/driver');
        }
      },
      error => {
        this.toastr.error('Something went wrong!! : ' + error.message, error.title);
      });
    }
    else{
    this.driver.licenseNo = this.driverResponse.data.licenseNo;
    this.driver.name = this.driverResponse.data.name;
    this.driver.address = this.driverResponse.data.address;
    this.driver.nic = this.driverResponse.data.nic;
    this.driver.phone = this.driverResponse.data.phone;
    this.driver.gender = this.driverResponse.data.gender;
    this.driver.email = this.driverResponse.data.email;

    if (this.driver.gender === 'Male'){
      this.gender = 1;
    }
    else if (this.driver.gender === 'Female'){
      this.gender = 2;
    }
    else{
      this.gender = 3;
    }

    this.driver.dob = this.driverResponse.data.dob;
    this.driver.password = this.driverResponse.data.password;

    this.router.navigateByUrl('/driver');
    }
  //  console.log(this.response.data.length);
  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
updateDriver(driverId: string, driver: Driver){
    if (this.gender === '1'){
      console.log('janz');
      driver.gender = 'Male';
    }
    if ((this.gender) === '2'){
      console.log('lopezz');
      driver.gender = 'Female';
    }
    if ((this.gender) === '3'){
      console.log('bandara');
      driver.gender = 'Other';
    }
    this.driverService.updateDriver(driverId, driver).subscribe((data: DriverResponse) => {
    console.log('response', data);

    this.driverResponse = data;
    if (this.driverResponse.statusCode === -99){
      this.toastr.warning('Driver not updated!!');
    }
    else{

      // update user
      this.user.username = this.driver.name;
      this.user.password = this.driver.password;
      this.user.email = this.driver.email;

      this.userService.updateUser(this.user).subscribe((userdata) => {
      console.log('User response : ', userdata);
      this.toastr.success('Driver updated successfully!!');
    },
    error => {
      this.toastr.error('Something went wrong!! : ' + error.message, error.title);
    });

      this.router.navigateByUrl('/driver');
    }

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
deleteDriver(driverId: string){
  if (confirm('Do you really want to delete this driver?')){

    this.driverService.deleteDriver(driverId).subscribe((data) => {
      console.log('response', data);

      this.userService.deleteUser(this.driver.name).subscribe((userdata) => {
        console.log('User response : ', userdata);
        this.toastr.success('Driver deleted successfully!!');
      });

     // this.reset();
      this.router.navigateByUrl('/driver');

    });
  }

}

// tslint:disable-next-line:typedef
getDriverByNic(nic: string){
  this.driverService.getDriverByNic(this.driver.licenseNo).subscribe((data: DriverResponse) => {
    console.log(data);
    this.driverResponse = data;

    if (this.driverResponse.statusCode === -99){
      this.toastr.warning('No driver found!!');
    }
    else{

      this.driver.licenseNo = this.driverResponse.data.licenseNo;
      this.driver.name = this.driverResponse.data.name;
      this.driver.address = this.driverResponse.data.address;
      this.driver.nic = this.driverResponse.data.nic;
      this.driver.phone = this.driverResponse.data.phone;
      this.driver.gender = this.driverResponse.data.gender;
      this.driver.dob = this.driverResponse.data.dob;
      this.driver.password = this.driverResponse.data.password;
      this.driver.email = this.driverResponse.data.email;

      this.router.navigateByUrl('/driver');
    //  console.log(this.response.data.length);
    }

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
reset(){
    this.driver.licenseNo = '';
    this.driver.name = '';
    this.driver.address = '';
    this.driver.nic = '';
    this.driver.phone = null;
    this.driver.gender = '';
    this.driver.dob = null;
    this.driver.password = '';
    this.driver.email = '';
}

}
