/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.common.persistence;

import com.bmp.sigeca.resource.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

/**
 *
 * @author jjacobo
 */
public class ConnectionFactory {

    private static ConnectionFactory factory = null;
    protected Logger log;
    
    public static ConnectionFactory getInstance() throws ClassNotFoundException {
        if (factory == null) {
            factory = new  ConnectionFactory();
        }
        return factory;
    }
    
    /** Creates a new instance of DataSource */
    private ConnectionFactory() throws ClassNotFoundException {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(Properties.getDBProperties());
        String driverClassName = bundle.getString("driver");
        Class.forName(driverClassName);
    }
    
    public Connection getConnection() throws java.sql.SQLException {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(Properties.getDBProperties());
        String url = bundle.getString("url");
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        Connection con = DriverManager.getConnection(url,username,password);
        return con;
    }
}
