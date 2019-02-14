/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.common.persistence;

import com.bmp.sigeca.util.BeanUtils;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author jjacobo
 */
public class GenericDAO implements DataAccess {

    protected TransactionContext context;
    protected Logger log;
    public static final String BOOLEAN_FIELD_TRUE_FLAG="1";
    public static final String BOOLEAN_FIELD_FALSE_FLAG="0";
    /** Creates a new instance of GenericDAO */
    public GenericDAO(TransactionContext context) {
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }
    
    public void delete(Object obj) throws DAOException {
    }
    
    public Object findByName(Object obj) throws DAOException {
        return null;
    }
    
    public Object findByPrimaryKey(Object obj) throws DAOException {
        return null;
    }
    
    public void insert(Object obj) throws DAOException {
    }
    
    public void update(Object obj) throws DAOException {
    }
    
    public Object getNextPK(Object obj) throws DAOException {
        return null;
    }
    
    public ArrayList list() throws DAOException {
        return null;
    }
    
   /**
    * Gets an InputStream object that represents the binary data of a LONGVARBINARY field
    *
   **/ 
   public InputStream getBinaryField(String sql, Object o, String optionalProperty) 
        throws DAOException
   {
       if (sql.length()==0) 
           throw new DAOException("Query vacío");
       Class objectClass=o.getClass();
       Statement stmt=null;
       try{
            stmt=context.getConnection().createStatement();
            ResultSet res=stmt.executeQuery(sql);
            if(res.first())
            {   if (optionalProperty!= null)
                {   String aux=res.getString(2);
                    if(res.wasNull()) aux=null;
                    BeanUtils.setProperty(o, optionalProperty, aux);
                }
                InputStream is=res.getBinaryStream(1);
                return is;
            }else throw new DAOException("No se halló registro");            
            /*pstmt.close();
            pstmt = null;*/
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            try{
                if(stmt != null)               
                    stmt.close();
            }catch(Exception ignore) {
            }
            throw new DAOException(e);
        }
   }
   
    public void updateBinaryField(String sql, InputStream is, int length) 
        throws DAOException{
        if (sql.length()==0) 
           throw new DAOException("Query vacío");
        PreparedStatement pstmt=null;
        try{
            pstmt=context.getConnection().prepareStatement(sql);
            pstmt.setBinaryStream(1,is,length);
            int result=pstmt.executeUpdate();
            if (result==0) throw new DAOException("No se actualizó registro"); 
            log.debug(result + " row(s) updated");
            pstmt.close();
            pstmt = null;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            try{
                if(pstmt != null)               
                    pstmt.close();
            }catch(Exception ignore) {
            }
            throw new DAOException(e);
        }
   }
    
    public boolean hasPresentReferences(Object obj) throws DAOException {
        return false;
    }
}
