import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-succes-page',
  templateUrl: './succes-page.component.html',
  styleUrls: ['./succes-page.component.scss']
})
export class SuccesPageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  click(){
    this.router.navigate(['user-tx']);
  }

}
