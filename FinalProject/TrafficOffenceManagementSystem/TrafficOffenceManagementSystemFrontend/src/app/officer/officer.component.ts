import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgModel } from '@angular/forms';
import { OfficerService } from '../service/officer.service';
import { Officer } from '../model/officer';
import { Response } from '../model/response';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-officer',
  templateUrl: './officer.component.html',
  styleUrls: ['./officer.component.css']
})
export class OfficerComponent implements OnInit {

// users: User[] = [];
/*
user = {
  name : '',
  nic: '',
  contact: '',
  password: ''
};
*/


valid = false;

validation = {
name: true,
nic: true,
contact: true,
password: true,
cnpassword: true
};

message: any;
gender: any;
// this.gender = 0;
private officers: Officer[];
// public officer: Officer;
// public response: Response = new Response(0, 'Initial', new Officer(0, '', '', '', 0, '', new Date(), ''));
public response: Response = new Response(0, 'Initial', new Officer());
// public officer: Officer =  new Officer();
// public officer: Officer = new Officer(0, '', '', '', 0, '', new Date(), '');
public officer: Officer = new Officer();
officerId: number;
public user: User = new User();
role = 2;

today = new Date();
datepipe: DatePipe = new DatePipe('en-US');
ctoday = this.datepipe.transform(this.today, 'yyyy-MM-dd');
/*
officer = {
  policeId: '',
    name: 'ss',
    address: '',
    nic: '',
    phone: '',
    gender: '',
    dob: '',
    password: ''
};*/
  // tslint:disable-next-line:max-line-length
  constructor(private officerService: OfficerService, private router: Router, private userService: UserService, private toastr: ToastrService ) { }

  ngOnInit(): void {
  /*  this.officerService.getOfficers().subscribe((data: Officer[]) => {
      console.log(data);
      this.officers = data;
    }); */
  }

  // tslint:disable-next-line:typedef
  onSubmit(officerForm: NgForm){
    console.log(officerForm);
 //   officerForm.reset();

}

// tslint:disable-next-line:typedef
getOfficers(){
  this.officerService.getOfficers().subscribe((data: Officer[]) => {
    console.log(data);
    this.officers = data;
  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
updateOfficer(policeId: number, officer: Officer){
    console.log(typeof(this.gender));
    if (this.gender === '1'){
      console.log('janz');
      officer.gender = 'Male';
    }
    if ((this.gender) === '2'){
      console.log('lopezz');
      officer.gender = 'Female';
    }
    if ((this.gender) === '3'){
      console.log('bandara');
      officer.gender = 'Other';
    }
    console.log(this.gender);
    console.log('gender', officer.gender);
    this.officerService.updateOfficer(policeId, officer).subscribe((data) => {
    console.log('response', data);

    // update user
    this.user.username = this.officer.name;
    this.user.password = this.officer.password;
    this.user.email = this.officer.email;

    this.userService.updateUser(this.user).subscribe((userdata) => {
      console.log('User response : ', userdata);
      this.toastr.success('Officer updated successfully!!');
    },
    error => {
      this.toastr.error('Something went wrong!! : ' + error.message, error.title);
    });

    this.router.navigateByUrl('/officer');

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
deleteOfficer(policeId: number){
  if (confirm('Do you really want to delete this officer?')){
    this.officerService.deleteOfficer(policeId).subscribe((data) => {
      console.log('response', data);
      this.userService.deleteUser(this.officer.name).subscribe((userdata) => {
        console.log('User response : ', userdata);
        this.toastr.success('Officer deleted successfully!!');
      });
      this.router.navigateByUrl('/officer');
    },
      error => {
        this.toastr.error('Something went wrong!! : ' + error.message, error.title);
      });
  }

}


/*
// tslint:disable-next-line:typedef
getOfficerById(officerId: number){
  this.officerService.getOfficer(officerId).subscribe((data: Officer) => {
    console.log(data);
    this.officer = data;
  //  console.log(this.officer.data.get(1).address);
  });
}
*/

// tslint:disable-next-line:typedef
getOfficerById(officerId: number){
  this.officerService.getOfficerById(this.officer.policeId).subscribe((data: Response) => {
    console.log(data);
    this.response = data;
    if (this.response.statusCode !== -99){
      this.officer.policeId = this.response.data.policeId;
      this.officer.name = this.response.data.name;
      this.officer.address = this.response.data.address;
      this.officer.gender = this.response.data.gender;
      if (this.officer.gender === 'Male'){
        this.gender = 1;
      }
      else if (this.officer.gender === 'Female'){
        this.gender = 2;
      }
      else{
        this.gender = 3;
      }
      this.officer.dob = this.response.data.dob;
      this.officer.password = this.response.data.password;
      this.officer.phone = this.response.data.phone;
      this.officer.nic = this.response.data.nic;
      this.officer.email = this.response.data.email;
    //  console.log(this.response);
    //  console.log(this.response.statusCode);
    //  console.log(this.response.message);
      console.log(this.response.data.address);
      this.router.navigateByUrl('/officer');
    //  console.log(this.response.data.length);
    }
    else{
      this.toastr.warning('No officer found for this id!!!');
    }
  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
createOfficer(){
  if (this.gender === '1'){
    console.log('janz');
    this.officer.gender = 'Male';
  }
  if ((this.gender) === '2'){
    console.log('lopezz');
    this.officer.gender = 'Female';
  }
  if ((this.gender) === '3'){
    console.log('bandara');
    this.officer.gender = 'Other';
  }
  console.log('response', this.officer);
  this.officerService.createOfficer(this.officer).subscribe((data: Officer) => {
    console.log('response', data);

    // create user
    this.user.username = this.officer.name;
    this.user.password = this.officer.password;
    this.user.email = this.officer.email;
    this.user.enabled = true;
    this.user.accountNonExpired = true;
    this.user.credentialsNonExpired = true;
    this.user.accountNonLocked = true;

    this.userService.createUser(this.user, this.role).subscribe((userdata) => {
      console.log('User response : ', userdata);
      this.toastr.success('Officer created successfully!!');
    });

    this.router.navigateByUrl('/officer');

  },
    error => {
      this.toastr.error('Something went wrong!! : ' + error.message, error.title);
    });
}

// tslint:disable-next-line:typedef
change(change: NgModel){
  console.log(change);
  if (change.valid){
    this.validation.name = true;
  }
  else if (change.invalid){
    this.validation.name = false;
  }

}

// tslint:disable-next-line:typedef
nicChange(nic: NgModel){
  /*
  if (nic.dirty){
    document.getElementById('nicid').classList.add('is-valid');
  }
  if (nic.control.value.length > 10){
    document.getElementById('nicid').classList.remove('is-valid');
    document.getElementById('nicid').classList.add('is-invalid');
  }
  */

// Validate NIC from the backend here
if (nic.valid){
  this.validation.nic = true;
}
else if (nic.invalid){
  this.validation.nic = false;
}

  }

  // tslint:disable-next-line:typedef
  contactChange(contact: NgModel){
    console.log(contact);

    if (contact.control.invalid && contact.dirty && contact.control.value.length > 0){
        this.validation.contact = false;
    }
    else
    {
      this.validation.contact = true;
    }

    }

    // tslint:disable-next-line:typedef
    passwordChange(password: NgModel){
      console.log(password);
      if (password.invalid){
        this.validation.password = false;
      }
      else{
        this.validation.password = true;
      }
    }

    // tslint:disable-next-line:typedef
    cnPassword(cnpassword: NgModel){
      console.log(cnpassword);

      if (cnpassword.value){
        this.validation.cnpassword = false;
      }
    }



}

