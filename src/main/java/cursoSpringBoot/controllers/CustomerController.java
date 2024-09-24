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
            new Customer(358, "Ornelas Lopez", "ornelaslopez", "password234")
    ));

    @GetMapping("/clients") // Get all the clients
    public List<Customer> getCustomers() { // Endpoint method
        return customers;
    }

    @GetMapping("/clients/{userName}") // Get one client by ID
    public Customer getClient(@PathVariable String userName) { // Second endpoint method
        for (Customer c : customers) {
            if(c.getUserName().equalsIgnoreCase(userName)) {
                return c;
            }
        }
        return null; // Return null in an endpoint is a bad practice, however, for the sake of practicing is acceptable to do just for this case.
    }

    @PostMapping("/clients")
    public Customer postClient(@RequestBody Customer customer) { //The annotation @RequestBody is in charge of obtaining the JSON values we're sending (through POST request), and indicate Spring Boot to make the conversion of that JSON format and store it in the customer list.
        customers.add(customer);
        return customer;
    }

    @PutMapping("/clients") // When using PUT we have to update all the fields, but if we use PATCH, we only have to update only the one field to be updated
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

    @DeleteMapping("/clients/{id}")
    public Customer deleteClient(@PathVariable int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                customers.remove(c);

                return c;
            }
        }
        return null;
    }

    @PatchMapping("/clients") // When using PUT we have to update all the fields, but if we use PATCH, we only have to update only the one field to be updated
    public  Customer patchClient(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                if (customer.getName() != null) { // Changing the real name
                    c.setName(customer.getName());
                }
                if (customer.getUserName() != null) { // Changing the username
                    c.setUserName(customer.getUserName());
                }
                if (customer.getPassword() != null) { // Changing the password
                    c.setPassword(customer.getPassword());
                }

                return  c;
            }
        }
        return null; // Return null in an endpoint is a bad practice, however, for the sake of practicing is acceptable to do just for this case.
    }
}
