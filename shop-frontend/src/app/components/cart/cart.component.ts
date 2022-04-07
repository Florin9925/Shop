import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  products: MatTableDataSource<Product> = new MatTableDataSource();
  columnsToDisplay = ['name', 'description', 'type', 'actions'];
  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) {
    this.fetch();
   }

  ngOnInit(): void {
  }
  fetch() {
    this.products = new MatTableDataSource(this.productService.getData());
  }
  deleteFromCart(product: Product) {
    this.productService.deleteData(product);
    this.fetch();
  }
}
