/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.common.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author jjacobo
 */
public class TablaDAO extends GenericDAO{

    /** Creates a new instance of DAO */
    public TablaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }
    
    public Object getNextPK(String nombreSecuenciador) throws DAOException {
        long result = 1;
        String sql = "select "+nombreSecuenciador+".NEXTVAL from DUAL ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = context.getConnection().prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                result = rs.getLong(1);
                result++;
            }
            pstmt.close();
            pstmt = null;
            rs.close();
            rs = null;
            log.debug("PK generated: " + result);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            if(pstmt != null) {
                try {
                    pstmt.close();
                }
                catch(Exception ignore) {
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                }
                catch(Exception ignore) {
                }
            }
            throw new DAOException(e);
        }
        return new Long(result);
    }

    public long getNextPK(String pk, String table) throws DAOException {
        long result = 1;
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT MAX(").append(pk).append(") FROM ").append(table);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = context.getConnection().prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                result = rs.getLong(1);
                result++;
            }
            pstmt.close();
            pstmt = null;
            rs.close();
            rs = null;
            log.debug("PK generated: " + result);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            if(pstmt != null) {
                try {
                    pstmt.close();
                }
                catch(Exception ignore) {
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                }
                catch(Exception ignore) {
                }
            }
            throw new DAOException(e);
        }
        return result;
    }

}
