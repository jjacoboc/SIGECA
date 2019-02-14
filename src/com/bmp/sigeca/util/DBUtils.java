package com.bmp.sigeca.util;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.persistence.TransactionSysException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBUtils
{
    private static final Logger logger = Logger.getLogger(DBUtils.class.getName());
    
    public static void executeQuery(String sql, TransactionContext context, RsCallback rsCallback) throws SQLException, TransactionSysException
    {
        executeQuery(sql, context, rsCallback, null);
    }

    public static void executeQuery(String sql, TransactionContext context, RsCallback rsCallback, Object[] params) throws SQLException, TransactionSysException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = context.getConnection().prepareStatement(sql);

            setParams(pstmt, params);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                rsCallback.nextResult(rs);
            }
        }
        finally
        {
            DBUtils.closeResources(pstmt, rs);
        }
    }
    
    private static void setParams(PreparedStatement pstmt, Object[] params) throws SQLException
    {
        if (params != null)
        {
            for (int i = 0; i < params.length; i++)
            {
                Object param = params[i];
                if (param instanceof String)
                {
                    pstmt.setString(i + 1, (String) param);
                }
                else if (param instanceof Integer)
                {
                    pstmt.setInt(i + 1, ((Integer) param).intValue());
                }
                else
                {
                    throw new RuntimeException("Paramter of type '"+param.getClass()+"' not currently supported.");
                }
            }
        }
    }
    
    /**
     * Cierra los recursos usados para el acceso a datos.
     * 
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void closeResources(Statement stmt, ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                logger.log(Level.WARNING, "Could not close ResultSet", e);
            }
        }
        if (stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException e)
            {
                logger.log(Level.WARNING, "Could not close Statement", e);
            }
        }
    }
    
    /**
     * 
     * Callback para leer los resultados en un ResultSet.
     * Nota, no deberia llamar a rs.next, el metodo executeQuery es el responsable
     * de hacer la iteracion. 
     */
    public static interface RsCallback
    {
        public void nextResult(ResultSet rs) throws SQLException;
    }

    public static void executeTransactionDao(CallTransaction callTransaction) throws SQLException
    {
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            callTransaction.callOperation(tx);
            tx.close();
            tx = null;
        }catch(Exception e){
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){}
            }
            throw new SQLException(e);
        }
    }

    public static interface CallTransaction {
        public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException;
    }
}
