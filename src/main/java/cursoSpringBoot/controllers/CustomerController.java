package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clients") // This is the class level annotation. When this annotation is applied to a class, it does unify the URLs for all endpoints
public class CustomerController {
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Alejandro Duran", "alejandroduran", "contrasena123"),
            new Customer(456, "Ivan Torres", "ivantorres", "clave456"),
            new Customer(789, "Alexis Aguirre", "alexisaguirre", "secreto789"),
            new Customer(358, "Ornelas Lopez", "ornelaslopez", "password234")
    ));

    @GetMapping // Get all the clients
    // @RequestMapping(method = RequestMethod.GET) // This is method level annotation and older way of GetMapping, however, the election between those two, depends on the needs or preferences of the project
    public ResponseEntity<List<Customer>> getCustomers() { // Endpoint method
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{userName}") // Get one client by ID
    // @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<?> getClient(@PathVariable String userName) { // Second endpoint method
        for (Customer c : customers) {
            if(c.getUserName().equalsIgnoreCase(userName)) {
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client " + userName + " does not exist");
    }

    @PostMapping
    // @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postClient(@RequestBody Customer customer) { //The annotation @RequestBody is in charge of obtaining the JSON values we're sending (through POST request), and indicate Spring Boot to make the conversion of that JSON format and store it in the customer list.
        customers.add(customer);
        // return ResponseEntity.status(HttpStatus.CREATED).body("The client " + customer.getName() + " was created successfully"); // For customized HTTP response messages
        URI location = ServletUriComponentsBuilder // Creating a URI
                .fromCurrentRequest() // With this method we get the base URI
                .path("/{userName}")
                .buildAndExpand(customer.getUserName())
                .toUri(); // This method finishes the construction, and convert the constructed route into a URI object

        // return ResponseEntity.created(location).build(); // Only sending the location
        return ResponseEntity.created(location).body(customer); // Sending the location, and the customer JSON
    }

    @PutMapping // When using PUT we have to update all the fields, but if we use PATCH, we only have to update only the one field to be updated
    // @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> putClient(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                c.setName(customer.getName());
                c.setUserName(customer.getUserName());
                c.setPassword(customer.getPassword());

                // return ResponseEntity.ok("The client " + customer.getId() + " was updated successfully"); // For customized HTTP response messages
                return ResponseEntity.noContent().build();
            }
        }
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client " + customer.getId() + " was not found"); // // For customized HTTP response messages
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                customers.remove(c);

                // return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The user " + id + " was deleted successfully"); // For customized HTTP response messages
                return ResponseEntity.noContent().build();
            }
        }
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client " + id + " was not found"); // For customized HTTP response messages
        return  ResponseEntity.notFound().build();
    }

    @PatchMapping // When using PUT we have to update all the fields, but if we use PATCH, we only have to update only the one field to be updated
    // @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<?> patchClient(@RequestBody Customer customer) {
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

                return  ResponseEntity.ok("The client " + customer.getId() + " was updated successfully");
            }
        }
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client " + customer.getId() + " was not found"); // For customized HTTP response messages
        return  ResponseEntity.notFound().build();
    }
}
