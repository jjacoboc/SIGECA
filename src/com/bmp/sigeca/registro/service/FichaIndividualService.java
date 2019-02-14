/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.mantenimiento.persistence.TablasCodigosDAO;
import com.bmp.sigeca.registro.persistence.BienComunDAO;
import com.bmp.sigeca.registro.persistence.CabeceraDAO;
import com.bmp.sigeca.registro.persistence.ConstruccionDAO;
import com.bmp.sigeca.registro.persistence.DocumentoDAO;
import com.bmp.sigeca.registro.persistence.DocumentoAdjuntoDAO;
import com.bmp.sigeca.registro.persistence.DomicilioTitularDAO;
import com.bmp.sigeca.registro.persistence.EdificacionDAO;
import com.bmp.sigeca.registro.persistence.ExoneracionesPredioDAO;
import com.bmp.sigeca.registro.persistence.ExoneracionesTitularDAO;
import com.bmp.sigeca.registro.persistence.FichaDAO;
import com.bmp.sigeca.registro.persistence.FichasDAO;
import com.bmp.sigeca.registro.persistence.FichasIndividualesDAO;
import com.bmp.sigeca.registro.persistence.HabUrbaDAO;
import com.bmp.sigeca.registro.persistence.IdeTitCatastralDAO;
import com.bmp.sigeca.registro.persistence.InfComplementariaDAO;
import com.bmp.sigeca.registro.persistence.IngresosDAO;
import com.bmp.sigeca.registro.persistence.InsRegPredioDAO;
import com.bmp.sigeca.registro.persistence.InstalacionDAO;
import com.bmp.sigeca.registro.persistence.LinderosDAO;
import com.bmp.sigeca.registro.persistence.LitiganteDAO;
import com.bmp.sigeca.registro.persistence.LoteDAO;
import com.bmp.sigeca.registro.persistence.ManzanaDAO;
import com.bmp.sigeca.registro.persistence.NotariaDAO;
import com.bmp.sigeca.registro.persistence.ObrComplementariaDAO;
import com.bmp.sigeca.registro.persistence.PersonasDAO;
import com.bmp.sigeca.registro.persistence.PredioDAO;
import com.bmp.sigeca.registro.persistence.PuertaDAO;
import com.bmp.sigeca.registro.persistence.RegistroLegalDAO;
import com.bmp.sigeca.registro.persistence.SectorDAO;
import com.bmp.sigeca.registro.persistence.ServiciosBasicosDAO;
import com.bmp.sigeca.registro.persistence.SunarpDAO;
import com.bmp.sigeca.registro.persistence.SysDireccionesDAO;
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
 * @author Jonatan Jacobo
 */
public class FichaIndividualService extends GenericService{

    public FichaIndividualService(){
        super();
    }

