import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TestService } from '../services/test.service';
import { Magazine } from '../model/magazine.model';

@Component({
  selector: 'app-view-one-magazine',
  templateUrl: './view-one-magazine.component.html',
  styleUrls: ['./view-one-magazine.component.scss']
})
export class ViewOneMagazineComponent implements OnInit {

  private magazine: Magazine;
  private editions: any[];

  constructor(private activatedRoute: ActivatedRoute, private testService: TestService, private router: Router) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      const magId = data.get('id');

      this.testService.getById(+magId).subscribe(data => {
        this.magazine = data;
      });

      this.testService.getAllEditions(+magId).subscribe(data => {
        this.editions = data;
      });
    });
  }

  viewEdition(id: string){
    this.router.navigate(['magazine-edition', id]);
  }

}
