package org.example.service;

import org.example.entity.Seller;
import org.example.entity.Product;
import org.example.exception.ProductException;
import org.example.exception.ProductNotFoundException;
import org.example.repository.SellerRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    SellerRepository sellerRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository){
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product saveProduct(long id, Product p) throws ProductNotFoundException {
        Optional<Seller> optional = sellerRepository.findById(id);
        Seller s;
        if(optional.isEmpty()){
            throw new ProductNotFoundException("no such artist...");
        }else{
            s = optional.get();
        }
        Product savedProduct = productRepository.save(p);
        s.getProducts().add(savedProduct);
        sellerRepository.save(s);
        return savedProduct;
    }
    public Product addProduct(Product p)throws ProductException{
        if (p.getProductName().isEmpty()|| p.getPrice() <= 0) {
        throw new ProductException("The product name is required. Price must be greater than $0.");
        }





            return addProduct(p);
        }



    public List<Product> getAllProductsByProductName(String productName) {
        return productRepository.findByProductName2(productName);
    }

    public Product getById(long id) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()){
            throw new ProductNotFoundException("no such product... ");
        }else{
            return p.get();
        }
    }
}
