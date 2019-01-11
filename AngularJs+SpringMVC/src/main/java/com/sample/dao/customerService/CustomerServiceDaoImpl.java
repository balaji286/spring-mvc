/*
 * A service class implementation of customer interface
 */
package com.sample.dao.customerService;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.dao.DataAccessException;

import com.sample.utility.DbUtility;
import com.sample.entity.Customer;

@Service("customerService") // @Service annotation is to make a class Autowired
public class CustomerServiceDaoImpl implements CustomerServiceDao {

    // Getting Database connection from Utility
    private final static JdbcTemplate JDBCTEMPLATE = new JdbcTemplate(DbUtility.getDataSource());

    // Logger is used to display information (ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF)
    private static final Logger LOGGER = Logger.getLogger(CustomerServiceDaoImpl.class);

    private static final String FETCH_ALL_CUSTOMERS_QUERY
            = "SELECT CUST_ID,CUSTOMER_NAME,CREATION_DATE,LAST_UPDATE_DATE,CUST_ACCT_NUMBER,CREATED_BY FROM xxstg_cust_contacts";
    // now() is used to set current date with time in mySql
    private static final String INSERT_CUSTOMER_QUERY
            = "INSERT INTO xxstg_cust_contacts(CUSTOMER_NAME,CREATION_DATE,LAST_UPDATE_DATE,CUST_ACCT_NUMBER) VALUES (?, now(), now(), ?)";
    private static final String UPDATE_CUSTOMER_QUERY
            = "UPDATE xxstg_cust_contacts SET CUSTOMER_NAME = ?, CUST_ACCT_NUMBER = ?, LAST_UPDATE_DATE = now() WHERE CUST_ID = ?";
    private static final String DELETE_CUSTOMER_QUERY
            = "DELETE FROM xxstg_cust_contacts WHERE CUST_ID = ?";

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = null;
        try {
            customers = JDBCTEMPLATE.query(
                    FETCH_ALL_CUSTOMERS_QUERY,
                    new BeanPropertyRowMapper(Customer.class)
            /*
                 * BeanPropertyRowMapper will map all properties to Customer Class automatically.
                 * But Customer class should contains the same name with same field which is in customer table.
             */
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while fetching customers", exception);
        }
        return customers;
    }

    @Override
    public void createCustomer(Customer customer) {
        try {
            JDBCTEMPLATE.update(
                    INSERT_CUSTOMER_QUERY,
                    new Object[]{
                        customer.getCustomerName(),
                        customer.getCustAcctNumber()
                    }
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while creating a customer", exception);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            JDBCTEMPLATE.update(
                    UPDATE_CUSTOMER_QUERY,
                    new Object[]{
                        customer.getCustomerName(),
                        customer.getCustAcctNumber(),
                        customer.getCustId()
                    }
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while updating a customer", exception);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        try {
            JDBCTEMPLATE.update(
                    DELETE_CUSTOMER_QUERY,
                    id
            );
        } catch (DataAccessException exception) {
            LOGGER.error("Error occured while deleting a customer", exception);
        }
    }

}
