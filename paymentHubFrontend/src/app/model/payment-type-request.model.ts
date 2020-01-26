export class PaymentTypeRequest{


    sellerId: number;
    orderId: number;

    constructor(id: number, orderId: number){
        this.sellerId = id;
        this.orderId = orderId;
    }
}