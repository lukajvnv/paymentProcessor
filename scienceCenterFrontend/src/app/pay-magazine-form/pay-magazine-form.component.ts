import { Component, OnInit } from '@angular/core';
import { Magazine } from '../model/magazine.model';
import { PayService } from '../service/pay.service';
import { PaymentTypeRequest } from '../model/payment-type-request.model';
import { PaymentTypeResponse } from '../model/payment-type-response.model';
import { PaymentType } from '../model/payment-type.model';
import { PayRequest } from '../model/pay-request.model';
import { PayResponse } from '../model/pay-response.model';
import { Router } from '@angular/router';
import { ThrowStmt } from '@angular/compiler';

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
    console.log(payRequest);
    //alert("Sta je jebeni sellerID:??? " + payRequest.sellerId)

    //alert(this.selectedPaymentType.paymentTypeName);

    if(this.selectedPaymentType.paymentTypeName == "BITCOIN") {
      //alert("Usao u bitcoin");
      payRequest.amount = this.magazine.priceInBitcoin;
     
    } else if(this.selectedPaymentType.paymentTypeName == "PAYPALL") {
      //alert("Usao u pphandler");
      payRequest.amount = this.magazine.price;
    } else {
      //za placanje karticom
     // alert("Usao u cardPay");
      payRequest.amount = this.magazine.price;
    }
    
    this.payService.buyMagazine(payRequest).subscribe(data => {
      console.log(data);
      this.payResponse = data;
      console.log(this.payResponse.paymentUrl)
      window.location.href =data.paymentUrl as string;
      // if (this.selectedPaymentType.paymentTypeName == "PAYPALL") {
      //   alert(1)
      //   const tempResponse = data;
      //   let url = `${payRequest.url}/execute`;
      //   let executeRequest = {
      //     url: url,
      //     payerId: this.paymentTypeResponse.sellerInfoDbId,
      //     amount: payRequest.amount,
      //     paymentId: tempResponse.paymentId
      //   }
      //   this.payService.executePayment(executeRequest).toPromise().then(data => {
      //     console.log(data);
      //   })
      // } else {
      //   // this.router.navigate(['/externalRedirect', { externalUrl: this.payResponse.paymentUrl }], {
        // skipLocationChange: true,
        // });
      // }
      console.log('uspeh');
    }, error => console.log(error));

  
  }

}
