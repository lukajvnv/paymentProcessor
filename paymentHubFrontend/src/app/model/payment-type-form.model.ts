import { FormField } from './payment-type-form-field.model';
import { PaymentType } from './payment-type.model';

export class Form {

    paymentType: PaymentType;
    // formFields: { [id: string] : FormField; } = {};
    formFields: FormField[] = [];


}