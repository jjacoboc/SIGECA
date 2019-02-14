/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.TablasCodigosDAO;
import com.bmp.sigeca.registro.bean.TrabajadorBean;
import com.bmp.sigeca.registro.persistence.CabeceraDAO;
import com.bmp.sigeca.registro.persistence.FichaDAO;
import com.bmp.sigeca.registro.persistence.FichasDAO;
import com.bmp.sigeca.registro.persistence.TrabajadoresDAO;
import com.bmp.sigeca.resource.Properties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class FichaService  extends GenericService{

    public List obtenerListaFicha(HashMap map){
        TransactionContext tx = null;
        List lista = null;
        try{
            tx = new TransactionContext();
            CabeceraDAO cabecera = new CabeceraDAO(tx);
            List listaCabecera = cabecera.obtenerListaCabecera(map);
            if(listaCabecera!=null && !listaCabecera.isEmpty()){
                lista = new ArrayList();
                for(int i=0;i<listaCabecera.size();i++){
                    HashMap mapa = (HashMap)listaCabecera.get(i);
                    FichasDAO fichas = new FichasDAO(tx);
                    HashMap ficha = fichas.obtenerFicha(mapa);
                    if(ficha!=null && !ficha.isEmpty()){
                        TrabajadoresDAO trabajadores = new TrabajadoresDAO(tx);
                        TrabajadorBean trabajador = trabajadores.getListTrabajadorPorId((String)ficha.get("idTecnico"));
                        ficha.put("dniTecnico", trabajador.getDni());
                        ficha.put("nomTecCatastral", trabajador.getNombres());
                        ficha.put("apeTecCatastral", trabajador.getApe_paterno()+" "+trabajador.getApe_materno());

                        TablasCodigosDAO tablas = new TablasCodigosDAO(tx);
                        HashMap mapaTabla = tablas.obtenerTablasCodigosById((String)ficha.get("codEstado"));
                        ficha.put("desTipFicha", (String)mapaTabla.get("descripcion"));

                        ficha.put("mapCabecera", mapa);
                        lista.add(ficha);
                    }
                }
            }

            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            if(tx != null) {
                try {
                    tx.rollback();
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return lista;
    }

    public List buscarFichas(HashMap map){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            FichasDAO fichas = new FichasDAO(tx);
            lista = fichas.buscarFichas(map);
            if(lista!=null && !lista.isEmpty()){
                for(int i=0;i<lista.size();i++){
                    HashMap ficha = (HashMap)lista.get(i);
                    if(ficha.get("idTecnico")!=null && !"".equals((String)ficha.get("idTecnico"))){
                        TrabajadoresDAO trabajadores = new TrabajadoresDAO(tx);
                        TrabajadorBean trabajador = trabajadores.getListTrabajadorPorId(((String)ficha.get("idTecnico")).trim());
                        if(trabajador!=null){
                            ficha.put("dniTecCatastral", trabajador.getDni());
                            ficha.put("nomTecCatastral", trabajador.getNombres());
                            ficha.put("apeTecCatastral", trabajador.getApe_paterno()+" "+trabajador.getApe_materno());
                        }
                    }
                    if(ficha.get("codEstado")!=null && !"".equals((String)ficha.get("codEstado"))){
                        TablasCodigosDAO tablas = new TablasCodigosDAO(tx);
                        HashMap mapaTabla = tablas.obtenerTablaCodigo(Properties.ESTADO_FICHA,(String)ficha.get("codEstado"));
                        if(mapaTabla!=null)
                            ficha.put("desTipFicha", (String)mapaTabla.get("descripcion"));
                    }
                }
            }
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
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

    public List reporteFichas(HashMap map){
        List lista = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            FichaDAO ficha = new FichaDAO(tx);
            lista = ficha.reporteFichas(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
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

    public HashMap obtenerFicha(HashMap map){
        HashMap mapFicha = null;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            FichaDAO ficha = new FichaDAO(tx);
            mapFicha = ficha.obtenerFicha(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return mapFicha;
    }

    public int eliminarFicha(HashMap map){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            FichaDAO ficha = new FichaDAO(tx);
            result = ficha.eliminarFicha(map);
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
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

    public String getLastNumFicha(){
        String numFicha = "";
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            FichaDAO ficha = new FichaDAO(tx);
            numFicha = ficha.getLastNumFicha();
            tx.close();
            tx = null;
        }catch(Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            if(tx != null) {
                try {
                    tx.close();
                    tx = null;
                }catch(Exception ignore){
                }
            }
        }
        return numFicha;
    }
}
