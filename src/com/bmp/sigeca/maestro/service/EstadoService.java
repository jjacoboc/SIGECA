/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.maestro.persistence.EstadoDAO;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class EstadoService extends GenericService{

    public List getListEstado(){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            EstadoDAO estado = new EstadoDAO(tx);
            lista = estado.getListEstado();
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
