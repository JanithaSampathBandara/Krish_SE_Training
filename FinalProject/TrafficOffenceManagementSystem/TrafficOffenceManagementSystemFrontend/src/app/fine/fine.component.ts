import { Component, OnInit } from '@angular/core';
import { Fine } from '../model/fine';
import { FineResponse } from '../model/fine-response';
import { FineService } from '../service/fine.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { DriverService } from '../service/driver.service';
import { Driver } from '../model/driver';
import { DriverResponse } from '../model/driver-response';
import { Offence } from '../model/offence';
import { OffenceService } from '../service/offence.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { TokenService } from '../service/token.service';
import { PaymentService } from '../service/payment.service';
import { DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-fine',
  templateUrl: './fine.component.html',
  styleUrls: ['./fine.component.css']
})
export class FineComponent implements OnInit {

  // tslint:disable-next-line:new-parens
  public fineResponse: FineResponse = new FineResponse(0, 'Initial', new Array<Fine>());
  public fine: Fine = new Fine();
  public driverResponse: DriverResponse = new DriverResponse(0, 'Initial', new Driver());
  public driver: Driver = new Driver();
  public fines: Array<Fine> = [];
  public offence: Offence = new Offence();
  public fineupdate: Fine = new Fine();
  offenceName = 'hello';
  amount = null;
  private baseUrl = 'http://localhost:8989/services/offences'; // 8191
  headersForApi = null;
  driverFound = false;
  fineFound = false;
  option = null;
  role = localStorage.getItem('role');
  update = null;
  fineNames = [];
  today = new Date();
  datepipe: DatePipe = new DatePipe('en-US');
  ctoday = this.datepipe.transform(this.today, 'yyyy-MM-dd');
  paymentNotAvailable: boolean;
 // sel = document.getElementById('stat').selectedIndex = "2";
//  public selectedFine: Fine = new Fine();
  // tslint:disable-next-line:max-line-length
  constructor(private fineService: FineService, private router: Router, private driverService: DriverService, private offenceService: OffenceService, private tokenService: TokenService, private http: HttpClient, private paymentService: PaymentService, private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  info(selectedFine){
    alert(selectedFine);
    this.fine.court = selectedFine.court;
  //  this.fine.fineId = selectedFine.fineId;
    this.update = selectedFine.fineId;
    this.fine.fineId = selectedFine.fineId;
    this.fine.licenseNo = selectedFine.licenseNo;
    this.fine.nic = selectedFine.nic;
    this.fine.vehicleNo = selectedFine.vehicleNo;
    alert(this.fine.vehicleNo);
    this.fine.dateAndTimeOfOffence = selectedFine.dateAndTimeOfOffence;
    this.fine.place = selectedFine.place;
    this.fine.validFrom = selectedFine.validFrom;
    this.fine.validTo = selectedFine.validTo;
    this.fine.court = selectedFine.court;
    this.fine.courtDate = selectedFine.courtDate;
    this.fine.policeStation = selectedFine.policeStation;
    this.fine.issuingOfficer = selectedFine.issuingOfficer;
    this.fine.status = selectedFine.status;
    this.fine.offenceId = selectedFine.offenceId;
    if (this.fine.status === 'not-paid'){
      this.option = 2;
    }
     // return this.option;
     else{
       this.option = 1;
     }
  //  document.getElementById('mySelect').nodeValue = this.fine.status;
  }

  // tslint:disable-next-line:typedef
  setOption(){
   // alert(this.option);
   if (this.fine.status === 'not-paid'){
     this.option = 1;
   }
    // return this.option;
    else{
      this.option = 2;
    }
  }

// tslint:disable-next-line:typedef
onSubmit(fineForm: NgForm){
  console.log(fineForm);
 // fineForm.reset();

}




  // tslint:disable-next-line:typedef
createFine(){
    this.fine.status = 'not-paid';
    this.fine.fineId = null;
  //  this.fine.licenseNo = this.driver.licenseNo;
    this.fineService.createFine(this.fine).subscribe((data: Fine) => {
    console.log('response', data);
    this.getDriverByLicense(this.driver.licenseNo);

    this.reset();
    this.toastr.success('Fine created successfully!!');
    this.router.navigateByUrl('/fine');
  //  this.userForm.reset();

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
getDriverByLicense(licenseNo: string){

  console.log('driver', this.driver);
  console.log('licn', licenseNo);
  this.driverService.getDriverByLicense(licenseNo).subscribe((data: DriverResponse) => {
    console.log(data);

    this.driverResponse = data;

    if (this.driverResponse.statusCode === -99){
      this.driverService.getDriverByNic(this.fine.licenseNo).subscribe((nicdata: DriverResponse) => {
        console.log(nicdata);
        this.driverResponse = nicdata;
        if (this.driverResponse.statusCode === -99){
          this.toastr.warning('No driver found for this ID!!');
          // alert('No driver found for this ID!!');
          this.driverFound = false;
          this.fineFound = false;
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
          this.fine.nic = this.driverResponse.data.nic;
          this.driverFound = true;

          this.fineService.getFineByLicenseNo(this.driver.licenseNo).subscribe((finedata: FineResponse) => {
            this.reset(); // remove
            if (finedata.statusCode === -99){
              this.fineFound = false;
              this.toastr.warning('No fines found for this license number!!');
              // alert('No fines found for this license number');
            }
            else{
              this.fineFound = true;
              console.log(data);
              this.fineResponse = finedata;
              console.log(this.fineResponse);
              this.fines = this.fineResponse.data;
              console.log(this.fines);
              console.log(this.fines[0]);
              this.getAmountToPay(this.driver.licenseNo);
            }

        //  console.log(this.getOffenceById(this.fines[0].offenceId));
          // tslint:disable-next-line:prefer-for-of
       //   for (let i = 0; i < this.fines.length; i++){
          //  console.log(i);
       //     console.log('for', this.getOffenceById(this.fines[i].offenceId));
       //   }
        /*  this.driver.licenseNo = this.driverResponse.data.licenseNo;
          this.driver.name = this.driverResponse.data.name;
          this.driver.address = this.driverResponse.data.address;
          this.driver.nic = this.driverResponse.data.nic;
          this.driver.phone = this.driverResponse.data.phone;
          this.driver.gender = this.driverResponse.data.gender;
          this.driver.dob = this.driverResponse.data.dob;
          this.driver.password = this.driverResponse.data.password;
      */
        //  this.router.navigateByUrl('/driver');
        //  console.log(this.response.data.length);
        },
        error => {
          this.toastr.error('Something went wrong!! : ' + error.message, error.title);
        });

          this.router.navigateByUrl('/fine');
        }
      },
      error => {
        this.toastr.error('Something went wrong!! : ' + error.message, error.title);
      });

    //  alert('No driver found for this license number');
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
      this.fine.nic = this.driverResponse.data.nic;
      this.driverFound = true;

    //  this.getFineByLicenseNo(this.driver.licenseNo);

      this.fineService.getFineByLicenseNo(this.driver.licenseNo).subscribe((finedata: FineResponse) => {
        this.reset(); // remove
        if (finedata.statusCode === -99){
          this.fineFound = false;
          this.toastr.warning('No fines found for this license number!!');
         // alert('No fines found for this license number');
        }
        else{
          this.fineFound = true;
          console.log(data);
          this.fineResponse = finedata;
          console.log(this.fineResponse);
          this.fines = this.fineResponse.data;
          this.populatOffences();
          console.log(this.fines);
          console.log(this.fines[0]);
          this.getAmountToPay(this.driver.licenseNo);
        }

    //  console.log(this.getOffenceById(this.fines[0].offenceId));
      // tslint:disable-next-line:prefer-for-of
   //   for (let i = 0; i < this.fines.length; i++){
      //  console.log(i);
   //     console.log('for', this.getOffenceById(this.fines[i].offenceId));
   //   }
    /*  this.driver.licenseNo = this.driverResponse.data.licenseNo;
      this.driver.name = this.driverResponse.data.name;
      this.driver.address = this.driverResponse.data.address;
      this.driver.nic = this.driverResponse.data.nic;
      this.driver.phone = this.driverResponse.data.phone;
      this.driver.gender = this.driverResponse.data.gender;
      this.driver.dob = this.driverResponse.data.dob;
      this.driver.password = this.driverResponse.data.password;
  */
    //  this.router.navigateByUrl('/driver');
    //  console.log(this.response.data.length);
    },
    error => {
      this.toastr.error('Something went wrong!! : ' + error.message, error.title);
    });

    //  console.log(this.fines[0].offenceId);
    //  console.log('method', this.getOffenceById(this.fines[0].offenceId));

    //  this.router.navigateByUrl('/driver');
    //  console.log(this.response.data.length);
    }

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  }/*,
  error => {
    console.log(error);
    // tslint:disable-next-line:prefer-const
    alert('No driver for this license number');
    this.router.navigate(['/fine']);
    }*/);
}


// tslint:disable-next-line:typedef
reset(){

    this.fine.vehicleNo = '';
    this.fine.dateAndTimeOfOffence = null;
    this.fine.place = '';
    this.fine.offenceId = null;
    this.fine.validFrom = null;
    this.fine.validTo = null;
    this.fine.court = '';
    this.fine.courtDate = null;
    this.fine.policeStation = '';
    this.fine.issuingOfficer = null;
}


// tslint:disable-next-line:typedef
updateFine(fineId: number, fine: Fine){
  this.fineService.updateFine(fineId, fine).subscribe((data: Fine) => {
    console.log('response', data);
    this.getDriverByLicense(fine.licenseNo);
    this.reset();
    this.toastr.success('Fine updated successfully!!');
   // location.href = '/fine';

  },
  error => {
    this.toastr.error('Something went wrong!! : ' + error.message, error.title);
  });
}

// tslint:disable-next-line:typedef
updateFineStatus(licenseNo: number, fine: Fine){
  /*
  fine.status = 'paid';
  this.fine.fineId = null;
  this.fine.nic = null;
  this.fine.vehicleNo = null;
  this.fine.dateAndTimeOfOffence = null;
  this.fine.place = null;
  this.fine.offenceId = null;
  this.fine.validFrom = null;
  this.fine.validTo = null;
  this.fine.court = null;
  this.fine.courtDate = null;
  this.fine.policeStation = null;
  this.fine.issuingOfficer = null;
*/
  // fine = undefined;
  this.fineupdate.status = 'paid';

  this.fineService.updateFineStatus(licenseNo, this.fineupdate).subscribe((data: Fine) => {
    console.log('response', data);
    this.getDriverByLicense(fine.licenseNo);
   // location.href = '/fine';

  });
}

// tslint:disable-next-line:typedef
pay(licenseNo, fine){
  this.updateFineStatus(licenseNo, fine);
  alert('Payment Succeeded');
}

/*
// tslint:disable-next-line:typedef
updateFine(selectedFine){
    console.log(this.fine.fineId);
    console.log(this.update);
    this.fine.fineId = this.update;
    console.log('statusssss', this.fine.status);
    this.fineService.createFine(this.fine).subscribe((data: Fine) => {
    console.log('response', data);
  //  this.getDriverByLicense(this.driver.licenseNo);
    this.router.navigateByUrl('/fine');

  });
}
*/

// tslint:disable-next-line:typedef
getAmountToPay(licenseNo: string){
  this.paymentService.getTotalAmount(licenseNo).subscribe((data: number) => {

  if (data === -99 ){
    this.paymentNotAvailable = true;
  }
  else{
    this.amount = data;
  }
  console.log(this.amount);
  });
}



// start get offence name

// tslint:disable-next-line:typedef
setHeaders(){
  this.headersForApi = new HttpHeaders({
    // tslint:disable-next-line:object-literal-key-quotes
    'Authorization': `Bearer ${this.tokenService.accessToken}`
    });
}

// tslint:disable-next-line:typedef
public getOffenceById(offenceId){
  this.setHeaders();
  return this.http.get(`${this.baseUrl}/${offenceId}`, { headers: this.headersForApi });
}

// tslint:disable-next-line:typedef
async getOffence(offenceId: number){

  this.getOffenceById(offenceId).subscribe((data: Offence) => {
    console.log('x');
   // alert('x');
    console.log(data.offence);
    return data.offence;
   // alert(data.offence);
  //  console.log('data', data);
   // this.offence = data;
 //   console.log('response', this.offence);
   // this.router.navigateByUrl('/offence');
  });
}

// tslint:disable-next-line:typedef
populatOffences(){
  // tslint:disable-next-line:prefer-for-of
  for (let i = 0; i < this.fines.length; i++ ){
    // tslint:disable-next-line:prefer-const
    let id = this.fines[i].offenceId;

    this.getOffenceById(id).subscribe((data: Offence) => {
      console.log('x');
     // alert('x');
      console.log(data.offence);
      this.fineNames[i] = data.offence;
     // return data.offence;
     // alert(data.offence);
    //  console.log('data', data);
     // this.offence = data;
   //   console.log('response', this.offence);
     // this.router.navigateByUrl('/offence');
    });

  }
  console.log(this.fineNames);
}

// end of get offence name

/*
// tslint:disable-next-line:typedef
increment(offenceId){
  // return offenceId + 100;
  this.offenceService.getOffenceById(offenceId).subscribe((data: Offence) => {
    this.offence.offenceId = data.offenceId;
    this.offence.offence = data.offence;
    this.offence.fine = data.fine;
    console.log('x');
  //  console.log('offence fine', this.offence.fine);

   // return 'in';
    // return this.offence.fine;

  });
  return 'out';
}
*/
/*


// tslint:disable-next-line:typedef
getFineByLicenseNoNew(licenseNo){

  this.fineService.getFineByLicenseNo(licenseNo).subscribe((data: FineResponse) => {
    console.log(data);
    this.fineResponse = data;
    console.log(this.fineResponse);
    this.fines = this.fineResponse.data;
    console.log(this.fines);
    console.log(this.fines[0]);
  //  console.log(this.getOffenceById(this.fines[0].offenceId));
    // tslint:disable-next-line:prefer-for-of
 //   for (let i = 0; i < this.fines.length; i++){
    //  console.log(i);
 //     console.log('for', this.getOffenceById(this.fines[i].offenceId));
 //   }
    this.driver.licenseNo = this.driverResponse.data.licenseNo;
    this.driver.name = this.driverResponse.data.name;
    this.driver.address = this.driverResponse.data.address;
    this.driver.nic = this.driverResponse.data.nic;
    this.driver.phone = this.driverResponse.data.phone;
    this.driver.gender = this.driverResponse.data.gender;
    this.driver.dob = this.driverResponse.data.dob;
    this.driver.password = this.driverResponse.data.password;

  //  this.router.navigateByUrl('/driver');
  //  console.log(this.response.data.length);
  });
}


*/

// tslint:disable-next-line:typedef
hello(){
  return this.offenceName;
}

/*
// tslint:disable-next-line:typedef
getOffenceById(offenceId: number) {

  let reason = '';
 // return offenceId;
  this.offenceService.getOffenceById(offenceId).subscribe((data: Offence) => {
  //  console.log('data', data);
  this.offence.offenceId = data.offenceId;
  this.offence.offence = data.offence;
  this.offence.fine = data.fine;
  console.log(this.offence.offence);
  reason = this.offence.offence;
  return reason;
 // this.offenceName = this.offence.offence;
 // localStorage.setItem('offence', this.offence.offence);
  //  console.log('response', this.offence);
 // console.log('offenceq', this.offence.offence);
  //  return 'offence' + this.offence.offence;
 // return 'offence' + this.offence.offence;
  });
 // console.log('a', this.offence.offence);

}
*/
}

