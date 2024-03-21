package org.example.controller;

import org.example.entity.Seller;
import org.example.entity.Product;
import org.example.exception.ProductException;
import org.example.exception.ProductNotFoundException;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
@GetMapping(value = "/product", params = "productName")
    public ResponseEntity<List<Product>> getAllProductsByTitle(@RequestParam String productName){

        List<Product> products;
    if (productName == null) {
        products = productService.getAllProducts();
    }else{
        products = productService.getAllProductsByProductName(productName);
    }
    return new ResponseEntity<>(products, HttpStatus.OK);
}

    @GetMapping(value = "product")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products;
        products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("seller/{id}/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product p, @PathVariable long id) throws Exception {
        Product product = productService.saveProduct(id, p);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        try{
            Product p = productService.getById(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/product")
    public ResponseEntity<Product> save(@RequestBody Product p) throws ProductException {
        Product product = productService.addProduct(p);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}