import { PaymentTypeRequest } from './../model/payment-type-request.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { retry, catchError } from 'rxjs/operators';
import { PayRequest } from '../model/pay-request.model';


@Injectable({
  providedIn: 'root'
})
export class PayServiceService {

  private REQUEST_HANDLER_ZUUL = 'https://localhost:8762/requestHandler/test';
  private REQUEST_HANDLER_API = 'https://localhost:8111/request/';
  private SCIENCE_CENTER_API = 'https://localhost:8836/pay/';

  constructor(private http: HttpClient) { }

  getPaymentTypes(request: PaymentTypeRequest): Observable<any> {
    //alert("Service?")
    return this.http.post(this.REQUEST_HANDLER_API + 'paymentTypes', request)
  //  .pipe(retry(1), catchError(this.handlerError));
  }

  buyMagazine(request: PayRequest) : Observable<any> {
    return this.http.post(request.url, request)
    .pipe(retry(1), catchError(this.handlerError));
  }

  getPrice(sellerId: Number) : Observable<any> {
    return this.http.get(this.REQUEST_HANDLER_API + 'getPrice/'+sellerId);
  }

  private handlerError(error: Response) {
    return Observable.throw(error);
  }
}
