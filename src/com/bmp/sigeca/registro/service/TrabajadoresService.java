/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.registro.persistence.TrabajadoresDAO;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class TrabajadoresService extends GenericService{

    public List getListTrabajadoresPorCargo(HashMap map){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            TrabajadoresDAO trabajadores = new TrabajadoresDAO(tx);
            lista = trabajadores.getListTrabajadoresPorCargo(map);
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
