import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
val = 0;
  constructor() {
  /*  if (this.val === 0){
    //  location.reload();
      this.val++;
    }
*/
  }

  ngOnInit(): void {

   // location.reload();
  }

}
