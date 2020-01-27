import { ShoppingCart } from './../model/shoppingcart.model';
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
  shoppingCart: ShoppingCart = new ShoppingCart();

  selectedPaymentType: PaymentType = new PaymentType();
  param;
  param1;

  price;
  priceBtc;
  btcValue = 0.0001308788;

  cartId: string;

  constructor(private router: Router, private payService: PayServiceService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    // this.param = this.route.snapshot.params.id;
    // this.param1 = this.route.snapshot.params.sellerId;
    // let request : PaymentTypeRequest = new PaymentTypeRequest(this.param1,this.param);
    // this.payService.getPaymentTypes(request).subscribe(data => {
    //   this.paymentTypeResponse = data;
    // })

    // this.payService.getPrice(this.route.snapshot.params.id).subscribe(data => {
    //   this.shoppingCart = data;
    //   console.log(data);
    //   this.price = this.shoppingCart.totalAmount;
    //   this.convertEuroToBtc(this.price);
    // })

    this.route.paramMap.subscribe(data => {
      const cartId = data.get('id');
      this.cartId = cartId;

      this.payService.getPaymentAndOrderData(+this.cartId).subscribe(data => {
        this.paymentTypeResponse = data.paymentTypes;
        this.shoppingCart = data.shoppingCart ;

        this.price = this.shoppingCart.totalAmount;
       this.convertEuroToBtc(this.price);
      });
    });

  }

  buy() {
    let payRequest: PayRequest = new PayRequest();
    payRequest.sellerId = this.paymentTypeResponse.sellerInfoDbId;
    payRequest.url = this.selectedPaymentType.paymentTypeHandlerUrl;
    console.log(payRequest);


    if(this.selectedPaymentType.paymentTypeName == "BITCOIN") {
      //alert("Usao u bitcoin");
      //bice neki convert u bitcoin
      payRequest.amount = this.priceBtc;
     
    } else if(this.selectedPaymentType.paymentTypeName == "PAYPALL") {
      //alert("Usao u pphandler");
      payRequest.amount = this.price;
    } else {
      //za placanje karticom
     // alert("Usao u cardPay");
      payRequest.amount = this.price;
    }

    //set OrderId iz Kp
    payRequest.orderId = +this.cartId;

    this.payService.buyMagazine(payRequest).subscribe(data => {
      if (this.selectedPaymentType.paymentTypeName == "PAYPALL") {
        // alert(1)
        // const tempResponse = data;
        // let url = `${payRequest.url}/execute`;
        // let executeRequest = {
        //   url: url,
        //   payerId: this.paymentTypeResponse.sellerInfoDbId,
        //   amount: payRequest.amount,
        //   paymentId: tempResponse.paymentId
        // }
        // this.payService.executePayment(executeRequest).toPromise().then(data => {
        //   console.log(data);
        // })
      } else {
        this.router.navigate(['/externalRedirect', { externalUrl: data.paymentUrl }], {
          skipLocationChange: true,
        });
      }
    }, error => console.log(error));
  }




    // 1€ => 0.0001308788 BTC
    convertEuroToBtc(amount) {
      this.priceBtc = amount * this.btcValue;
    }

}
