import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TestService } from '../services/test.service';
import { TokenStorageService } from '../services/token-storage.service';
import { Cart } from '../model/shoppingcart.model';
import { NewCartItemRequest } from '../model/shoppingcart-new-item-request.model';
import { BuyingType } from '../model/buying.type.enum';

@Component({
  selector: 'app-view-one-magazine-edition',
  templateUrl: './view-one-magazine-edition.component.html',
  styleUrls: ['./view-one-magazine-edition.component.scss']
})
export class ViewOneMagazineEditionComponent implements OnInit {

  private edition;
  private articles;

  constructor(private activatedRoute: ActivatedRoute, private testService: TestService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      const magId = data.get('id');

      this.testService.getEditionById(+magId).subscribe(data => {
        this.edition = data;
      });

      this.testService.getAllArticles(+magId).subscribe(data => {
        this.articles = data;
      });
    });
  }

  addToCart(article){
    const l = this.tokenStorageService.hasCart();
    if(!l){
      this.testService.createCart().subscribe(newCart => {
        this.tokenStorageService.setCart(newCart);
        const cart: Cart = this.tokenStorageService.getCart();
        let request = new NewCartItemRequest(cart.cartId, article.articleId);
        request.buyingType = BuyingType.ARTICLE;
        this.testService.addToCart(request).subscribe(data => {
    
        });
      });
    } else{
      const cart: Cart = this.tokenStorageService.getCart();
      let request = new NewCartItemRequest(cart.cartId, article.articleId);
      request.buyingType = BuyingType.ARTICLE;
      this.testService.addToCart(request).subscribe(data => {
  
      });
    }
    
  }

}
