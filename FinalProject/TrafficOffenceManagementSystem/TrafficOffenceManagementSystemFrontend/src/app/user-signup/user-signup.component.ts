import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgModel } from '@angular/forms';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

 // users: User[] = [];

  user = {
    name : '',
    nic: '',
    contact: '',
    password: ''
};

valid = false;

validation = {
  name: true,
  nic: true,
  contact: true,
  password: true,
  cnpassword: true
};

role = '';

  constructor() { }

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
  }

  // tslint:disable-next-line:typedef
  onSubmit(userForm: NgForm){
    console.log(userForm);

    // tslint:disable-next-line:max-line-length
  //  if (document.getElementById('nicid').classList.contains('is-valid') && document.getElementById('nameid').classList.contains('is-valid')){

  //  }
    console.log(userForm.value.name);

   // new User(userForm.value.name, userForm.value.nic, userForm.value.contact,userForm.value.password);
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

