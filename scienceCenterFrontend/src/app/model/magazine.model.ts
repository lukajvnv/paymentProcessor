export class Magazine{
    id: number;
    issn: string;
    name: string;
    price: number;

    // constructor() {}

    constructor(id: number, issn: string, name: string, price: number){
        this.id = id;
        this.issn = issn;
        this.name = name;
        this.price = price;
    }
}