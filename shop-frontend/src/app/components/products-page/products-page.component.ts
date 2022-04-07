import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-products-page',
  templateUrl: './products-page.component.html',
  styleUrls: ['./products-page.component.css']
})
export class ProductsPageComponent implements OnInit {
  products: MatTableDataSource<Product> = new MatTableDataSource();
  columnsToDisplay = ['name', 'description', 'type', 'actions'];
  
  productList: Array < Product > =[
    {
      name: "Adidasi",
      price: 29.00,
      description:"Adidasi de dama marimea 38"

    },
    {
      name: "Caciula copil",
      price: 15.00,
      description:"Caciula copil de 5-7 ani roz"
    },
    {
      name: "Conserve de peste",
      price: 21.00,
      description:"Diferite sortimente de peste"
    },{
      name: "Paste fainoase",
      price: 20.00,
      description:"paste fainoase diverse sortimente"
    },
    {
      name: "Sampon copii",
      price: 18.00,
      description:"Sampon pentru copii 100 ml"
    },
    {
      name: "Periuta si pasta de dinti Colgate ",
      price: 20.00,
      description:"pasta de dinti si periuta pentru copii"
    },
    {
      name: "Cornuri 7Days",
      price: 10.00,
      description:"cornuri diverse sortimente"
    },
  ];
  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) {
    //this.fetch();
   }

  ngOnInit(): void {
    
    this.products = new MatTableDataSource(this.productList);
  }
  fetch() {
    this.productService.getProducts().subscribe((items) => {
      this.products = new MatTableDataSource(items);
    });
  }
 

 // deleteItem(itemId: string) {
  //   console.log("itemId", itemId)
  //   this.itemServices.deleteItem(itemId).subscribe(() => {
  //     console.log("itemId deleted", itemId)
  //     this.fetch();
  //   });
  // }
  addToCart(product: Product) {
    this.productService.addData(product);
  }
}
