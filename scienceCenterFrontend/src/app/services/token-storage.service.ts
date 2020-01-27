import { Injectable } from '@angular/core';
import { Cart } from '../model/shoppingcart.model';
import { TestService } from './test.service';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private testService: TestService) { }

  public getCart() : Cart{
    let cart = window.sessionStorage.getItem('cart');
    return JSON.parse(cart);
    
  }

  public hasCart() : any {
    let cart = window.sessionStorage.getItem('cart');
    return cart;    
  }

  public setCart(cart: Cart) {
    window.sessionStorage.setItem('cart', JSON.stringify(cart));
  }

  public addCart() : Cart {
    
    return new Cart();
  }

  public removeCart() {
    window.sessionStorage.removeItem('cart');
    
  }





  
}
