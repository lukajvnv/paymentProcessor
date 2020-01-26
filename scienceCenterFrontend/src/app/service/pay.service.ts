import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { Magazine } from '../model/magazine.model';
import { PaymentTypeRequest } from '../model/payment-type-request.model';
import { PayRequest } from '../model/pay-request.model';


@Injectable({
  providedIn: 'root'
})
export class PayService {

  private REQUEST_HANDLER_ZUUL = 'https://localhost:8762/requestHandler/test';
  private REQUEST_HANDLER_API = 'https://localhost:8111/request/';
  private SCIENCE_CENTER_API = 'https://localhost:8836/pay/';


  constructor(private http: HttpClient) { }

  test(): Observable<any>{
    return this.http.get(this.SCIENCE_CENTER_API).pipe( retry(1), catchError(this.handlerError));
    // return this.http.get(this.REQUEST_HANDLER_ZUUL);

  }

  // payPost(): Observable<any>{
  //   return this.http.post(this.SERVICE_API, new Proba(5, 'fjla'));
  // }

  // getPaymentTypes(request: PaymentTypeRequest): Observable<any> {
  //   return this.http.post(this.REQUEST_HANDLER_API + 'paymentTypes', request)
  //   .pipe(retry(1), catchError(this.handlerError));
  // }

  getPaymentTypes(request: PaymentTypeRequest): Observable<any> {
    return this.http.post(this.SCIENCE_CENTER_API + 'paymentTypes', request)
    .pipe(retry(1), catchError(this.handlerError));
  }

  buyMagazine(request: PayRequest) : Observable<any> {
    return this.http.post(this.SCIENCE_CENTER_API + 'buy', request)
    .pipe(retry(1), catchError(this.handlerError));
  }

  //cuvanje shopping cart-a
  executePayment(request: any): Observable<any> {
    // workaround
//alert("???????????????????????????")
    return this.http.post(this.SCIENCE_CENTER_API + 'cart', request)
    .pipe(retry(1), catchError(this.handlerError));
  }

  private handlerError(error: Response) {
    return Observable.throw(error);
  }
}
