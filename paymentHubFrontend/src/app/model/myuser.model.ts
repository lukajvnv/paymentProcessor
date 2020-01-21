import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

export class User{
    id: number;
    username: string;
    password: string;
    mail: string;
    firstName: string;
    lastName: string;
    city: string;
    country: string;
    title: string;
    reviewer: boolean;
    activate: boolean;

    constructor(id: number,
        username: string,
        password: string,
        mail: string,
        firstName: string,
        lastName: string,
        city: string,
        country: string,
        title: string,
        reviewer: boolean,
        activate: boolean) {

            this.id = id;
            this.username = username;
            this.password = password;
            this.mail = mail;
            this.firstName = firstName;
            this.lastName = lastName;
            this.city = city;
            this.country = country;
            this.title = title;
            this.reviewer = reviewer;
            this.activate = activate;

    }

    // constructor() {}

   /* constructor(id: number, issn: string, name: string, price: number){
        this.id = id;
        this.issn = issn;
        this.name = name;
        this.price = price;
    }*/

}