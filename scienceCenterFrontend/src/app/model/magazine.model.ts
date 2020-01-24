
export enum WayOfPayment{
    OPEN_ACCESS, PAID_ACCESS 
}

export class Magazine{
    magazineId: number;
    issn: string;
    name: string;
    wayOfPayment: WayOfPayment;
    sellerIdentifier: number;
    price: number;

  //   constructor() {}
    //constructor() {}

   /* constructor(id: number, issn: string, name: string, price: number){
        this.id = id;
        this.issn = issn;
        this.name = name;
        this.price = price;
    }*/

    constructor(id: number, issn: string, name: string, wayOfPayment: WayOfPayment, sellerId: number, price: number){
        this.magazineId = id;
        this.issn = issn;
        this.name = name;
        this.wayOfPayment = wayOfPayment;
        this.sellerIdentifier = sellerId;
        this.price = price;
    }


    // constructor(id: number, issn: string, name: string, wayOfPayment: WayOfPayment, sellerId: number, price: number){
    //     this.magazineId = id;
    //     this.issn = issn;
    //     this.name = name;
    //     this.wayOfPayment = wayOfPayment;
    //     this.sellerIdentifier = sellerId;
    //     this.price = price;
    // }
}