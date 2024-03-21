package org.example.repository;
import  org.example.entity.Product;
import org.hibernate.query.SelectionQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductName(String productName);

    @Query("from Product where productName=:productName")
    List<Product> findByProductName2(@Param("productName")String productName);
}
