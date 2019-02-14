/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.consulta.service;

import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.persistence.TransactionSysException;
import com.bmp.sigeca.common.service.GenericService;
import com.bmp.sigeca.consulta.persistence.FichaDAO;
import com.bmp.sigeca.util.DBUtils;
import com.bmp.sigeca.util.DBUtils.CallTransaction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrador
 */
public class FichaService  extends GenericService{

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por CodigoUnicoCatastral.
     * @param codigoUnicoCatastral
     * @return List
     */
    public List buscarPorCodigoUnicoCatastral(final String codigoUnicoCatastral, final String estado) throws SQLException
    {
        final List fichas = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                fichas.addAll(fichaDao.buscarPorCodigoUnicoCatastral(codigoUnicoCatastral, estado));
            }
        });
        obtenerTiposDeFichas(fichas);
        return fichas;
    }

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por CRC
     * Código Referencia Catastral
     * @param codigoReferenciaCatastral
     * @param estado
     * @return
     * @throws SQLException
     */
    public List buscarPorCodigoReferenciaCatastral(final String codigoReferenciaCatastral, final String estado) throws SQLException
    {
        final List fichas = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                fichas.addAll(fichaDao.buscarPorCodigoReferenciaCatastral(codigoReferenciaCatastral, estado));
            }
        });
        obtenerTiposDeFichas(fichas);
        return fichas;
    }

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por CodigoPredialRenta
     * @param codigoPredialRenta
     * @param estado
     * @return
     * @throws SQLException
     */
    public List buscarPorCodigoPredialRenta(final String codigoPredialRenta, final String estado) throws SQLException
    {
        final List fichas = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                fichas.addAll(fichaDao.buscarPorCodigoPredialRenta(codigoPredialRenta, estado));
            }
        });
        obtenerTiposDeFichas(fichas);
        return fichas;
    }

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por Dirección
     * @param tipoVia
     * @param nombreVia
     * @param numeroMunicipal
     * @param estado
     * @return
     * @throws SQLException
     */
    public List buscarPorDireccion(final String tipoVia, final String nombreVia, final String numeroMunicipal, final String estado) throws SQLException
    {
        final List fichas = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                fichas.addAll(fichaDao.buscarPorDireccion(tipoVia, nombreVia, numeroMunicipal, estado));
            }
        });
        obtenerTiposDeFichas(fichas);
        return fichas;
    }

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por Ubicación
     * @param lote
     * @param manzana
     * @param estado
     * @return
     * @throws SQLException
     */
    public List buscarPorUbicacion(final String lote, final String manzana, final String estado) throws SQLException
    {
        final List fichas = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                fichas.addAll(fichaDao.buscarPorUbicacion(lote, manzana, estado));
            }
        });
        obtenerTiposDeFichas(fichas);
        return fichas;
    }

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por Titular
     * @param codigoUnicoCatastral
     * @return List
     */
    public List buscarPorTitular(final String nombre, final String apellidoPaterno, final String apellidoMaterno, final String estado) throws SQLException
    {
        final List fichas = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                fichas.addAll(fichaDao.buscarPorTitular(nombre, apellidoPaterno, apellidoMaterno, estado));
            }
        });
        obtenerTiposDeFichas(fichas);
        return fichas;
    }

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por DNI/RUC
     * @param dniRuc
     * @param estado
     * @return
     * @throws SQLException
     */
    public List buscarPorDniRuc(final String dniRuc, final String estado) throws SQLException
    {
        final List fichas = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                fichas.addAll(fichaDao.buscarPorDniRuc(dniRuc, estado));
            }
        });
        obtenerTiposDeFichas(fichas);
        return fichas;
    }

    /**
     * Retorna una lista de todos los registros de la tabla ESTADO, cada elemento
     * de la lista es un Map que contiene todas las columnas del registro.
     * @return
     * @throws SQLException
     */
    public List obtenerEstados() throws SQLException
    {
        final List estados = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                estados.addAll(fichaDao.obtenerEstados());
            }
        });
        return estados;
    }

    /**
     * Retorna una lista de registros con la columna tip_via de la tabla VIAS, 
     * cada elemento de la lista es un Map, los elementos no se repiten.
     * @return
     * @throws SQLException
     */
    public List obtenerTiposVia() throws SQLException
    {
        final List estados = new ArrayList();
        DBUtils.executeTransactionDao(new CallTransaction(){
            public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
            {
                FichaDAO fichaDao = new FichaDAO(tx);
                estados.addAll(fichaDao.obtenerTiposVia());
            }
        });
        return estados;
    }

    /**
     * Agrega a la lista Fichas los tipos de ficha Individuales, Económicas,
     * Cotitularidades y BienesComunes
     * @param fichas
     * @throws SQLException
     */
    private void obtenerTiposDeFichas(List fichas) throws SQLException
    {
        for (Iterator it = fichas.iterator(); it.hasNext();) {
            final Map contenido = (Map)it.next();
            final String idFicha = getSubMapValue(contenido, "columnas", "id_ficha");
            if(StringUtils.isNotBlank(idFicha)){
                DBUtils.executeTransactionDao(new CallTransaction(){
                    public void callOperation(TransactionContext tx) throws SQLException, TransactionSysException
                    {
                        FichaDAO fichaDao = new FichaDAO(tx);
                        contenido.put("fichasIndividuales", fichaDao.buscarFichasIndividuales(idFicha));
                        contenido.put("fichasEconomicas", fichaDao.buscarFichasEconomicas(idFicha));
                        contenido.put("fichasCotitularidades", fichaDao.buscarFichasCotitularidades(idFicha));
                        contenido.put("fichasBienesComunes", fichaDao.buscarFichasBienesComunes(idFicha));
                    }
                });
            }
        }
    }

    /**
     * Retorna el valor de un SubMap, primero extrae el SubMap identificado con
     * keySubMap, luego extrae un valor del SubMap identificado con keySubMapValue.
     * @param map
     * @param keySubMap
     * @param keySubMapValue
     * @return
     */
    private String getSubMapValue(Map map, String keySubMap, String keySubMapValue)
    {
        if(map != null)
        {
            Map subMap = (Map)map.get(keySubMap);
            if(subMap != null){
                return (String)subMap.get(keySubMapValue);
            }
        }
        return null;
    }
}
