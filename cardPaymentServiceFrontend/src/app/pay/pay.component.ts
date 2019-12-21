import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../service/payment.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { CardRequest } from '../model/card-request.model';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { error } from 'util';

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css']
})
export class PayComponent implements OnInit {

  cardFormGroup : FormGroup;
  cardPaymentRequest: CardRequest = new CardRequest();

  constructor(private payService: PaymentService, private formBuilder: FormBuilder, private toastr: ToastrService, private router: Router) { 
   // this.cardFormGroup = this.formBuilder.group();
   this.cardFormGroup = this.formBuilder.group({
      pan: new FormControl('', [Validators.required/*, Validators.minLength(10), Validators.maxLength(16)*/] ),
      securityCode: new FormControl('', [Validators.required/*, Validators.minLength(3), Validators.maxLength(4)*/]),
      cardHolderName: new FormControl('', Validators.required),
      validUntil: new FormControl('', Validators.required)
  });
  }

  ngOnInit() {
    // this.router.navigate(['/externalRedirect', { externalUrl: "http://www.ftn.uns.ac.rs/n1941540019/fakultet-tehnickih-nauka" }], {
    //   skipLocationChange: true,
    // });
    this.cardPaymentRequest.paymentId = '799806098';
    this.cardPaymentRequest.merchantUsername = 'casopisA';
  

  }

  // cardFormGroup : FormGroup = new FormGroup({
  //   pan: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(16)] ),
  //   securityCode: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(4)]),
  //   cardHolderName: new FormControl('', Validators.required),
  //   validUntil: new FormControl('', Validators.required)
  // });



  proba(){
    this.payService.pay().subscribe(data => {
      console.log();
    });
  }

  probaPost(){
    this.payService.payPost().subscribe(data => {
      console.log();
    });
  }

  get pan() {
    return this.cardFormGroup.get('pan');
  }

  get cardHolderName() {
    return this.cardFormGroup.get('cardHolderName');
  }

  get securityCode() {
    return this.cardFormGroup.get('securityCode');
  }

  get validUntil() {
    return this.cardFormGroup.get('validUntil');
  }

  add(){
    this.cardPaymentRequest.pan = this.pan.value;
    this.cardPaymentRequest.cardHolderName = this.cardHolderName.value;
    this.cardPaymentRequest.securityCode = this.securityCode.value;
    this.cardPaymentRequest.validUntil = this.validUntil.value;

    this.payService.initPayment(this.cardPaymentRequest).subscribe(data => {
      console.log('resi');
    }, (error: Response) => {
      console.log(error.json);
    });
  }

}
