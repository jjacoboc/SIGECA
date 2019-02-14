/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.TablasCodigosDAO;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class TablasCodigosService extends GenericService{

    public List getListaMaestro(String idTabla){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TablasCodigosDAO tablasCodigos = new TablasCodigosDAO(tx);
            lista = tablasCodigos.getListaMaestro(idTabla);
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
        return lista;
    }
}
