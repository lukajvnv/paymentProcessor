import { Magazine } from './magazine.model';
export class Cart {

    magazines: Magazine[] = [];
    
    totalAmount: number = 0;

    //bice neki customerId da se zna kom kupcu aktiviramo casopis

    constructor() {

    }


}
//pomocna klasa za cuvanje totalAmount-a privremeno na paymentHandlar-u
export class ShoppingCart {
    sellerId: number;
    totalAmount: number;
    url: string;//url za cuvanje na payment handlaru
    orderId: number; //random generisani orderId

    constructor() {

    }

}