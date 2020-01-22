import { PayResponse } from './../model/pay-response.model';
import { PayRequest } from './../model/pay-request.model';
import { PaymentTypeResponse } from './../model/payment-type-response.model';
import { PaymentTypeRequest } from './../model/payment-type-request.model';
import { PayServiceService } from './../services/pay-service.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PaymentType } from '../model/payment-type.model';

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.scss']
})
export class PayComponent implements OnInit {

  paymentTypeResponse: PaymentTypeResponse = new PaymentTypeResponse();
  payResponse: PayResponse = new PayResponse();

  selectedPaymentType: PaymentType = new PaymentType();
  param;

  constructor(private router: Router, private payService: PayServiceService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.param = this.route.snapshot.params.id;
    let request : PaymentTypeRequest = new PaymentTypeRequest(this.param);
    //alert("Ide gas: " + request.sellerId);
    this.payService.getPaymentTypes(request).subscribe(data => {
      //alert("Ide gas");
      this.paymentTypeResponse = data;
      console.log('ide gas');
    })

  }

  // buy(){
  //   let payRequest: PayRequest = new PayRequest();
  //   payRequest.sellerId = this.paymentTypeResponse.sellerInfoDbId;
  //   // za karticu puca ako nema iznos
  //   payRequest.amount = 500;
  //   payRequest.url = this.selectedPaymentType.paymentTypeHandlerUrl;
  //   this.payService.buyMagazine(payRequest).subscribe(data => {
  //     this.payResponse = data;

  //     this.router.navigate(['/externalRedirect', { externalUrl: this.payResponse.paymentUrl }], {
  //       skipLocationChange: true,
  //     });

  //   }, err => {

  //   });
  // }

}
