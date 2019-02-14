/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.UsoPreRuralDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class UsoPreRuralService extends GenericService{

    public List getListUsoPreRural(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            UsoPreRuralDAO usoPreRural = new UsoPreRuralDAO(tx);
            lista = usoPreRural.getListUsoPreRural();
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
