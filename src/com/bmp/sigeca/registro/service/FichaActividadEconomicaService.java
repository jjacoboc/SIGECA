/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.registro.persistence.ActividadDAO;
import com.bmp.sigeca.registro.persistence.ActividadFichaDAO;
import com.bmp.sigeca.registro.persistence.AnuncioDAO;
import com.bmp.sigeca.registro.persistence.CabeceraDAO;
import com.bmp.sigeca.registro.persistence.ConductoresDAO;
import com.bmp.sigeca.registro.persistence.DomicilioTitularDAO;
import com.bmp.sigeca.registro.persistence.EdificacionDAO;
import com.bmp.sigeca.registro.persistence.FichaDAO;
import com.bmp.sigeca.registro.persistence.FichasDAO;
import com.bmp.sigeca.registro.persistence.FichasEconomicasDAO;
import com.bmp.sigeca.registro.persistence.FuncionamientoDAO;
import com.bmp.sigeca.registro.persistence.IdeTitCatastralDAO;
import com.bmp.sigeca.registro.persistence.InfComplementariaDAO;
import com.bmp.sigeca.registro.persistence.LoteDAO;
import com.bmp.sigeca.registro.persistence.ManzanaDAO;
import com.bmp.sigeca.registro.persistence.PersonasDAO;
import com.bmp.sigeca.registro.persistence.SectorDAO;
import com.bmp.sigeca.registro.persistence.UniCatDAO;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.util.StringUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class FichaActividadEconomicaService extends GenericService{

    public long guardarFicha(HashMap map){
        long result = 0;
        TransactionContext tx = null;
        String id_ubi_geo = "";
        try{
            tx = new TransactionContext();
            HashMap mapUbigeo = (HashMap)map.get("mapUbigeo");
            id_ubi_geo = mapUbigeo.get("id_ubi_geo").toString().trim();

            Calendar c = Calendar.getInstance();
            String año = Integer.toString(c.get(Calendar.YEAR));
            map.put("id_ubi_geo", id_ubi_geo);
            map.put("id_sector",id_ubi_geo+map.get("sector").toString());
            map.put("id_mzna",map.get("id_sector").toString()+map.get("manzana").toString());
            map.put("id_lote",map.get("id_mzna").toString()+map.get("lote").toString());
            map.put("id_edificacion",map.get("id_lote").toString()+map.get("edifica").toString());
            //map.put("id_hab_urba",id_ubi_geo+map.get("codHUTit").toString());
            map.put("id_ficha",año+id_ubi_geo+map.get("codTipFicha").toString()+map.get("numFicha").toString());
            map.put("id_uni_cat",map.get("id_edificacion").toString()+map.get("entrada").toString()+map.get("piso").toString()+map.get("unidad").toString());

            SectorDAO sector = new SectorDAO(tx);
            if(!sector.existeSector(map)){
                sector.guardarSector(map);
            }else{
                sector.actualizarSector(map);
            }
            ManzanaDAO manzana = new ManzanaDAO(tx);
            if(!manzana.existeManzana(map)){
                manzana.guardarManzana(map);
            }else{
                manzana.actualizarManzana(map);
            }

            LoteDAO lote = new LoteDAO(tx);
            if(!lote.existeLote(map)){
                lote.guardarLote(map);
            }else{
                lote.actualizarLote(map);
            }

            EdificacionDAO edificacion = new EdificacionDAO(tx);
            if(!edificacion.existeEdificacion(map)){
                edificacion.guardarEdificacion(map);
            }else{
                edificacion.actualizarEdificacion(map);
            }

            UniCatDAO unicat = new UniCatDAO(tx);
            if(!unicat.existeUniCat(map)){
                unicat.guardarUniCat(map);
            }else{
                unicat.actualizarUniCat(map);
            }

            PersonasDAO personas = new PersonasDAO(tx);
            HashMap mapPersona = new HashMap();
            String dniDeclarante = map.get("dniDeclarante")!=null?((String)map.get("dniDeclarante")).trim():"";
            if(dniDeclarante!=null && !"".equals(dniDeclarante)){
                mapPersona.put("id_persona", Properties.TipoTitular_PersonaNatural+Properties.TipoDoc_DNI+dniDeclarante);
                mapPersona.put("nro_doc", StringUtil.nullAsEmptyString((String)map.get("dniDeclarante")));
                mapPersona.put("tip_doc", Properties.TipoDoc_DNI);
                mapPersona.put("tip_persona", Properties.TipoTitular_PersonaNatural);
                mapPersona.put("nombres", StringUtil.nullAsEmptyString((String)map.get("nomDeclarante")));
                mapPersona.put("ape_materno", StringUtil.nullAsEmptyString((String)map.get("apeMatDeclarante")));
                mapPersona.put("ape_paterno", StringUtil.nullAsEmptyString((String)map.get("apePatDeclarante")));
                mapPersona.put("tip_persona_juridica", Properties.TipoPersonaJuridica_Otros);

                if(!personas.existePersona(mapPersona)){
                    personas.guardarPersona(mapPersona);
                }else{
                    personas.actualizarPersona(mapPersona);
                }
            }

            map.put("firma_declarante", Properties.Activa);
            map.put("declarante", mapPersona.get("id_persona"));

            FichasDAO fichas = new FichasDAO(tx);
            if(!fichas.existeFichas(map)){
                fichas.guardarFichas(map);
            }else{
                fichas.actualizarFichas(map);
            }

            mapPersona.clear();
            String numDocIdentidad = map.get("numDocIdentidad")!=null?map.get("numDocIdentidad").toString().trim():"";
            String codTipDocIdentidad = map.get("codTipDocIdentidad")!=null?map.get("codTipDocIdentidad").toString().trim():"";
            String codTipTitular = map.get("codTipTitular")!=null?map.get("codTipTitular").toString().trim():"";
            if(codTipTitular.length()>0 && codTipDocIdentidad.length()>0 && numDocIdentidad.length()>0 ){
                String id_persona = numDocIdentidad+codTipDocIdentidad+codTipTitular;
                mapPersona.put("id_persona", id_persona);
                if(codTipTitular!=null  && codTipTitular.equals(Properties.TipoTitular_PersonaNatural)){
                    mapPersona.put("nro_doc", (String)map.get("numDocIdentidad"));
                    mapPersona.put("nombres", (String)map.get("nombre"));
                    mapPersona.put("ape_materno", (String)map.get("apePaterno"));
                    mapPersona.put("ape_paterno", (String)map.get("apeMaterno"));
                    mapPersona.put("tip_persona_juridica", Properties.TipoPersonaJuridica_Otros);
                }else{
                    mapPersona.put("nro_doc", (String)map.get("numRuc"));
                    mapPersona.put("nombres", (String)map.get("razSocial"));
                    mapPersona.put("ape_materno", "");
                    mapPersona.put("ape_paterno", "");
                    mapPersona.put("tip_persona_juridica", (String)map.get("codPerJuridica"));
                }
                mapPersona.put("tip_doc", (String)map.get("codTipDocIdentidad"));
                mapPersona.put("tip_persona", (String)map.get("codTipTitular"));

                if(!personas.existePersona(mapPersona)){
                    personas.guardarPersona(mapPersona);
                }else{
                    personas.actualizarPersona(mapPersona);
                }

                HashMap mapConductor = new HashMap();
                mapConductor.put("id_ficha", (String)map.get("id_ficha"));
                mapConductor.put("id_persona", (String)mapPersona.get("id_persona"));
                mapConductor.put("fax", (String)map.get("faxTit"));
                mapConductor.put("telefono", (String)map.get("telefonoTit"));
                mapConductor.put("anexo", (String)map.get("anexoTit"));
                mapConductor.put("correo_elect", (String)map.get("correoTit"));
                mapConductor.put("condicion_conductor", (String)map.get("codConConductor"));

                ConductoresDAO conductor = new ConductoresDAO(tx);
                if(!conductor.existeConductor(mapConductor)){
                    conductor.guardarConductor(mapConductor);
                }else{
                    conductor.actualizarConductor(mapConductor);
                }

                DomicilioTitularDAO domicilio = new DomicilioTitularDAO(tx);
                if(!domicilio.existeDomicilioTitular(map)){
                    domicilio.guardarDomicilioTitular(map);
                }else{
                    domicilio.actualizarDomicilioTitular(map);
                }
            }

            List listaActividad = (List)map.get("listaActividad");
            if(listaActividad!=null && !listaActividad.isEmpty()){
                for(int i=0;i<listaActividad.size();i++){
                    HashMap mapa = (HashMap)listaActividad.get(i);
                    mapa.put("id_ficha", map.get("id_ficha").toString());
                    ActividadDAO actividad = new ActividadDAO(tx);
                    ActividadFichaDAO actividadficha = new ActividadFichaDAO(tx);
                    if(!actividad.existeActividad(mapa)){
                        actividad.guardarActividad(mapa);
                        actividadficha.guardarActividadFicha(mapa);
                    }else{
                        actividad.actualizarActividad(mapa);
                    }
                }
            }

            List listaAnuncio = (List)map.get("listaAnuncio");
            if(listaAnuncio!=null && !listaAnuncio.isEmpty()){
                for(int i=0;i<listaAnuncio.size();i++){
                    HashMap mapa = (HashMap)listaAnuncio.get(i);
                    mapa.put("id_ficha", map.get("id_ficha").toString());
                    AnuncioDAO anuncio = new AnuncioDAO(tx);
                    if(!anuncio.existeAnuncio(mapa)){
                        anuncio.guardarAnuncio(mapa);
                    }else{
                        anuncio.actualizarAnuncio(mapa);
                    }
                }
            }

            FichasEconomicasDAO fichasEconomicas = new FichasEconomicasDAO(tx);
            if(!fichasEconomicas.existeFichaActividadEconomica(map)){
                fichasEconomicas.guardarFichaActividadEconomica(map);
            }else{
                fichasEconomicas.actualizarFichaActividadEconomica(map);
            }

            result = 1;
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
        return result;
    }

    public int eliminarFicha(HashMap mapCondicion){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            
            InfComplementariaDAO informacion = new InfComplementariaDAO(tx);
            informacion.eliminarInfComplementaria(mapCondicion);

            AnuncioDAO anuncio = new AnuncioDAO(tx);
            anuncio.eliminarAnuncioByFicha(mapCondicion);

            IdeTitCatastralDAO titular = new IdeTitCatastralDAO(tx);
            titular.eliminarIdeTitCatastral(mapCondicion);

            FuncionamientoDAO funcionamiento = new FuncionamientoDAO(tx);
            HashMap mapFuncionamiento = funcionamiento.obtenerFuncionamiento(mapCondicion);
            ActividadDAO actividad = new ActividadDAO(tx);
            //actividad.eliminarActividadByFuncionamiento(mapFuncionamiento);

            funcionamiento.eliminarFuncionamiento(mapCondicion);

            FichaDAO ficha = new FichaDAO(tx);
            ficha.eliminarFicha(mapCondicion);

            CabeceraDAO cabecera = new CabeceraDAO(tx);
            cabecera.eliminarCabecera(mapCondicion);
            
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
        return result;
    }

    public HashMap obtenerFicha(HashMap map){
        TransactionContext tx = null;
        HashMap mapCondicion = new HashMap();
        HashMap fichaIndividual = null;
        try{
            tx = new TransactionContext();
            CabeceraDAO cabecera = new CabeceraDAO(tx);
            HashMap mapCabecera = cabecera.obtenerCabeceraById(map);
            if(mapCabecera!=null && !mapCabecera.isEmpty()){
                mapCondicion.put("cuc", (String)mapCabecera.get("cuc"));
                mapCondicion.put("id_ficha", (String)map.get("id_ficha"));

                FichasDAO ficha = new FichasDAO(tx);
                fichaIndividual = ficha.obtenerFicha(mapCondicion);
                fichaIndividual.put("mapCabecera", mapCabecera);

                if(fichaIndividual!=null && !fichaIndividual.isEmpty()){

                    ConductoresDAO conductor = new ConductoresDAO(tx);
                    HashMap mapConductor = conductor.obtenerConductor(mapCondicion);

                    DomicilioTitularDAO domicilio = new DomicilioTitularDAO(tx);
                    mapConductor.putAll(domicilio.obtenerDomicilioTitular(mapCondicion));

                    PersonasDAO personas = new PersonasDAO(tx);
                    mapCondicion.put("id_persona", (String)mapConductor.get("id_persona"));
                    mapConductor.putAll(personas.obtenerPersona(mapCondicion));
                    fichaIndividual.put("mapConductor", mapConductor);

                    FuncionamientoDAO funcionamiento = new FuncionamientoDAO(tx);
                    HashMap mapFuncionamiento = funcionamiento.obtenerFuncionamiento(mapCondicion);
                    fichaIndividual.put("mapFuncionamiento", mapFuncionamiento);
                    if(mapFuncionamiento!=null && !mapFuncionamiento.isEmpty()){
                        ActividadDAO actividad = new ActividadDAO(tx);
                        List listaActividad = actividad.obtenerActividadByFicha(mapCondicion);
                        fichaIndividual.put("listaActividad", listaActividad);
                    }

                    AnuncioDAO anuncio = new AnuncioDAO(tx);
                    List listaAnuncio = anuncio.obtenerListaAnuncio(mapCondicion);
                    fichaIndividual.put("listaAnuncio", listaAnuncio);

                    fichaIndividual.put("mapInformacion", mapFuncionamiento);

                    FichasEconomicasDAO fichasEconomicas = new FichasEconomicasDAO(tx);
                    fichaIndividual.putAll(fichasEconomicas.obtenerFichaEconomica(mapCondicion));
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
        return fichaIndividual;
    }

    public boolean existeCUC(HashMap map){
        boolean existe = false;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();
            CabeceraDAO cabecera = new CabeceraDAO(tx);
            HashMap mapCabecera = (HashMap)map.get("mapCabecera");
            existe = cabecera.existeCUC(mapCabecera);
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
        return existe;
    }
}