package com.bmp.sigeca.resource;

/**
 *
 * @author admin
 */

import java.sql.*;
import java.util.ResourceBundle;

public class DBConnection {
/*
    static {
	try {
            //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            Class.forName("com.mysql.jdbc.Driver");
	} catch (Exception e) {
            System.out.println(
		"Proyecto: "
		+ Parametros.S_APP_NOMBRE
		+ "; Clase: "
		+ "org.mef.resource.DBConnection"
		+ "; Mensaje:"
		+ e);
	}
    }
*/
    /**
     * Obtiene una conexión a la Base de Datos.
     */
    public static Connection getConnection() {
/*
        Connection connection = null;
	try {
            Context initCtx = new InitialContext();
	    Context myEnv = (Context)initCtx.lookup("java:comp/env");

	    String urlConnection = (String)myEnv.lookup("dao/url");
	    String usuario = (String)myEnv.lookup("dao/usuario");
	    String clave = (String)myEnv.lookup("dao/clave");
            System.out.println(urlConnection);
            System.out.println(usuario);
            System.out.println(clave);
	        
            connection = DriverManager.getConnection(urlConnection,usuario,clave);
        } catch (Exception e) {
            System.out.println(
		"Proyecto: "
		+ Parametros.S_APP_NOMBRE
		+ "; Clase: "
		+ "org.mef.resource.DBConnection"
		+ "; Mensaje:"
		+ e);
	}
	return connection;*/
        try{
            ResourceBundle rb=ResourceBundle.getBundle(Properties.getDBProperties());
            String driver=rb.getString("driver");
            String url=rb.getString("url");
            String user=rb.getString("username");
            String pass=rb.getString("password");
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pass);
            return con;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