    public long guardarFicha(HashMap map){
        long result = 0;
        TransactionContext tx = null;
        String id_ubi_geo = "";
        try{
            tx = new TransactionContext();
            HashMap mapUbigeo = (HashMap)map.get("mapUbigeo");
            id_ubi_geo = mapUbigeo.get("id_ubi_geo").toString();

            Calendar c = Calendar.getInstance();
            String año = Integer.toString(c.get(Calendar.YEAR));
            map.put("id_ubi_geo", id_ubi_geo);
            map.put("id_sector",id_ubi_geo+map.get("sector").toString());
            map.put("id_mzna",map.get("id_sector").toString()+map.get("manzana").toString());
            map.put("id_lote",map.get("id_mzna").toString()+map.get("lote").toString());
            map.put("id_edificacion",map.get("id_lote").toString()+map.get("edifica").toString());
            map.put("id_hab_urba",id_ubi_geo+map.get("codHUPre").toString());
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

            HashMap mapHabUrb = new HashMap();
            mapHabUrb.put("id_hab_urba", id_ubi_geo+(String)map.get("codHUPre"));
            mapHabUrb.put("grupo_urba", (String)map.get("sectorPre"));
            mapHabUrb.put("nom_hab_urb", (String)map.get("nomHUPre"));
            mapHabUrb.put("tip_hab_urba", map.get("tip_hab_urba")!=null?(String)map.get("tip_hab_urba"):"");
            mapHabUrb.put("cod_hab_urba", (String)map.get("codHUPre"));
            mapHabUrb.put("id_ubi_geo", id_ubi_geo);

            HabUrbaDAO haburba = new HabUrbaDAO(tx);
            if(!haburba.existeHabilitacionUrbana(mapHabUrb)){
                haburba.guardarHabilitacionUrbana(mapHabUrb);
            }else{
                haburba.actualizarHabilitacionUrbana(mapHabUrb);
            }

            HashMap mapSysDir = new HashMap();
            mapSysDir.put("id_ubi_geo", id_ubi_geo);
            mapSysDir.put("referencia", "");
            mapSysDir.put("nro_interior", (String)map.get("numInteriorPre"));
            mapSysDir.put("sub_lote_muni", (String)map.get("sublotePre"));
            mapSysDir.put("lote_muni", (String)map.get("lotePre"));
            mapSysDir.put("mzna_muni", (String)map.get("manzanaPre"));
            mapSysDir.put("id_hab_urba", (String)mapHabUrb.get("id_hab_urba"));
            if(map.containsKey("sys_dir"))
                mapSysDir.put("sys_dir", ((String)map.get("sys_dir")).trim());
            SysDireccionesDAO sysDir = new SysDireccionesDAO(tx);
            if(!sysDir.existeSysDirecciones(mapSysDir)){
                mapSysDir.put("sys_dir", Long.toString(sysDir.getPK()));
                sysDir.guardarSysDirecciones(mapSysDir);
                map.put("sys_dir", (String)mapSysDir.get("sys_dir"));
            }else{
                sysDir.actualizarSysDirecciones(mapSysDir);
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

            List listaVia = (List)map.get("listaVia");
            if(listaVia!=null && !listaVia.isEmpty()){
                for(int i=0;i<listaVia.size();i++){
                    HashMap mapa = (HashMap)listaVia.get(i);
                    mapa.put("id_via", id_ubi_geo+(String)mapa.get("codVia"));
                    mapa.put("id_hab_urba", (String)mapHabUrb.get("id_hab_urba"));
                    ViaDAO via = new ViaDAO(tx);
                    if(!via.existeVia(mapa)){
                        via.guardarVia(mapa);
                    }else{
                        via.actualizarVia(mapa);
                    }
                    ViaHabUrbDAO viaHabUrb = new ViaHabUrbDAO(tx);
                    if(!viaHabUrb.existeViaHabUrba(mapa)){
                        viaHabUrb.guardarViaHabUrba(mapa);
                    }

                    HashMap mapPuerta = new HashMap();
                    mapPuerta.put("id_puerta", (String)map.get("id_lote")+(String)mapa.get("numMunicipal"));
                    mapPuerta.put("cod_puerta", null);
                    mapPuerta.put("nro_muni", (String)mapa.get("numMunicipal"));
                    mapPuerta.put("tip_puerta", (String)mapa.get("codTipPuerta"));
                    mapPuerta.put("id_lote", (String)map.get("id_lote"));
                    mapPuerta.put("condicion_nro", (String)mapa.get("codConNumeracion"));
                    mapPuerta.put("nro_certificacion", (String)mapa.get("numCerNumeracion"));
                    mapPuerta.put("id_via", (String)mapa.get("id_via"));
                    PuertaDAO puerta = new PuertaDAO(tx);
                    if(!puerta.existePuerta(mapPuerta)){
                        puerta.guardarPuerta(mapPuerta);
                    }else{
                        puerta.actualizarPuerta(mapPuerta);
                    }

                    HashMap mapIngreso = new HashMap();
                    mapIngreso.put("id_ficha", (String)map.get("id_ficha"));
                    mapIngreso.put("id_puerta", (String)mapPuerta.get("id_puerta"));
                    IngresosDAO ingreso = new IngresosDAO(tx);
                    if(!ingreso.existeIngresos(mapIngreso)){
                        ingreso.guardarIngresos(mapIngreso);
                    }
                }
            }

            mapPersona.clear();
            mapPersona.put("id_persona", (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad")+(String)map.get("numDocIdentidad"));
            String codTipTitular = (String)map.get("codTipTitular");
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

            mapPersona.clear();
            if(codTipTitular!=null  && codTipTitular.equals(Properties.TipoTitular_PersonaNatural)){
                String id_persona = (String)map.get("codTipTitular")+(String)map.get("codTipDocIdentidad2")+(String)map.get("numDocIdentidad2");
                mapPersona.put("id_persona", id_persona);
                mapPersona.put("tip_doc", (String)map.get("codTipDocIdentidad2"));
                mapPersona.put("nro_doc", (String)map.get("numDocIdentidad2"));
                mapPersona.put("nombres", (String)map.get("nombre2"));
                mapPersona.put("ape_materno", (String)map.get("apePaterno2"));
                mapPersona.put("ape_paterno", (String)map.get("apeMaterno2"));
                mapPersona.put("tip_persona", (String)map.get("codTipTitular"));
                mapPersona.put("tip_persona_juridica", Properties.TipoPersonaJuridica_Otros);
                mapPersona.put("id_ficha", (String)map.get("id_ficha"));
                if(id_persona!=null && !"".equals(id_persona) && id_persona.length()>3){
                    if(!personas.existePersona(mapPersona)){
                        personas.guardarPersona(mapPersona);
                    }else{
                        personas.actualizarPersona(mapPersona);
                    }
                    mapPersona.put("codTipTitular", (String)map.get("codTipTitular"));
                    mapPersona.put("codTipDocIdentidad", (String)map.get("codTipDocIdentidad2"));
                    mapPersona.put("numDocIdentidad", (String)map.get("numDocIdentidad2"));
                    TitularesDAO titulares = new TitularesDAO(tx);
                    if(!titulares.existeTitular(mapPersona)){
                        titulares.guardarTitular(mapPersona);
                    }else{
                        titulares.actualizarTitular(mapPersona);
                    }
                }
            }

            HashMap mapVia = new HashMap();
            mapVia.put("id_via", (String)map.get("codDepartamento")+(String)map.get("codProvincia")+(String)map.get("codDistrito")+(String)map.get("codVia"));
            mapVia.put("id_ubi_geo", (String)map.get("codDepartamento")+(String)map.get("codProvincia")+(String)map.get("codDistrito"));
            mapVia.put("nomVia", (String)map.get("nomVia"));
            mapVia.put("codTipVia", (String)map.get("codTipVia"));
            mapVia.put("codVia", (String)map.get("codVia"));
            mapVia.put("id_sys_via", Properties.ZERO);

            ViaDAO via = new ViaDAO(tx);
            if(!via.existeVia(mapVia) && mapVia.get("id_ubi_geo")!=null && !"".equals((String)mapVia.get("id_ubi_geo"))){
                via.guardarVia(mapVia);
            }else{
                via.actualizarVia(mapVia);
            }

            mapHabUrb.clear();
            mapHabUrb.put("id_hab_urba", (String)map.get("codDepartamento")+(String)map.get("codProvincia")+(String)map.get("codDistrito")+map.get("codHUTit").toString());
            mapHabUrb.put("grupo_urba", (String)map.get("sectorTit"));
            mapHabUrb.put("nom_hab_urba", (String)map.get("nomHUTit"));
            mapHabUrb.put("tip_hab_urba", null);
            mapHabUrb.put("cod_hab_urba", (String)map.get("codHUTit"));
            mapHabUrb.put("id_ubi_geo", (String)map.get("codDepartamento")+(String)map.get("codProvincia")+(String)map.get("codDistrito"));

            if(!haburba.existeHabilitacionUrbana(mapHabUrb)){
                haburba.guardarHabilitacionUrbana(mapHabUrb);
            }else{
                haburba.actualizarHabilitacionUrbana(mapHabUrb);
            }

            HashMap mapViaHabUrba = new HashMap();
            mapViaHabUrba.put("id_via", (String)mapVia.get("id_via"));
            mapViaHabUrba.put("id_hab_urba", (String)mapHabUrb.get("id_hab_urba"));

            ViaHabUrbDAO viaHabUrb = new ViaHabUrbDAO(tx);
            if(!viaHabUrb.existeViaHabUrba(mapViaHabUrba) && mapViaHabUrba.get("id_ubi_geo")!=null && !"".equals((String)mapViaHabUrba.get("id_ubi_geo"))){
                viaHabUrb.guardarViaHabUrba(mapViaHabUrba);
            }

            ExoneracionesTitularDAO exoneracionesTitular = new ExoneracionesTitularDAO(tx);
            if(!exoneracionesTitular.existeExoneracionesTitular(map)){
                exoneracionesTitular.guardarExoneracionesTitular(map);
            }else{
                exoneracionesTitular.actualizarExoneracionesTitular(map);
            }

            TitularesDAO titulares = new TitularesDAO(tx);
            if(!titulares.existeTitular(map)){
                titulares.guardarTitular(map);
            }else{
                titulares.actualizarTitular(map);
            }

            DomicilioTitularDAO domicilio = new DomicilioTitularDAO(tx);
            if(!domicilio.existeDomicilioTitular(map)){
                domicilio.guardarDomicilioTitular(map);
            }else{
                domicilio.actualizarDomicilioTitular(map);
            }

            ExoneracionesPredioDAO exoneracionesPredio = new ExoneracionesPredioDAO(tx);
            if(!exoneracionesPredio.existeExoneracionesPredio(map)){
                exoneracionesPredio.guardarExoneracionesPredio(map);
            }else{
                exoneracionesPredio.actualizarExoneracionesPredio(map);
            }

            FichasIndividualesDAO fichasIndividuales = new FichasIndividualesDAO(tx);
            if(!fichasIndividuales.existeFichaIndividual(map)){
                fichasIndividuales.guardarFichaIndividual(map);
            }else{
                fichasIndividuales.actualizarFichaIndividual(map);
            }

            LinderosDAO linderos = new LinderosDAO(tx);
            if(!linderos.existeLinderos(map)){
                linderos.guardarLinderos(map);
            }else{
                linderos.actualizarLinderos(map);
            }

            ServiciosBasicosDAO serviciosBasicos = new ServiciosBasicosDAO(tx);
            if(!serviciosBasicos.existeServiciosBasicos(map)){
                serviciosBasicos.guardarServiciosBasicos(map);
            }else{
                serviciosBasicos.actualizarServiciosBasicos(map);
            }

            List listaConstruccion = (List)map.get("listaConstruccion");
            if(listaConstruccion!=null && !listaConstruccion.isEmpty()){
                for(int i=0;i<listaConstruccion.size();i++){
                    HashMap mapa = (HashMap)listaConstruccion.get(i);
                    mapa.put("id_ficha", map.get("id_ficha").toString());
                    mapa.put("nro_registro", null);
                    ConstruccionDAO construccion = new ConstruccionDAO(tx);
                    if(!mapa.containsKey("id_construccion") || (mapa.containsKey("id_construccion") && "".equals((String)mapa.get("id_construccion")))){
                        mapa.put("id_construccion", construccion.getPK());
                    }
                    if(!construccion.existeConstruccion(mapa)){
                        construccion.guardarConstruccion(mapa);
                    }else{
                        construccion.actualizarConstruccion(mapa);
                    }
                }
            }

            List listaObra = (List)map.get("listaObra");
            if(listaObra!=null && !listaObra.isEmpty()){
                for(int i=0;i<listaObra.size();i++){
                    HashMap mapa = (HashMap)listaObra.get(i);
                    mapa.put("id_ficha", map.get("id_ficha").toString());
                    mapa.put("nro_registro", "");
                    InstalacionDAO instalacion = new InstalacionDAO(tx);
                    if(!instalacion.existeInstalacion(mapa)){
                        instalacion.guardarInstalacion(mapa);
                    }else{
                        instalacion.actualizarInstalacion(mapa);
                    }
                }
            }

            List listaDocumento = (List)map.get("listaDocumento");
            if(listaDocumento!=null && !listaDocumento.isEmpty()){
                for(int i=0;i<listaDocumento.size();i++){
                    HashMap mapa = (HashMap)listaDocumento.get(i);
                    mapa.put("id_ficha", map.get("id_ficha").toString());
                    DocumentoAdjuntoDAO documento = new DocumentoAdjuntoDAO(tx);
                    if(!documento.existeDocumentoAdjunto(mapa)){
                        documento.guardarDocumentoAdjunto(mapa);
                    }else{
                        documento.actualizarDocumentoAdjunto(mapa);
                    }
                }
            }

            RegistroLegalDAO registroLegal = new RegistroLegalDAO(tx);
            if(!registroLegal.existeRegistroLegal(map)){
                registroLegal.guardarRegistroLegal(map);
            }else{
                registroLegal.actualizarRegistroLegal(map);
            }

            SunarpDAO sunarp = new SunarpDAO(tx);
            if(!sunarp.existeSunarp(map)){
                sunarp.guardarSunarp(map);
            }else{
                sunarp.actualizarSunarp(map);
            }

            List listaLitigante = (List)map.get("listaLitigante");
            if(listaLitigante!=null && !listaLitigante.isEmpty()){
                for(int i=0;i<listaLitigante.size();i++){
                    HashMap mapa = (HashMap)listaLitigante.get(i);
                    mapa.put("id_ficha", map.get("id_ficha").toString());

                    mapPersona.clear();
                    mapPersona.put("id_persona", Properties.TipoTitular_PersonaNatural+(String)mapa.get("codTipDocIdentidad")+(String)mapa.get("numDocumento"));
                    mapPersona.put("nro_doc", (String)mapa.get("numDocumento"));
                    mapPersona.put("tip_doc", (String)mapa.get("codTipDocIdentidad"));
                    mapPersona.put("tip_persona", Properties.TipoTitular_PersonaNatural);
                    mapPersona.put("nombres", (String)mapa.get("nomLitigante"));
                    mapPersona.put("ape_materno", (String)mapa.get("apePatLitigante"));
                    mapPersona.put("ape_paterno", (String)mapa.get("apeMatLitigante"));
                    mapPersona.put("tip_persona_juridica", Properties.TipoPersonaJuridica_Otros);

                    if(!personas.existePersona(mapPersona)){
                        personas.guardarPersona(mapPersona);
                    }else{
                        personas.actualizarPersona(mapPersona);
                    }

                    LitiganteDAO litigante = new LitiganteDAO(tx);
                    mapa.put("id_persona", (String)mapPersona.get("id_persona"));
                    if(!litigante.existeLitigante(mapa)){
                        litigante.guardarLitigante(mapa);
                    }else{
                        litigante.actualizarLitigante(mapa);
                    }
                }
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

                    FichasIndividualesDAO fichasIndividuales = new FichasIndividualesDAO(tx);
                    fichaIndividual.putAll(fichasIndividuales.obtenerFichaIndividual(mapCondicion));

                    InstalacionDAO instalacion = new InstalacionDAO(tx);
                    List listaObra = instalacion.obtenerListaInstalacion(mapCondicion);
                    fichaIndividual.put("listaObra", listaObra);

                    PredioDAO predio = new PredioDAO(tx);
                    HashMap mapPredio = predio.obtenerPredio(mapCondicion);
                    fichaIndividual.put("mapPredio", mapPredio);
                    if(mapPredio!=null) fichaIndividual.put("sys_dir", (String)mapPredio.get("sys_dir"));
                    if(mapPredio!=null && !mapPredio.isEmpty()){
                        ViaDAO via = new ViaDAO(tx);
                        List listaVia = via.obtenerListaVia(mapCondicion);
                        if(listaVia!=null && !listaVia.isEmpty()){
                            for(int i=0;i<listaVia.size();i++){
                                HashMap mapvia = (HashMap)listaVia.get(i);
                                TablasCodigosDAO tablascodigos = new TablasCodigosDAO(tx);
                                HashMap codigo = tablascodigos.obtenerTablaCodigo(Properties.TIPO_PUERTA, (String)mapvia.get("codTipPuerta"));
                                if(codigo!=null && codigo.containsKey("descripcion"))
                                    mapvia.put("desTipPuerta", (String)codigo.get("descripcion"));
                            }
                        }
                        fichaIndividual.put("listaVia", listaVia);
                    }

                    IdeTitCatastralDAO titular = new IdeTitCatastralDAO(tx);
                    HashMap mapTitular = titular.obtenerIdeTitCatastral(mapCondicion);

                    TitularesDAO titulares = new TitularesDAO(tx);
                    mapTitular.putAll(titulares.obtenerCotitular(mapCondicion));

                    DomicilioTitularDAO domicilio = new DomicilioTitularDAO(tx);
                    mapTitular.putAll(domicilio.obtenerDomicilioTitular(mapCondicion));

                    fichaIndividual.put("mapTitular", mapTitular);

                    BienComunDAO bienComun = new BienComunDAO(tx);
                    HashMap mapBienComun = bienComun.obtenerBienComun(mapCondicion);
                    fichaIndividual.put("mapBienComun", mapBienComun);
                    if(mapBienComun!=null && !mapBienComun.isEmpty()){
                        ConstruccionDAO construccion = new ConstruccionDAO(tx);
                        List listaConstruccion = construccion.obtenerConstruccion(mapCondicion);
                        fichaIndividual.put("listaConstruccion", listaConstruccion);
                    }

                    NotariaDAO notaria = new NotariaDAO(tx);
                    HashMap mapNotaria = notaria.obtenerNotaria(mapCondicion);
                    fichaIndividual.put("mapNotaria", mapNotaria);
                    if(mapNotaria!=null && !mapNotaria.isEmpty()){
                        DocumentoDAO documento = new DocumentoDAO(tx);
                        List listaDocumento = documento.obtenerDocumento(mapCondicion);
                        fichaIndividual.put("listaDocumento", listaDocumento);
                    }

                    InsRegPredioDAO inscripcion = new InsRegPredioDAO(tx);
                    HashMap mapInscripcion = inscripcion.obtenerInsRegPredio(mapCondicion);
                    fichaIndividual.put("mapInscripcion", mapInscripcion);

                    InfComplementariaDAO informacion = new InfComplementariaDAO(tx);
                    HashMap mapInformacion = informacion.obtenerInfComplementaria(mapCondicion);
                    fichaIndividual.put("mapInformacion", mapInformacion);
                    if(mapInformacion!=null && !mapInformacion.isEmpty()){
                        LitiganteDAO litigante = new LitiganteDAO(tx);
                        List listaLitigante = litigante.obtenerLitigante(mapCondicion);
                        fichaIndividual.put("listaLitigante", listaLitigante);
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
        return fichaIndividual;
    }

    public int eliminarFicha(HashMap mapCondicion){
        int result = 0;
        TransactionContext tx = null;
        try{
            tx = new TransactionContext();

            InfComplementariaDAO informacion = new InfComplementariaDAO(tx);
            HashMap mapInformacion = informacion.obtenerInfComplementaria(mapCondicion);
            if(mapInformacion!=null && !mapInformacion.isEmpty()){
                LitiganteDAO litigante = new LitiganteDAO(tx);
                //litigante.eliminarLitiganteByInfComplementaria(mapInformacion);
            }
            informacion.eliminarInfComplementaria(mapCondicion);

            InsRegPredioDAO inscripcion = new InsRegPredioDAO(tx);
            inscripcion.eliminarInsRegPredio(mapCondicion);

            NotariaDAO notaria = new NotariaDAO(tx);
            HashMap mapNotaria = notaria.obtenerNotaria(mapCondicion);
            if(mapNotaria!=null && !mapNotaria.isEmpty()){
                DocumentoDAO documento = new DocumentoDAO(tx);
                documento.eliminarDocumentoByNotaria(mapNotaria);
            }
            notaria.eliminarNotaria(mapCondicion);

            BienComunDAO bienComun = new BienComunDAO(tx);
            HashMap mapBienComun = bienComun.obtenerBienComun(mapCondicion);
            if(mapBienComun!=null && !mapBienComun.isEmpty()){
                ConstruccionDAO construccion = new ConstruccionDAO(tx);
                //construccion.eliminarConstruccionByBienComun(mapBienComun);
            }
            bienComun.eliminarBienComun(mapCondicion);

            IdeTitCatastralDAO titular = new IdeTitCatastralDAO(tx);
            titular.eliminarIdeTitCatastral(mapCondicion);

            PredioDAO predio = new PredioDAO(tx);
            HashMap mapPredio = predio.obtenerPredio(mapCondicion);
            if(mapPredio!=null && !mapPredio.isEmpty()){
                ViaDAO via = new ViaDAO(tx);
                via.eliminarViaByPredio(mapPredio);
            }
            predio.eliminarPredio(mapCondicion);

            ObrComplementariaDAO obra = new ObrComplementariaDAO(tx);
            obra.eliminarObraComplementariaByFicha(mapCondicion);

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
