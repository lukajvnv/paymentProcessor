import { Form } from './payment-type-form.model';

export class NewClientDto {
    newClientId: number;
    forms: { [id: number] : Form; } = {};

    paymentTypes: any[];

    constructor() {}
}