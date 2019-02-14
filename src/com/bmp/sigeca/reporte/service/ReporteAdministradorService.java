/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.reporte.bean.FichaReporteAdministradorBean;
import com.bmp.sigeca.reporte.persistence.ReporteAdministradorDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Ricardo Avila
 */
public class ReporteAdministradorService {

     public List<FichaReporteAdministradorBean> getLstReporte01Detallado(HashMap map){

        List<FichaReporteAdministradorBean> lstFichaReporte = new ArrayList<FichaReporteAdministradorBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteAdministradorDAO administradorDAO = new ReporteAdministradorDAO(tx);
            lstFichaReporte = administradorDAO.getLstReporte01Detallado(map);
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
        return lstFichaReporte;
    }

      public List<FichaReporteAdministradorBean> getLstReporte02Detallado(HashMap map){

        List<FichaReporteAdministradorBean> lstFichaReporte = new ArrayList<FichaReporteAdministradorBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteAdministradorDAO administradorDAO = new ReporteAdministradorDAO(tx);
            lstFichaReporte = administradorDAO.getLstReporte02Detallado(map);
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
        return lstFichaReporte;
    }

       public List<FichaReporteAdministradorBean> getLstReporte01General(HashMap map){

        List<FichaReporteAdministradorBean> lstFichaReporte = new ArrayList<FichaReporteAdministradorBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteAdministradorDAO administradorDAO = new ReporteAdministradorDAO(tx);
            lstFichaReporte = administradorDAO.getLstReporte01General(map);
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
        return lstFichaReporte;
    }

}
