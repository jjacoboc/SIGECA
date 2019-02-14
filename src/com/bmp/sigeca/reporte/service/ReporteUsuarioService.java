/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.reporte.bean.FichaReporteUsuarioBean;
import com.bmp.sigeca.reporte.persistence.ReporteUsuarioDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Ricardo Avila
 */
public class ReporteUsuarioService {

     public List<FichaReporteUsuarioBean> getLstReportePorUbicacion(HashMap map){

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteUsuarioDAO usuarioDAO = new ReporteUsuarioDAO(tx);
            lstFichaReporte = usuarioDAO.getLstReportePorUbicacion(map);
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

      public List<FichaReporteUsuarioBean> getLstReportePorTitularesArea(HashMap map){

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteUsuarioDAO usuarioDAO = new ReporteUsuarioDAO(tx);
            lstFichaReporte = usuarioDAO.getLstReportePorTitularesArea(map);
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

       public List<FichaReporteUsuarioBean> getLstReportePorDocumento(HashMap map){

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteUsuarioDAO usuarioDAO = new ReporteUsuarioDAO(tx);
            lstFichaReporte = usuarioDAO.getLstReportePorDocumento(map);
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

   public List<FichaReporteUsuarioBean> getLstReportePorCodigo(HashMap map){

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteUsuarioDAO usuarioDAO = new ReporteUsuarioDAO(tx);
            lstFichaReporte = usuarioDAO.getLstReportePorCodigo(map);
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

    public List<FichaReporteUsuarioBean> getLstReportePorUbicacionPredio(HashMap map){

        List<FichaReporteUsuarioBean> lstFichaReporte = new ArrayList<FichaReporteUsuarioBean>();
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            ReporteUsuarioDAO usuarioDAO = new ReporteUsuarioDAO(tx);
            lstFichaReporte = usuarioDAO.getLstReportePorUbicacionPredio(map);
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


    //METODOS PARA CARGAR LOS COMBOS

}
