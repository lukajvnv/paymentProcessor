import { Component, OnInit } from '@angular/core';
import { Magazine } from '../model/magazine.model';
import { Cart, OrderId, ShoppingCart } from '../model/shoppingcart.model';
import { PaymentTypeResponse } from '../model/payment-type-response.model';
import { PayResponse } from '../model/pay-response.model';
import { PaymentType } from '../model/payment-type.model';
import { Router } from '@angular/router';
import { TestService } from '../services/test.service';
import { PayService } from '../service/pay.service';
import { PayRequest } from '../model/pay-request.model';

@Component({
  selector: 'app-view-all-magazines',
  templateUrl: './view-all-magazines.component.html',
  styleUrls: ['./view-all-magazines.component.scss']
})
export class ViewAllMagazinesComponent implements OnInit {

  magazine: Magazine;
   magazines : Array<any>;

   shoppingcart : Cart = new Cart();

   paymentTypeResponse: PaymentTypeResponse = new PaymentTypeResponse();
   payResponse: PayResponse = new PayResponse();

  selectedPaymentType: PaymentType = new PaymentType();
  orderIdDTO: OrderId = new OrderId();

   listMagazines: Magazine[] = [];

  constructor(private router: Router, 
    private testService: TestService,
    private payService: PayService) { }

  ngOnInit() {
    this.testService.getAll().subscribe(data => {
      this.magazines = data;
    })

  }

  buyMagazine(id:number,sellerId:number) {
    let payRequest: PayRequest = new PayRequest();
    payRequest.sellerId = sellerId;
    
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

  view(mag: Magazine) {
    
    this.router.navigate(['magazine', mag.magazineId]);

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
      
      let shoppingCartTemp: ShoppingCart = new ShoppingCart();
      shoppingCartTemp.totalAmount = this.shoppingcart.totalAmount;
      shoppingCartTemp.sellerId = payRequest.sellerId;
      shoppingCartTemp.url = "https://localhost:8762/requestHandler/request/save";
      this.payService.executePayment(shoppingCartTemp).subscribe(data => {
        
        this.orderIdDTO = data;
        payRequest.orderId = this.orderIdDTO.orderId;
        console.log(data);

       

        this.payService.buyMagazine(payRequest).subscribe(data => {
          this.payResponse = data;
        
          this.router.navigate(['/externalRedirect', { externalUrl: this.payResponse.url }], {
          skipLocationChange: true,
        }) 
        })
      });

     
      
      
   }
  }

}
