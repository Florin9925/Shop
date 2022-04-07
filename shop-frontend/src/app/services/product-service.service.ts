import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public productList: Product[] = [];

  readonly baseUrl = "http://localhost:80/api/item";
  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private httpClient: HttpClient) { }

  getData(): Product[] {
    return this.productList;
  }
  addData(product: Product) {
    console.log(typeof this.productList);
    this.productList.push(product);

  }
  deleteData(product: Product) {
    const index = this.productList.findIndex(p => p.name === product.name);
    if (index > -1) {
      this.productList.splice(index, 1);
    }
  }
  getProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.baseUrl, this.httpOptions);
  }
  addItem(product: Product) {
    return this.httpClient.post(this.baseUrl, product, this.httpOptions).subscribe();
  }
  editItem(object: Product): Observable<Product> {
    return this.httpClient.put<Product>(this.baseUrl, object, this.httpOptions)
  }
  deleteItem(id: string) {
    return this.httpClient.delete<Product>(this.baseUrl + '/' + id, this.httpOptions)
  }
  getItemById(id: string): Observable<Product> {
    return this.httpClient.get<Product>(this.baseUrl + '/id/' + id, this.httpOptions)
  }
 
}
