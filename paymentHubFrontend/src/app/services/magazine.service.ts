import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PayRequest } from '../model/pay-request.model';
import { Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { SellerInfo } from '../model/seller-info.model';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {

  private REQUEST_HANDLER_ZUUL = 'https://localhost:8762/requestHandler/test';
  private REQUEST_HANDLER_API = 'https://localhost:8111/request/';
  private REQUEST_CLIENT_HANDLER_API = 'https://localhost:8111/client/';

  

  constructor(private http: HttpClient) { }

  

  newClientBasic(request: SellerInfo) : Observable<any> {
    return this.http.post(this.REQUEST_CLIENT_HANDLER_API + 'newClient', request)
    .pipe(retry(1), catchError(this.handlerError));
  }

  newClientSelectedPaymentType(request) : Observable<any> {
    return this.http.post(this.REQUEST_CLIENT_HANDLER_API + 'newClientPaymentTypesSubmit', request)
    .pipe(retry(1), catchError(this.handlerError));
  }

  newClientComplex(request) : Observable<any> {
    return this.http.post(this.REQUEST_CLIENT_HANDLER_API + 'newClientPaymentData', request)
    .pipe(retry(1), catchError(this.handlerError));
  }

  test(req): Observable<any> {
    return this.http.post('https://localhost:8766/field/submit', req);
  }

  submitParticularForm(url: string, req): Observable<any> {
    return this.http.post(url + 'field/submit', req);
  }

  private handlerError(error: Response) {
    return Observable.throw(error);
  }
}
