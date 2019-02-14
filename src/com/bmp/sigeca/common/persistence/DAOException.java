/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.common.persistence;

/**
 *
 * @author jjacobo
 */
public class DAOException extends Exception{

    /**
     * Creates a new instance of <code>DAOException</code> without detail message.
     */
    public DAOException() {
    }    
    
    /**
     * Constructs an instance of <code>DAOException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String message, Throwable cause) {
        //super(message, cause);
    }
    
    public DAOException(Throwable cause) {
        super(cause);
    }

}
