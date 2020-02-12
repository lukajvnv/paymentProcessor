import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proba } from '../model/proba.model';
import { CardRequest } from '../model/card-request.model';
import { retry, catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private SERVICE_API = 'https://localhost:8841/test';
  private CARD_SERVICE_API = 'https://localhost:8841/card/';

  constructor(private http: HttpClient) { }

  pay(): Observable<any>{
    return this.http.get(this.SERVICE_API);
  }

  payPost(): Observable<any>{
    return this.http.post(this.SERVICE_API, new Proba(5, 'fjla'));
  }

  initPayment(cardPaymentRequest: CardRequest): Observable<any> {
    return this.http.post(this.CARD_SERVICE_API + 'pay', cardPaymentRequest)
    .pipe(retry(1), catchError(this.handlerError));
  }

  testRedirect(): Observable<any> {
    return this.http.get(this.SERVICE_API + '/locationRedirect');
  }

  retrieveHtml(): Observable<any> {
    // return this.http.get<string>(this.SERVICE_API + '/testHelloT', options: {} );
    const headers = new HttpHeaders();
    return this.http.get(this.SERVICE_API + '/testHelloT', { headers, responseType: 'text'})
  }

  retrieveHtmls(): Observable<any> {
    // return this.http.get<string>(this.SERVICE_API + '/testHelloT', options: {} );
    const headers = new HttpHeaders();
    return this.http.get(this.SERVICE_API + '/testHelloTArr')
  }

  private handlerError(error: Response) {
    return Observable.throw(error);
  }
}
