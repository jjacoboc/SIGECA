package com.bmp.sigeca.resource;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;


/**
 *
 * @author  Ronald Ramirez
 * @version 1.1a
 */
public class ConnectionPool implements java.lang.Runnable {
    
    static ConnectionPool pool = null;
    
    public static ConnectionPool getConnectionPool(){
        if(pool==null){
            try{
                pool = new ConnectionPool();
            }
            catch (Exception e){
                System.out.println("No se pudo crear el Pool");
                e.printStackTrace();
            }
        }
        return pool;
    }
    
    private String driver, url, username, password;
    private int maxConnections;
    private boolean waitIfBusy;
    private Vector availableConnections, busyConnections;
    private boolean connectionPending = false;
    
    public ConnectionPool()throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle(Properties.getDBProperties());
        this.driver=rb.getString("driver");
        this.url=rb.getString("url");
        this.username=rb.getString("user");
        this.password=rb.getString("pass");
        
        //String x = bundle.getString("driver");
        ///this.driver = "oracle.jdbc.driver.OracleDriver";
        /*this.url = "jdbc:odbc:"+bundle.getString("hostBD")+":"+bundle.getString("puertoBD")+
            "?database="+bundle.getString("instancia");*/
        //this.url = "jdbc:odbc:"+bundle.getString("hostBD");
        //this.url = "jdbc:hsqldb:usr/local/resin/doc/~paulonline/paulonline/paulonline";
        ///this.url = "jdbc:oracle:thin:@localhost:1521:XE";
        ///this.username = "dat";
        ///this.password = "java";
        //this.maxConnections = Integer.parseInt(bundle.getString("tamPool"));
        this.maxConnections = 4;
        this.waitIfBusy = true;
        //int initialConnections = Integer.parseInt(bundle.getString("tamPool"));
        int initialConnections = 4;
        if (initialConnections > maxConnections) {
            initialConnections = maxConnections;
        }
        availableConnections = new Vector(initialConnections);
        busyConnections = new Vector();
        for(int i=0; i<initialConnections; i++) {
            availableConnections.addElement(makeNewConnection());
        }
    }
    
    public synchronized Connection getConnection()throws SQLException {
        if (!availableConnections.isEmpty()) {
            Connection existingConnection =(Connection)availableConnections.lastElement();
            int lastIndex = availableConnections.size() - 1;
            availableConnections.removeElementAt(lastIndex);
            System.out.println("Con tomada "+lastIndex+" ");
            if (existingConnection.isClosed()) {
                notifyAll(); // Freed up a spot for anybody waiting
                return(getConnection());
            }
            else {
                busyConnections.addElement(existingConnection);
                System.out.println("Se esta entregando : "+existingConnection);
                return(existingConnection);
            }
        }
        else {
            if ((totalConnections() < maxConnections) &&!connectionPending) {
                System.out.println("Se creara una conexion en BG");
                makeBackgroundConnection();
            }
            else
                if (!waitIfBusy) {
                    throw new SQLException("Connection limit reached");
                }
            try {
                wait();
            }
            catch(InterruptedException ie) {}
            return(getConnection());
        }
    }
    
    
    private void makeBackgroundConnection() {
        connectionPending = true;
        try {
            Thread connectThread = new Thread(this);
            connectThread.start();
        }
        catch(OutOfMemoryError oome) {}
    }
    
    public void run() {
        try {
            Connection connection = makeNewConnection();
            synchronized(this) {
                availableConnections.addElement(connection);
                connectionPending = false;
                notifyAll();
            }
        }
        catch(Exception e) {}
    }
    
    private Connection makeNewConnection()throws SQLException {
        try {
            Class.forName(driver).newInstance();
            Connection connection = DriverManager.getConnection(url, username, password);
            return(connection);
        }
        catch(Exception cnfe) {
            //            throw new SQLException("Can't find class for driver: " + driver);
            System.out.println("ERROR "+cnfe.getMessage());
            try{
                PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("logsPool.txt")));
                ps.println("ERROR : "+cnfe.getMessage());
                cnfe.printStackTrace(ps);
                
                ps.close();
            }
            catch (Exception e){}
            
            
        }
        return null;
    }
    
    public synchronized void free(Connection connection) {
        if(busyConnections.removeElement(connection)){
            availableConnections.addElement(connection);
            System.out.println("****Se esta liberando la conexion "+connection);
        }
        else
            System.out.println("*-*-*-*-*-No encontro a la conexion dentro de los busyConnection ");
        notifyAll();
    }
    
    public synchronized int totalConnections() {
        return(availableConnections.size() +
        busyConnections.size());
    }
    
    public synchronized void closeAllConnections() {
        closeConnections(availableConnections);
        availableConnections = new Vector();
        closeConnections(busyConnections);
        busyConnections = new Vector();
    }
    
    private void closeConnections(Vector connections) {
        try {
            for(int i=0; i<connections.size(); i++) {
                Connection connection =(Connection)connections.elementAt(i);
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        } catch(SQLException sqle) {}
    }
    
    @Override
    public synchronized String toString() {
        String info =  "ConnectionPool(" + url + "," + username + ")" +
        ", available=" + availableConnections.size() +
        ", busy=" + busyConnections.size() +
        ", max=" + maxConnections;
        return(info);
    }
}
