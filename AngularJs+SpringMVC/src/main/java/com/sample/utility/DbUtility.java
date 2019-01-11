/**
 * A Database connection class by using DriverManagerDataSource class
 */

package com.sample.utility;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbUtility {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.16.1.93/propertylease";
    private static final String USER_NAME = "MobileAppsAdmin";
    private static final String PASSWORD = "Welcome@4i";

    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

}
