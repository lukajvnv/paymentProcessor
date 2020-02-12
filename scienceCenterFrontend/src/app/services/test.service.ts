import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewCartItemRequest } from '../model/shoppingcart-new-item-request.model';


@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  test(): Observable<any> {
    alert("USO! 2");
    return this.http.get('https://localhost:8836/test');
  }

  getAll(): Observable<any> {
    return this.http.get('https://localhost:8836/test/getAll');
  }

  getById(id: number): Observable<any> {
    return this.http.get('https://localhost:8836/test/getOne/' + id);
  }

  getAllEditions(id: number): Observable<any> {
    return this.http.get('https://localhost:8836/test/getEditions/'+ id);
  }

  getEditionById(id: number): Observable<any> {
    return this.http.get('https://localhost:8836/test/getOneEdition/'+ id);
  }

  getAllArticles(id: number): Observable<any> {
    return this.http.get('https://localhost:8836/test/getArticles/'+ id);
  }

  addToCart(request: NewCartItemRequest) : Observable<any> {
    return this.http.post('https://localhost:8836/test/addItemToCart', request);
  }

  createCart() : Observable<any> {
    return this.http.get('https://localhost:8836/test/newCart');
  }

  getCart(id: number) : Observable<any> {
    return this.http.get('https://localhost:8836/test/getCart/' + id);
  }

  removeCart(id: string) : Observable<any> {
    return this.http.get('https://localhost:8836/test/removeItemFromCart/' + id);
  }

  getUserTx() : Observable<any> {
    return this.http.get('https://localhost:8836/test/getUserTxs');
  }

}
