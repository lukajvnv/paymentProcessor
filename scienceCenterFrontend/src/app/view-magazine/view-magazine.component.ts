import { PayResponse } from './../model/pay-response.model';
import { PaymentTypeResponse } from './../model/payment-type-response.model';
import { PayService } from './../service/pay.service';
import { PayRequest } from './../model/pay-request.model';
import { TestService } from './../services/test.service';
import { Magazine } from './../model/magazine.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentType } from '../model/payment-type.model';


@Component({
  selector: 'app-view-magazine',
  templateUrl: './view-magazine.component.html',
  styleUrls: ['./view-magazine.component.scss']
})
export class ViewMagazineComponent implements OnInit {

   magazine: Magazine;
   magazines : Array<any>;

   paymentTypeResponse: PaymentTypeResponse = new PaymentTypeResponse();
  payResponse: PayResponse = new PayResponse();

  selectedPaymentType: PaymentType = new PaymentType();

   listMagazines: Magazine[] = [];

  constructor(private router: Router, 
    private testService: TestService,
    private payService: PayService) { }

  ngOnInit() {
    //this.magazine = new Magazine(1, '4563-1235', 'Inzenjerski glasnik', 500, 0.0001);
    
    this.testService.getAll().subscribe(data => {
      this.magazines = data;
      console.log('IDE GAS');
      console.log(this.magazines);
    })

  }

  buy(){
    this.router.navigate(['buy-magazine']);
  }
  registrate() {
    this.router.navigate(['new-magazine']);
  }

  buyMagazine(id:number,sellerId:number) {
    let payRequest: PayRequest = new PayRequest();
    payRequest.sellerId = sellerId;
    //payRequest.amount = this.magazine.price;
    //payRequest.url = this.selectedPaymentType.paymentTypeHandlerUrl;
    //na ovaj url treba da ide gas https://localhost:8762/requestHandler/request/paymentTypes

    payRequest.url = "https://localhost:8762/requestHandler/request/paymentTypes";

    this.payService.buyMagazine(payRequest).subscribe(data => {
      this.payResponse = data;
      
      this.router.navigate(['/externalRedirect', { externalUrl: this.payResponse.url }], {
      skipLocationChange: true,
    });

      console.log('uspeh');
    }, (error: Response) => {
      console.log(error.text);
      
    });

  }

}
