import { Magazine } from './magazine.model';
export class Cart {

    magazines: Magazine[] = [];
    
    totalAmount: number = 0;

    cartId: number;

    //bice neki customerId da se zna kom kupcu aktiviramo casopis

    constructor() {

    }


}
//pomocna klasa za cuvanje totalAmount-a privremeno na paymentHandlar-u
export class ShoppingCart {
    sellerId: number;
    totalAmount: number;
    url: string;//url za cuvanje na payment handlaru
    orderId: number; //jedinstven orderId po kome ce se znati ko je zeli da kupi

    constructor() {

    }

}

export class OrderId {
    orderId: number;
    constructor() {

    }

}