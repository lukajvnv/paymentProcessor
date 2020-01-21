import { PaymentType } from './payment-type.model';

export class PaymentTypeResponse {

     sellerInfoDbId: number;

     paymentTypes: PaymentType[];

     url: string;

    constructor(){

    }
}