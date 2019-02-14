/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.common.persistence;

import com.bmp.sigeca.resource.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 *
 * @author jjacobo
 */
public class PooledConnectionFactory {

    private static PooledConnectionFactory factory = null;
    protected GenericObjectPool pool = null;
    protected DriverManagerConnectionFactory dmcf = null;
    protected PoolableConnectionFactory pcf = null;
    protected Logger log;
    
    public static PooledConnectionFactory getInstance() throws ClassNotFoundException {
        if (factory == null) {
            factory = new  PooledConnectionFactory();
        }
        return factory;
    }
    
    /** Creates a new instance of PooledDataSource */
    private PooledConnectionFactory() throws ClassNotFoundException {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(Properties.getDBProperties());
        String url = bundle.getString("url");
        String driverClassName = bundle.getString("driver");
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        String poolName = bundle.getString("poolName");
        log = Logger.getLogger(this.getClass());
        log.debug("Pool url " + url);
        log.debug("Pool driver class name " + driverClassName);
        log.debug("Pool username " + username);
        log.debug("Pool password " + password);
        pool = new GenericObjectPool(null);
        Class.forName(driverClassName);
        dmcf = new DriverManagerConnectionFactory(url, username, password);
        
        try {
            int maxActive = Integer.parseInt(bundle.getString("maxActive"));
            log.debug("Pool maxActive " + maxActive);
            pool.setMaxActive(maxActive);
        }
        catch(java.util.MissingResourceException mre) {
        }

        try {
            int maxIdle = Integer.parseInt(bundle.getString("maxIdle"));
            log.debug("Pool maxIdle " + maxIdle);
            pool.setMaxIdle(maxIdle);
        }
        catch(java.util.MissingResourceException mre) {
        }
        
        try {
            int maxWait = Integer.parseInt(bundle.getString("maxWait"));
            log.debug("Pool maxWait " + maxWait );
            pool.setMaxWait(maxWait);
        }
        catch(java.util.MissingResourceException mre) {
        }

        try {
            int minIdle = Integer.parseInt(bundle.getString("minIdle"));
            log.debug("Pool minIdle " + minIdle );
            pool.setMinIdle(minIdle);
        }
        catch(java.util.MissingResourceException mre) {
        }

        pcf = new PoolableConnectionFactory(
        dmcf, pool, null, null, false, true);

        // register our pool and give it a name
        new PoolingDriver().registerPool(poolName, pool);
    }
    
    public Connection getConnection() throws java.sql.SQLException {
        // get a connection from the pool
        log.debug("Getting a connection from the pool");
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(Properties.getDBProperties());
        String poolName = bundle.getString("poolName");
        //Connection con = DriverManager.getConnection(url);
        Connection con = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+poolName);
        log.debug("Connection retrieved from the pool");
        log.info("numActive " + pool.getNumActive());
        log.debug("numIdle " + pool.getNumIdle());
        log.debug("maxWait " + pool.getMaxWait());
        return con;
    }
}
