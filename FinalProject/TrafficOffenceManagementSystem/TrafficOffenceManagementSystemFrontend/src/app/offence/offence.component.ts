import { Component, OnInit } from '@angular/core';
import { OffenceService } from '../service/offence.service';
import { NgForm } from '@angular/forms';
import { NgModel } from '@angular/forms';
import { Offence } from '../model/offence';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-offence',
  templateUrl: './offence.component.html',
  styleUrls: ['./offence.component.css']
})
export class OffenceComponent implements OnInit {

  public offence: Offence = new Offence();


  constructor(private offenceService: OffenceService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  onSubmit(offenceForm: NgForm){
    console.log(offenceForm);
  //  offenceForm.reset();
  }

  // tslint:disable-next-line:typedef
  getOffenceById(offenceId: number){

    this.offenceService.getOffenceById(this.offence.offenceId).subscribe((data: Offence) => {
      console.log('data', data);
      this.offence = data;
      if (this.offence.fine != null){
        console.log('response', this.offence);
        this.router.navigateByUrl('/offence');
      }
      else{
        this.toastr.warning('No offence found for this ID!!');
      }
    },
    error => {
      this.toastr.error('Something went wrong!! : ' + error.message, error.title);
    });
  }

  // tslint:disable-next-line:typedef
  createOffence(){

  console.log('response', this.offence);
  this.offenceService.createOffence(this.offence).subscribe((data: Offence) => {
    console.log('response', data);
    this.toastr.success('Offence created successfully!!');
    this.router.navigateByUrl('/offence');

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
updateOffence(offenceId: number, offence: Offence){
  this.offenceService.updateOffence(offenceId, offence).subscribe((data: Offence) => {
    console.log('response', data);
    this.toastr.success('Offence updated successfully!!');
    this.router.navigateByUrl('/offence');

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

  // tslint:disable-next-line:typedef
deleteOffence(offenceId: number){
  if (confirm('Do you really want to delete this offence?')){
    this.offenceService.deleteOffence(offenceId).subscribe((data) => {
      console.log('response', data);
      this.toastr.success('Offence deleted successfully!!');
      this.router.navigateByUrl('/offence');
    },
    error => {
      this.toastr.error('Something went wrong!! : ' + error.message, error.title);
    });
  }
}

}
