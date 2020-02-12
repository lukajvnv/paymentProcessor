import { Component, OnInit, Renderer2, ViewChild, ElementRef, AfterViewInit, ContentChildren, QueryList } from '@angular/core';
import { PaymentService } from '../service/payment.service';
import { TestDisplayComponent } from '../test-display/test-display.component';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit, AfterViewInit {
  
  ngAfterViewInit(): void {
    // throw new Error("Method not implemented.");
    console.log('dd');
  }

  @ViewChild('divMessages', {read: ElementRef, static: true}) divMessages: ElementRef;

  // @ContentChildren(TestDisplayComponent) tabs: QueryList<TestDisplayComponent>;
  @ContentChildren(TestDisplayComponent) tabs: TestDisplayComponent[] = [];


  content;

  constructor(private payService: PaymentService, private renderer: Renderer2) { }

  ngOnInit() {
  }

  click(){
    // this.payService.retrieveHtml().subscribe( (d) => {
    //   console.log(d);

    //   var x = document.createElement('div');
    //   x.innerHTML = d;

    //   this.content = d;

    //   this.renderer.appendChild(this.divMessages.nativeElement, x);

    // });

    this.payService.retrieveHtmls().subscribe( (d) => {
      // let tt : TestDisplayComponent[] = [];
      // for(let s of d){
      //   let t: TestDisplayComponent = TestDisplayComponent.prototype;
      //   t.append(s);
      //   tt.push(TestDisplayComponent.prototype);
      // }

      // this.tabs = tt;

      for(let s of d){
          var x = document.createElement('div');
           x.innerHTML = d;


          this.renderer.appendChild(this.divMessages.nativeElement, x);
      }
    });
  }

}
