package com.dbmicrotech.johnlewisstore.control;

import com.dbmicrotech.johnlewisstore.model.Customer;
import com.dbmicrotech.johnlewisstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cus")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/insert")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>("customer added successfully", HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<Customer> getCustomer(@RequestParam Integer id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer!=null){
            return ResponseEntity.ok(customer);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public List<Customer>getOrderAll(){
        return customerRepository.findAll();

    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestParam Integer id,@RequestBody Customer updateCustomer){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer!=null){
            customer.setAddress(updateCustomer.getAddress());
            customer.setName(updateCustomer.getName());
            customer.setEmail(updateCustomer.getEmail());
            customerRepository.save(customer);
            return ResponseEntity.ok(customer);

        }else {
            return ResponseEntity.noContent().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteById(@PathVariable Integer id){

            if (customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        }

}
