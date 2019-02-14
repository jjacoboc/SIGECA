/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.TablasCodigosDAO;
import com.bmp.sigeca.registro.persistence.CabeceraDAO;
import com.bmp.sigeca.registro.persistence.DomicilioTitularDAO;
import com.bmp.sigeca.registro.persistence.EdificacionDAO;
import com.bmp.sigeca.registro.persistence.ExoneracionesTitularDAO;
import com.bmp.sigeca.registro.persistence.FichaDAO;
import com.bmp.sigeca.registro.persistence.FichasCotitularesDAO;
import com.bmp.sigeca.registro.persistence.FichasDAO;
import com.bmp.sigeca.registro.persistence.HabUrbaDAO;
import com.bmp.sigeca.registro.persistence.IdeTitCatastralDAO;
import com.bmp.sigeca.registro.persistence.InfComplementariaDAO;
import com.bmp.sigeca.registro.persistence.LoteDAO;
import com.bmp.sigeca.registro.persistence.ManzanaDAO;
import com.bmp.sigeca.registro.persistence.PersonasDAO;
import com.bmp.sigeca.registro.persistence.SectorDAO;
import com.bmp.sigeca.registro.persistence.TitularesDAO;
import com.bmp.sigeca.registro.persistence.UniCatDAO;
import com.bmp.sigeca.registro.persistence.ViaDAO;
import com.bmp.sigeca.registro.persistence.ViaHabUrbDAO;
import com.bmp.sigeca.resource.Properties;
import com.bmp.sigeca.util.StringUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class FichaCotitularidadService extends GenericService{

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
//            map.put("id_hab_urba",id_ubi_geo+map.get("codHUTit").toString());
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
            String dniDeclarante = ((String)map.get("dniDeclarante")).trim();
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
//++++++++++++++++++++++++++++++++
            List listaCotitulares = (List)map.get("listaCotitulares");
            if(listaCotitulares!=null && !listaCotitulares.isEmpty()){
                for(int i=0;i<listaCotitulares.size();i++){
                    HashMap mapa = (HashMap)listaCotitulares.get(i);
                    mapa.put("id_ficha", map.get("id_ficha"));
                    mapPersona.clear();
                    mapPersona.put("id_persona", (String)mapa.get("codTipTitular")+(String)mapa.get("codTipDocIdentidad")+(String)mapa.get("numDocIdentidad"));
                    String codTipTitular = (String)mapa.get("codTipTitular");
                    if(codTipTitular!=null  && codTipTitular.equals(Properties.TipoTitular_PersonaNatural)){
                        mapPersona.put("nro_doc", (String)mapa.get("numDocIdentidad"));
                        mapPersona.put("nombres", (String)mapa.get("nombre"));
                        mapPersona.put("ape_materno", (String)mapa.get("apePaterno"));
                        mapPersona.put("ape_paterno", (String)mapa.get("apeMaterno"));
                        mapPersona.put("tip_persona_juridica", Properties.TipoPersonaJuridica_Otros);
                    }else{
                        mapPersona.put("nro_doc", (String)mapa.get("numRuc"));
                        mapPersona.put("nombres", (String)mapa.get("razSocial"));
                        mapPersona.put("ape_materno", "");
                        mapPersona.put("ape_paterno", "");
                        mapPersona.put("tip_persona_juridica", (String)mapa.get("codPerJuridica"));
                    }
                    mapPersona.put("tip_doc", (String)mapa.get("codTipDocIdentidad"));
                    mapPersona.put("tip_persona", (String)mapa.get("codTipTitular"));

                    if(!personas.existePersona(mapPersona)){
                        personas.guardarPersona(mapPersona);
                    }else{
                        personas.actualizarPersona(mapPersona);
                    }

//                    HashMap mapVia = new HashMap();
//                    mapVia.put("id_via", (String)mapa.get("codDepartamento")+(String)mapa.get("codProvincia")+(String)mapa.get("codDistrito")+(String)mapa.get("codViaTit"));
//                    mapVia.put("id_ubi_geo", (String)mapa.get("codDepartamento")+(String)mapa.get("codProvincia")+(String)mapa.get("codDistrito"));
//                    mapVia.put("nomVia", (String)mapa.get("nomViaTit"));
//                    mapVia.put("codTipVia", (String)mapa.get("codTipViaTit"));
//                    mapVia.put("codVia", (String)mapa.get("codViaTit"));
//                    mapVia.put("id_sys_via", Properties.ZERO);
//
//                    ViaDAO via = new ViaDAO(tx);
//                    if(!via.existeVia(mapVia) && mapVia.get("id_ubi_geo")!=null && !"".equals((String)mapVia.get("id_ubi_geo"))){
//                        via.guardarVia(mapVia);
//                    }else{
//                        via.actualizarVia(mapVia);
//                    }
//
//                    HashMap mapHabUrb = new HashMap();
//                    mapHabUrb.put("id_hab_urba", (String)mapa.get("codDepartamento")+(String)mapa.get("codProvincia")+(String)mapa.get("codDistrito")+mapa.get("codHUTit").toString());
//                    mapHabUrb.put("grupo_urba", (String)mapa.get("sectorTit"));
//                    mapHabUrb.put("nom_hab_urba", (String)mapa.get("nomHUTit"));
//                    mapHabUrb.put("tip_hab_urba", null);
//                    mapHabUrb.put("cod_hab_urba", (String)mapa.get("codHUTit"));
//                    mapHabUrb.put("id_ubi_geo", (String)mapa.get("codDepartamento")+(String)mapa.get("codProvincia")+(String)mapa.get("codDistrito"));
//
//                    HabUrbaDAO haburba = new HabUrbaDAO(tx);
//                    if(!haburba.existeHabilitacionUrbana(mapHabUrb)){
//                        haburba.guardarHabilitacionUrbana(mapHabUrb);
//                    }else{
//                        haburba.actualizarHabilitacionUrbana(mapHabUrb);
//                    }
//
//                    HashMap mapViaHabUrba = new HashMap();
//                    mapViaHabUrba.put("id_via", (String)mapVia.get("id_via"));
//                    mapViaHabUrba.put("id_hab_urba", (String)mapHabUrb.get("id_hab_urba"));
//
//                    ViaHabUrbDAO viaHabUrb = new ViaHabUrbDAO(tx);
//                    if(!viaHabUrb.existeViaHabUrba(mapViaHabUrba) && mapViaHabUrba.get("id_ubi_geo")!=null && !"".equals((String)mapViaHabUrba.get("id_ubi_geo"))){
//                        viaHabUrb.guardarViaHabUrba(mapViaHabUrba);
//                    }

                    ExoneracionesTitularDAO exoneracionesTitular = new ExoneracionesTitularDAO(tx);
                    if(!exoneracionesTitular.existeExoneracionesTitular(mapa)){
                        exoneracionesTitular.guardarExoneracionesTitular(mapa);
                    }else{
                        exoneracionesTitular.actualizarExoneracionesTitular(mapa);
                    }

                    mapa.put("fecAdquisicion", mapa.get("fecAdqTitular"));
                    mapa.put("codForAdqPredio", mapa.get("codForAdqTitular"));
                    TitularesDAO titulares = new TitularesDAO(tx);
                    if(!titulares.existeTitular(mapa)){
                        titulares.guardarTitular(mapa);
                    }else{
                        titulares.actualizarTitular(mapa);
                    }

                    DomicilioTitularDAO domicilio = new DomicilioTitularDAO(tx);
                    if(!domicilio.existeDomicilioTitular(mapa)){
                        domicilio.guardarDomicilioTitular(mapa);
                    }else{
                        domicilio.actualizarDomicilioTitular(mapa);
                    }
                }
            }

            FichasCotitularesDAO fichasCotitulares = new FichasCotitularesDAO(tx);
            if(!fichasCotitulares.existeFichaCotitular(map)){
                fichasCotitulares.guardarFichaCotitular(map);
            }else{
                fichasCotitulares.actualizarFichaCotitular(map);
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

            IdeTitCatastralDAO titular = new IdeTitCatastralDAO(tx);
            titular.eliminarIdeTitCatastral(mapCondicion);

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

                    TitularesDAO titulares = new TitularesDAO(tx);
                    List listaCotitulares = titulares.obtenerListaCotitular(mapCondicion);

                    if(listaCotitulares!=null && !listaCotitulares.isEmpty()){
                        for(int i=0;i<listaCotitulares.size();i++){
                            HashMap cotitular = (HashMap)listaCotitulares.get(i);
                            String codTipoDocumento = (String)cotitular.get("codTipDocIdentidad");
                            TablasCodigosDAO tablas = new TablasCodigosDAO(tx);
                            HashMap mapaTabla = tablas.obtenerTablaCodigo(Properties.TIPO_DOCUMENTO,codTipoDocumento);
                            if(mapaTabla!=null)
                                cotitular.put("desTipDocIdentidad", (String)mapaTabla.get("descripcion"));
                        }
                    }

                    fichaIndividual.put("listaCotitulares", listaCotitulares);

                    FichasCotitularesDAO fichasCotitulares = new FichasCotitularesDAO(tx);
                    HashMap mapInformacion = fichasCotitulares.obtenerFichaCotitular(mapCondicion);
                    fichaIndividual.put("mapInformacion", mapInformacion);

                    fichaIndividual.put("observacion", (String)mapInformacion.get("observacion"));
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
