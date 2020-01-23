import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SellerInfo } from '../model/seller-info.model';
import { MagazineService } from '../services/magazine.service';
import { FormField } from '../model/payment-type-form-field.model';
import { Form } from '../model/payment-type-form.model';
import { NewClientDto } from '../model/new-client.model';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.scss']
})
export class NewClientComponent implements OnInit {

  private newClientRequestId: string;
  private newClientReqeust: SellerInfo = new SellerInfo();

  private newClientId: number;
  private forms: { [id: number] : Form; } = {};


  private displaySubmitBasic: boolean = true;
  private displaySelect: boolean = false;
  private displayForms: boolean = false;

  private formsKeyValues = [];
  private selectedPaymentTypes = [];

  private selectedForms: { [id: number] : Form; } = {};
  private selectedFormsKeyValues = [];

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private magazineService: MagazineService) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      this.newClientRequestId = data.get('id');
    });
    

  }

  ngOnSubmitBasic(value, form ){
    this.newClientReqeust.newClientRequestId = this.newClientRequestId;
    this.newClientReqeust.newClientRequestId = '1';
    let x = this.magazineService.newClientBasic(this.newClientReqeust);

    x.subscribe(data => {
      console.log(data);
      this.forms = data.forms;
      this.newClientId = data.newClientId;
      console.log(Object.keys(this.forms));
      console.log(this.forms);

      this.formsKeyValues = Object.keys(this.forms);
      this.displaySubmitBasic = false;
      this.displaySelect = true;
    }, err => {

    });
  }

  ngOnSubmitSelect(value, form){
    console.log(value);
    console.log(form);
    console.log(this.selectedPaymentTypes);

    for(let t of this.selectedPaymentTypes){
      this.selectedForms[+t] = this.forms[+t];
    }

    


    this.displaySelect = false;
    this.displayForms = true;
  }

  ngOnSubmitSubmitPaymentData(value, form){
    console.log(value);
    console.log(form);
    console.log(this.selectedForms);

    let newClientData = new NewClientDto();
    newClientData.newClientId = this.newClientId;
    newClientData.forms = this.selectedForms;
    let x = this.magazineService.newClientComplex(newClientData);

    x.subscribe(data => {
      alert('New client is added!')
      this.router.navigate(['/externalRedirect', { externalUrl: 'https://localhost:4200/success' }], {
        skipLocationChange: true,
        });
    }, err => {
      
    });
  }

}
