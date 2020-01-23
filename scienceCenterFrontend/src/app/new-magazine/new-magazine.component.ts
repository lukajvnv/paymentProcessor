import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Magazine } from '../model/magazine.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-new-magazine',
  templateUrl: './new-magazine.component.html',
  styleUrls: ['./new-magazine.component.scss']
})
export class NewMagazineComponent implements OnInit {

  private request: Magazine = new Magazine();
  private response;
  


  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit() {
    
  }

  onSubmit(value, form){
  
    let x = this.http.post('https://localhost:8836/pay/register', this.request) as Observable<any>;

    x.subscribe(
      res => {
        this.response = res;
        this.router.navigate(['/externalRedirect', { externalUrl: this.response.url }], {
          skipLocationChange: true,
        });
      },
      err => {
        console.log("Error occured");
      }
    );

  
  }

  

}
