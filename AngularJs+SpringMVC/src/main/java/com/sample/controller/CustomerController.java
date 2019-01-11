/**
 * A controller class which is taken care for CustomerService
 */
package com.sample.controller;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dao.customerService.CustomerServiceDao;
import com.sample.entity.Customer;

@RestController
public class CustomerController {
    
    // Logger is used to display information (ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF)
    private static final Logger LOGGER  = Logger.getLogger(CustomerController.class);

    @Autowired
    CustomerServiceDao customerService;
    
    @PostMapping(value = "/status", headers = "Accept=application/json")
    public Object status(@RequestBody Object obj) {
        LOGGER.info("**********STATUS***********");
        LOGGER.info(obj);
        return obj;
    }

    // Fetching all customer details
    @GetMapping(value = "/customers", headers = "Accept=application/json")
    public List<Customer> getAllCustomers() {
        LOGGER.info("Getting customer details");
        return customerService.getAllCustomers();
    }

    // Create a new customer
    @PostMapping(value = "/customers/create", headers = "Accept=application/json")
    public List<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        LOGGER.info("New customer successfully created..!");
        return customerService.getAllCustomers();
    }
    
    // Update the customer detail
    @PutMapping(value = "/customers/{custId}", headers = "Accept=application/json")
    public List<Customer> updateCustomer(@PathVariable int custId, @RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        LOGGER.info("Customer details updated successfully..!");
        return customerService.getAllCustomers();
    }

    // Delete an existing customer
    @DeleteMapping(value = "/customers/{custId}", headers = "Accept=application/json")
    public List<Customer> deleteCustomer(@PathVariable int custId) {
        customerService.deleteCustomer(custId);
        LOGGER.info("Customer deleted successfully..!");
        return customerService.getAllCustomers();
    }
}
