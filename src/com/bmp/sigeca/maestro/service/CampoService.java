/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.CampoDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class CampoService extends GenericService{

    public List getListaCampos(HashMap map){
        List result = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            CampoDAO campo = new CampoDAO(tx);
            result = campo.getListaCampos(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return result;
    }

    public List getDataMaestro(StringBuffer sql){
        List result = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            CampoDAO campo = new CampoDAO(tx);
            result = campo.getDataMaestro(sql);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return result;
    }
}
