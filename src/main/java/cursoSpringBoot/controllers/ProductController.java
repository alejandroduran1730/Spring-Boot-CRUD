package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Product;
import cursoSpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Dependencies injection
    @Autowired
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productsService.getProducts();

        return ResponseEntity.ok(products);
    }
}
