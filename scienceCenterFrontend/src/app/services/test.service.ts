import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  test(): Observable<any> {
    alert("USO! 2");
    return this.http.get('https://localhost:8836/test');
  }
}
