package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Alejandro Duran", "alejandroduran", "contrasena123"),
            new Customer(456, "Ivan Torres", "ivantorres", "clave456"),
            new Customer(789, "Alexis Aguirre", "alexisaguirre", "secreto789"),
            new Customer(123, "Ornelas Lopez", "ornelaslopez", "password234")
    ));

    @GetMapping("/clients")
    public List<Customer> getCustomers() {// Endpoint method
        return customers;
    }

    @GetMapping("/clients/{userName}")// Second endpoint method
    public Customer getClient(@PathVariable String userName) {
        for (Customer c : customers) {
            if(c.getUserName().equalsIgnoreCase(userName)) {
                return c;
            }
        }
        return null;
    }

    @PostMapping("/clients")
    public Customer postClient(@RequestBody Customer customer) { //The annotation @RequestBody is in charge of obtaining the JSON values we're sending (through POST request), and indicate Spring Boot to make the conversion of that JSON format and store it in the customer list.
        customers.add(customer);
        return customer;
    }

    @PutMapping("/clients")
    public Customer putClient(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                c.setName(customer.getName());
                c.setUserName(customer.getUserName());
                c.setPassword(customer.getPassword());

                return c;
            }
        }
        return null; // Return null in an endpoint is a bad practice, however, for the sake of practicing is acceptable to do just for this case.
    }
}
