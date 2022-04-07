export class Product {
  name: string = '';
  price: number = 0.00;
  description: string = '';
 
  constructor(init?: Partial<Product>) {
    Object.assign(this, init);
  }
}
