import { TestService } from './services/test.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'scienceCenterFrontend'; 

  constructor(private testService: TestService) {

  }

  

  testna() {
    alert("USO!");
    this.testService.test();
  }
}
