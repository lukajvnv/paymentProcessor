import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, Renderer2 } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MagazineService } from '../services/magazine.service';
import { SellerInfo } from '../model/seller-info.model';
import { Form } from '@angular/forms';
import { NewClientDto } from '../model/new-client.model';
import { NewClientSelectedPaymentTypes } from '../model/new-client-selected-payment';
import { PaymentType } from '../model/payment-type.model';

@Component({
  selector: 'app-new-client-html',
  templateUrl: './new-client-html.component.html',
  styleUrls: ['./new-client-html.component.scss']
})
export class NewClientHtmlComponent implements OnInit {
  

  private newClientRequestId: string;
  private newClientReqeust: SellerInfo = new SellerInfo();

  private newClientId: number;
  private forms: { [id: number] : Form; } = {};


  private displaySubmitBasic: boolean = true;
  private displaySelect: boolean = false;
  private displayForms: boolean = false;

  private formsKeyValues = [];
  private selectedPaymentTypes: PaymentType[] = [];

  // private selectedForms: { [id: number] : Form; } = {};
  // private selectedFormsKeyValues = [];

  private htmlContent: string[] = [];

  private uploadedFiles: { [id: string] : File; } = {};

  private shouldDisplayForm: boolean[] = [];


  constructor(private activatedRoute: ActivatedRoute, private router: Router, private magazineService: MagazineService,
    private renderer: Renderer2) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      this.newClientRequestId = data.get('id');
    });
  }

  ngOnSubmitBasic(value, form ){
    this.newClientReqeust.newClientRequestId = this.newClientRequestId;
    let x = this.magazineService.newClientBasic(this.newClientReqeust);

    x.subscribe(data => {
      console.log(data);
      //this.forms = data.forms;
      //console.log(this.forms);

      this.newClientId = data.newClientId;

      // this.formsKeyValues = Object.keys(this.forms);
      this.formsKeyValues = data.paymentTypes;
      this.displaySubmitBasic = false;
      this.displaySelect = true;
    }, err => {

    });
  }

  ngOnSubmitSelect(value, form){
    console.log(value);
    console.log(form);
    console.log(this.selectedPaymentTypes);

    // for(let t of this.selectedPaymentTypes){
    //   this.selectedForms[+t] = this.forms[+t];
    // }

    let request = new NewClientDto();
    request.newClientId = this.newClientId;
    request.paymentTypes = this.selectedPaymentTypes;
    
    let x = this.magazineService.newClientSelectedPaymentType(request);

    x.subscribe(data => {
      this.forms = data.forms;

      this.shouldDisplayForm = new Array(data.paymentTypes.length);
      for (let i = 0; i < data.paymentTypes.length; ++i) {
        this.shouldDisplayForm[i] = true;
      }

      this.selectedPaymentTypes = data.paymentTypes;

      
          
      this.displaySelect = false;
      this.displayForms = true;
    }, err => {
      console.log(err);
    });

    
  }

  proba(value, form){
    // console.log(this.divMessages.nativeElement);
    // console.log(this.divMessages.nativeElement.children[0].children[3]);
    // // this.divMessages.nativeElement.children[0].children[3].submit();

    // console.log(document.getElementById('test'));

    let f = new FormData();
    f.append('test', 'proba');
    let file = this.uploadedFiles['image'];
    f.append('image', file, file.name);

    let x = this.magazineService.test(f);

    x.subscribe(data => {
      console.log('ok');
    }, err => {
      console.log('eror');
    });

  }

  onFileUpload(event, name: string){
    console.log(name);
    let fileList: FileList = event.target.files;
    let file: File = fileList.item(0);

    this.uploadedFiles[name] = file;
  }

  ngOnSubmitParticularForm(form, index){
    let paymentType: PaymentType = form.paymentType;

    let formToSend = new FormData();


    for(let field of form.formFields){
      if(field.fieldTypeFront === 'file'){
        let file = this.uploadedFiles[field.fieldName];
        formToSend.append('image', file, file.name);
      } else {
        formToSend.append(field.fieldName, field.fieldValue);
      }
    }

    formToSend.append('sellerFk', this.newClientId.toString());

    let x = this.magazineService.submitParticularForm(paymentType.paymentTypeHandlerUrlRoot, formToSend);

    x.subscribe(data => {
      console.log('success');

      this.shouldDisplayForm[index] = false;
    }, err => {
      console.log('error');
    });
    
  }

  ngOnSubmitSubmitPaymentData(value, form){
      let nonFinished: boolean[] = this.shouldDisplayForm.filter(f => f);
      if(nonFinished.length > 0){
        alert('Please submit all data!!!');
        return;
      } 

      console.log('submitting data finished');
      //neki drugi url
      this.router.navigate(['/externalRedirect', { externalUrl: 'https://localhost:4200' }], {
        skipLocationChange: true,
        });
  }

}
