/*
 * A Pojo class for Customer Table in PropertyLease database(MySql: phpMyAdmin)
 */
package com.sample.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {

    private int custId;
    private String customerName;
    private String creationDate;
    private String lastUpdateDate;
    private long custAcctNumber;
    private String createdBy;

    // Setting out a custom date format in order to display on UI screen
    private Date date;
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/YYYY"); // Eg: 23/04/2004

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        date = new Date(creationDate.getTime());
        this.creationDate = FORMATTER.format(date);
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        date = new Date(lastUpdateDate.getTime());
        this.lastUpdateDate = FORMATTER.format(date);
    }

    public long getCustAcctNumber() {
        return custAcctNumber;
    }

    public void setCustAcctNumber(long custAcctNumber) {
        this.custAcctNumber = custAcctNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * *********RETURNING CUSTOMER DETAIL AS ARRAY**********
     * @return 
     */
    @Override
    public String toString() {
        return "Customer "
                + "[custId=" + custId + ",custName=" + customerName
                + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate
                + ", custAccNumber=" + custAcctNumber + ", createdBy=" + createdBy
                + ']';
    }
}
