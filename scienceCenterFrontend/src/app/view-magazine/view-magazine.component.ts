import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Magazine } from '../model/magazine.model';

@Component({
  selector: 'app-view-magazine',
  templateUrl: './view-magazine.component.html',
  styleUrls: ['./view-magazine.component.scss']
})
export class ViewMagazineComponent implements OnInit {

   magazine: Magazine;

  constructor(private router: Router) { }

  ngOnInit() {
    this.magazine = new Magazine(1, '4563-1235', 'Inzenjerski glasnik', 500, 0.0001);
  }

  buy(){
    this.router.navigate(['buy-magazine']);
  }

}
