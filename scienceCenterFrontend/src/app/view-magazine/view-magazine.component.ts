import { Cart, ShoppingCart } from './../model/shoppingcart.model';
import { PayResponse } from './../model/pay-response.model';
import { PaymentTypeResponse } from './../model/payment-type-response.model';
import { PayService } from './../service/pay.service';
import { PayRequest } from './../model/pay-request.model';
import { TestService } from './../services/test.service';
import { Magazine, WayOfPayment } from './../model/magazine.model';
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

   shoppingcart : Cart = new Cart();

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

   // this.shoppingcart = new Cart();

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

  mag;
  sellerId;
  addToCart(issn: string) {
    
    this.findByIssn(issn);
    this.shoppingcart.magazines.push(this.mag);
    this.shoppingcart.totalAmount += this.mag.price;
    this.sellerId = this.mag.sellerId;

  }

  findByIssn(issn: string) {
    this.magazines.forEach(element => {
      if(element.issn == issn) {
        this.mag = new Magazine(element.id, element.issn, element.name, element.wayOfPayment, element.sellerIdentifier, element.price);
      }
    });

  }

  removeFromCart(issn: string) {

    for( let i = 0; i < this.shoppingcart.magazines.length; i++) {
      if(this.shoppingcart.magazines[i].issn == issn) {
        this.shoppingcart.totalAmount -= this.shoppingcart.magazines[i].price;
        this.shoppingcart.magazines.splice(i,1);
        break;
      }
    }

  
  }

  checkout() {
    if(this.shoppingcart.magazines.length != 0 ) {
      //alert("seller ID: " + this.sellerId);
      //ako je prazna lista magazina, ne moze da se checkout-uje

      

      let payRequest: PayRequest = new PayRequest();
      //payRequest.sellerId = this.sellerId;
      payRequest.amount = this.shoppingcart.totalAmount;
      payRequest.url = "https://localhost:8762/requestHandler/request/paymentTypes";


      for(let m of this.shoppingcart.magazines) {
        payRequest.sellerId = m.sellerIdentifier;
        console.log(m);
        break;
      }
      //alert("seller ID: " + payRequest.sellerId);
      //prvo cemo da sacuvamo shopping cart pa cemo ponuditi kp
      let shoppingCartTemp: ShoppingCart = new ShoppingCart();
      shoppingCartTemp.totalAmount = this.shoppingcart.totalAmount;
      shoppingCartTemp.sellerId = payRequest.sellerId;
      shoppingCartTemp.url = "https://localhost:8762/requestHandler/request/save";
      this.payService.executePayment(shoppingCartTemp).subscribe(data => {
        console.log(data);
      });
      //mozda ipak necemo
      //ili cemo samo cuvati na science, daaaaa!!! IQ 200

      //metoda za redirekciju na ponudjaca placanja
      this.payService.buyMagazine(payRequest).subscribe(data => {
        this.payResponse = data;
      
        this.router.navigate(['/externalRedirect', { externalUrl: this.payResponse.url }], {
        skipLocationChange: true,
      }) 
      })
      
   }
  }

}
