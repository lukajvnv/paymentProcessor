import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../service/payment.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  constructor(private payService: PaymentService) { }

  ngOnInit() {
  }

  click(){
    this.payService.testRedirect().subscribe( d => {

    }, err => {
      
    });
  }

}
