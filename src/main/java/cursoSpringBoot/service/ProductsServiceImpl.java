package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@ConditionalOnProperty(name = "service.products", havingValue = "list") // Conditional dependencies injection
public class ProductsServiceImpl implements ProductService {

    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop", 5999.99, 10),
            new Product(2, "Backpack", 99.99, 120),
            new Product(1, "Smartphone", 19999.99, 15),
            new Product(1, "Playstation 5", 9999.99, 100)
    ));

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
