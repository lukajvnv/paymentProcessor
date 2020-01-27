export class NewCartItemRequest{

    cartId: number;
    articleId: number;

    constructor(cartId:number, articleId: number) {
        this.cartId = cartId;
        this.articleId = articleId;
    }
}