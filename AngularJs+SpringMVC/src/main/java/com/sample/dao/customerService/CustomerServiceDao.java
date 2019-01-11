/**
 * A service interface of customer
 */
package com.sample.dao.customerService;

import java.util.List;

import com.sample.entity.Customer;

public interface CustomerServiceDao {
    
    public List<Customer> getAllCustomers(); // Fetch all Customer details from customer table
    
    public void createCustomer(Customer customer); // Insert a new customer in customer table
    
    public void updateCustomer(Customer customer); // Update the existing customer in customer table
    
    public void deleteCustomer(int id); // Delete a existing customer in customer table
    
}
