import { Component, OnInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-test-display',
  templateUrl: './test-display.component.html',
  styleUrls: ['./test-display.component.css']
})
export class TestDisplayComponent implements OnInit {

  constructor(private renderer: Renderer2) { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    // throw new Error("Method not implemented.");
    console.log('dd');
  }

  @ViewChild('divMessages', {read: ElementRef, static: true}) divMessages: ElementRef;

  append(d){
      var x = document.createElement('div');
      x.innerHTML = d;

      // this.content = d;

      this.renderer.appendChild(this.divMessages.nativeElement, x);
  }

}
