/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.registro.persistence.CabeceraDAO;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class CabeceraService extends GenericService{

    public HashMap obtenerCabeceraByCUC(HashMap map){
        HashMap mapa = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            CabeceraDAO cabecera = new CabeceraDAO(tx);
            mapa = cabecera.obtenerCabeceraByCUC(map);
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
        return mapa;
    }
}
