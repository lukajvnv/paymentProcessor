import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';
import { Cart, ShoppingCart } from '../model/shoppingcart.model';
import { TestService } from '../services/test.service';
import { PayService } from '../service/pay.service';
import { PayRequest } from '../model/pay-request.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  private cart;

  constructor(private tokenStorageService: TokenStorageService, private router: Router, private testService: TestService, private payService: PayService) { }

  ngOnInit() {
    let hasCart = this.tokenStorageService.hasCart();
    if(!hasCart){
      this.router.navigate(['error']);
    }else{
      let cart: Cart = this.tokenStorageService.getCart();

      this.testService.getCart(+cart.cartId).subscribe(data => {
        this.cart = data;
      });
    }
  }

  
  removeFromCart(item) {

  }

  checkout() {
      let shoppingCartTemp: Cart = new Cart();
      let cart: Cart = this.tokenStorageService.getCart();
     
      this.payService.executePayment(cart).subscribe(data => {
        this.tokenStorageService.removeCart();
        this.router.navigate(['/externalRedirect', { externalUrl: data.kpUrl }], {
          skipLocationChange: true,
            });
      
      });

     
      
      
   
  }

}
