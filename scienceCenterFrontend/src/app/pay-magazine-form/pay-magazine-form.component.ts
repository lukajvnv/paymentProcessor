import { Component, OnInit } from '@angular/core';
import { Magazine } from '../model/magazine.model';
import { PayService } from '../service/pay.service';
import { PaymentTypeRequest } from '../model/payment-type-request.model';
import { PaymentTypeResponse } from '../model/payment-type-response.model';
import { PaymentType } from '../model/payment-type.model';
import { PayRequest } from '../model/pay-request.model';
import { PayResponse } from '../model/pay-response.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pay-magazine-form',
  templateUrl: './pay-magazine-form.component.html',
  styleUrls: ['./pay-magazine-form.component.scss']
})
export class PayMagazineFormComponent implements OnInit {

  magazine: Magazine;

  paymentTypeResponse: PaymentTypeResponse = new PaymentTypeResponse();
  payResponse: PayResponse = new PayResponse();

  selectedPaymentType: PaymentType = new PaymentType();

  constructor(private payService: PayService, private router: Router) { }

  ngOnInit() {
    this.magazine = new Magazine(1, '4563-1235', 'Inzenjerski glasnik', 500, 0.0001);
    let request : PaymentTypeRequest = new PaymentTypeRequest(this.magazine.id);
    this.payService.getPaymentTypes(request).subscribe(data => {
      this.paymentTypeResponse = data;
      console.log('uspeh');
    }, (error: Response) => {
      console.log(error.text);
      
    });
    // this.payService.test().subscribe(data => {
    //   console.log('uspeh');

    // }, (error: Response) => {
    //   console.log(error.text);

    // });
  }

  buy() {
    let payRequest: PayRequest = new PayRequest();
    payRequest.sellerId = this.paymentTypeResponse.sellerInfoDbId;
    //payRequest.amount = this.magazine.price;
    payRequest.url = this.selectedPaymentType.paymentTypeHandlerUrl;

    //alert("Sta je jebeni sellerID:??? " + payRequest.sellerId)

    //alert(this.selectedPaymentType.paymentTypeName);

    if(this.selectedPaymentType.paymentTypeName == "BITCOIN") {
      //alert("Usao u bitcoin");
      payRequest.amount = this.magazine.priceInBitcoin;
     
    } else if(this.selectedPaymentType.paymentTypeName == "PAYPALL") {
      //alert("Usao u pphandler");
    } else {
      //za placanje karticom
     // alert("Usao u cardPay");
      payRequest.amount = this.magazine.price;
    }

    this.payService.buyMagazine(payRequest).subscribe(data => {
      this.payResponse = data;
      
      this.router.navigate(['/externalRedirect', { externalUrl: this.payResponse.paymentUrl }], {
      skipLocationChange: true,
    });

      console.log('uspeh');
    }, (error: Response) => {
      console.log(error.text);
      
    });
    

    
  }

}
