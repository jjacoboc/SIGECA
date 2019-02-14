/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.ConDeclaranteDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ConDeclaranteService extends GenericService{

    public List getListConDeclarante(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ConDeclaranteDAO conDeclarante = new ConDeclaranteDAO(tx);
            lista = conDeclarante.getListConDeclarante();
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
