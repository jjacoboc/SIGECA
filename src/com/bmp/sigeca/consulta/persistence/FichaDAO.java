/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.consulta.persistence;

import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.common.persistence.TransactionSysException;
import com.bmp.sigeca.util.DBUtils;
import com.bmp.sigeca.util.DBUtils.RsCallback;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class FichaDAO extends GenericDAO {

    /** Crea una nueva instancia de UsosDAO */
    public FichaDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta donde
     * uni_cat.cuc = codigoUnicoCatastral y fichas.id_uni_cat = uni_cat.id_uni_cat
     * @param codigoUnicoCatastral
     * @param estado
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarPorCodigoUnicoCatastral(String codigoUnicoCatastral, String estado) throws SQLException, TransactionSysException{
        
        Object[] params = new String[1];

        String query = "SELECT fichas.*, uni_cat.cuc FROM fichas INNER JOIN uni_cat ON fichas.id_uni_cat = uni_cat.id_uni_cat WHERE uni_cat.cuc = ?";
        if(estado != null)
        {
            query = query + " AND fichas.id_estado = ?";
            params = new String[2];
            params[1] = estado;
        }
        params[0] = codigoUnicoCatastral;

        return getListOfSubMaps(query, params);
    }

    /**
     * Retorna una lista con las Fichas resultantes de la consulta por CRC
     * Código Referencia Catastral
     * @param codigoReferenciaCatastral
     * @param estado
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarPorCodigoReferenciaCatastral(String codigoReferenciaCatastral, String estado) throws SQLException, TransactionSysException{

        if(codigoReferenciaCatastral!=null && codigoReferenciaCatastral.length() == 21)
        {
            Object[] params = new String[8];
            String query = "SELECT f.*, ub.id_ubi_geo || s.cod_sector || m.cod_mzna || l.cod_lote || e.cod_edificacion || u.cod_entrada || u.cod_piso || u.cod_unidad || f.dc as codigoReferenciaCatastral ";
            query = query + "FROM fichas f, ubigeo ub, sectores s, manzanas m, lotes l, edificaciones e, uni_cat u ";
            query = query + "WHERE ub.id_ubi_geo = s.id_ubi_geo and s.id_sector  = m.id_sector and m.id_mzna = l.id_mzna and l.id_lote = e.id_lote and u.id_uni_cat = f.id_uni_cat and f.id_lote = l.id_lote and ";
            query = query + "ub.id_ubi_geo = ? and s.cod_sector = ? and l.cod_lote = ? and e.cod_edificacion = ? and u.cod_entrada = ? and u.cod_piso = ? and u.cod_unidad = ? and f.dc = ?";

            if(estado != null)
            {
                query = query + " AND f.id_estado = ?";
                params = new String[9];
                params[8] = estado;
            }
            params[0] = codigoReferenciaCatastral.substring(0, 6); //id_ubi_geo
            params[1] = codigoReferenciaCatastral.substring(6, 8); //cod_sector
            params[2] = codigoReferenciaCatastral.substring(8, 11); //cod_lote
            params[3] = codigoReferenciaCatastral.substring(11, 13); //cod_edificacion
            params[4] = codigoReferenciaCatastral.substring(13, 15); //cod_entrada
            params[5] = codigoReferenciaCatastral.substring(15, 17); //cod_piso
            params[6] = codigoReferenciaCatastral.substring(17, 20); //cod_unidad
            params[7] = codigoReferenciaCatastral.substring(20, 21); //dc

            return getListOfSubMaps(query, params);
        }
        return new ArrayList();
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta donde
     * uni_cat.cod_pred_rentas = codigoPredialRenta y fichas.id_uni_cat = uni_cat.id_uni_cat
     * @param codigoPredialRenta
     * @param estado
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarPorCodigoPredialRenta(String codigoPredialRenta, String estado) throws SQLException, TransactionSysException{

        Object[] params = new String[1];

        String query = "SELECT fichas.*, uni_cat.cod_pred_rentas FROM fichas INNER JOIN uni_cat ON fichas.id_uni_cat = uni_cat.id_uni_cat WHERE uni_cat.cod_pred_rentas = ?";
        if(estado != null)
        {
            query = query + " AND fichas.id_estado = ?";
            params = new String[2];
            params[1] = estado;
        }
        params[0] = codigoPredialRenta;

        return getListOfSubMaps(query, params);
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta donde
     * fichas.id_lote = puertas.id_lote, puertas.id_via = vias.id_via,
     * vias.tip_via = tipoVia, vias.nom_via like %nombreVia% y
     * puertas.nro_muni = numeroMunicipal
     * @param tipoVia
     * @param nombreVia
     * @param numeroMunicipal
     * @param estado
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarPorDireccion(String tipoVia, String nombreVia, String numeroMunicipal, String estado) throws SQLException, TransactionSysException{

        Object[] params = new String[3];

        String query = "SELECT fichas.*, vias.tip_via, vias.nom_via, puertas.nro_muni FROM fichas INNER JOIN puertas ON fichas.id_lote = puertas.id_lote INNER JOIN vias ON puertas.id_via = vias.id_via AND vias.tip_via = ? AND vias.nom_via like ? AND puertas.nro_muni = ?";
        if(estado != null)
        {
            query = query + " AND fichas.id_estado = ?";
            params = new String[4];
            params[3] = estado;
        }
        params[0] = tipoVia;
        params[1] = "%" + nombreVia + "%";
        params[2] = numeroMunicipal;

        return getListOfSubMaps(query, params);
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta donde
     * fichas.id_lote = lotes.id_lote, manzanas ON lotes.id_mzna,
     * lotes.cod_lote = lote y manzanas.cod_mzna = manzana
     * @param lote
     * @param manzana
     * @param estado
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarPorUbicacion(String lote, String manzana, String estado) throws SQLException, TransactionSysException{

        Object[] params = new String[2];

        String query = "SELECT fichas.*, lotes.cod_lote, manzanas.cod_mzna FROM fichas INNER JOIN lotes ON fichas.id_lote = lotes.id_lote INNER JOIN manzanas ON lotes.id_mzna = manzanas.id_mzna AND lotes.cod_lote = ? AND manzanas.cod_mzna = ?";
        if(estado != null)
        {
            query = query + " AND fichas.id_estado = ?";
            params = new String[3];
            params[2] = estado;
        }
        params[0] = lote;
        params[1] = manzana;

        return getListOfSubMaps(query, params);
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta donde
     * fichas.id_ficha = titulares.id_ficha, titulares.id_persona = personas.id_persona,
     * personas.nombre = nombre, personas.apellidoPaterno = apellidoPaterno y
     * personas.apellidoMaterno = apellidoMaterno
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param estado
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarPorTitular(String nombre, String apellidoPaterno, String apellidoMaterno, String estado) throws SQLException, TransactionSysException{

        Object[] params = new String[3];

        String query = "SELECT fichas.*, personas.* FROM fichas INNER JOIN titulares ON fichas.id_ficha = titulares.id_ficha INNER JOIN personas ON titulares.id_persona = personas.id_persona AND nombres LIKE ? AND ape_paterno LIKE ? AND ape_materno LIKE ?";
        if(estado != null)
        {
            query = query + " AND fichas.id_estado = ?";
            params = new String[4];
            params[3] = estado;
        }
        params[0] = nombre + "%";
        params[1] = apellidoPaterno + "%";
        params[2] = apellidoMaterno + "%";

        return getListOfSubMaps(query, params);
    }

    /**
     * Retorna una lista con los registros resultantes de la consulta donde
     * fichas.id_ficha = titulares.id_ficha, titulares.id_persona = personas.id_persona y
     * personas.nro_doc = dniRuc
     * @param dniRuc
     * @param estado
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarPorDniRuc(String dniRuc, String estado) throws SQLException, TransactionSysException{

        Object[] params = new String[1];

        String query = "SELECT fichas.*, personas.* FROM fichas INNER JOIN titulares ON fichas.id_ficha = titulares.id_ficha INNER JOIN personas ON titulares.id_persona = personas.id_persona AND personas.nro_doc = ?";
        if(estado != null)
        {
            query = query + " AND fichas.id_estado = ?";
            params = new String[2];
            params[1] = estado;
        }
        params[0] = dniRuc;

        return getListOfSubMaps(query, params);
    }

    /**
     * Retorna una lista de todos los registros de la tabla ESTADO, cada elemento
     * de la lista es un Map que contiene todas las columnas del registro.
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List obtenerEstados() throws SQLException, TransactionSysException{
        String query = "SELECT * FROM estado";
        return getListOfMaps(query, null);
    }

    /**
     * Retorna una lista de registros con la columna tip_via de la tabla VIAS, cada elemento
     * de la lista es un Map, los elementos no se repiten.
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List obtenerTiposVia() throws SQLException, TransactionSysException{
        String query = "SELECT DISTINCT vias.tip_via FROM tablas_codigos INNER JOIN vias ON tablas_codigos.id_tabla = vias.tip_via";
        return getListOfMaps(query, null);
    }

    /**
     * Retorna una lista con FichasIndividuales donde 
     * fichas.id_ficha = idFicha y fichas_individuales.id_ficha = fichas.id_ficha
     * @param idFicha
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarFichasIndividuales(String idFicha) throws SQLException, TransactionSysException{

        String query = "SELECT * FROM fichas_individuales INNER JOIN fichas ON fichas_individuales.id_ficha = fichas.id_ficha AND fichas.id_ficha = ?";
        Object[] params = new String[1];
        params[0] = idFicha;
        return getListOfMaps(query, params);
    }

    /**
     * Retorna una lista con FichasEconomicas donde
     * fichas.id_ficha = idFicha y fichas_economicas.id_ficha = fichas.id_ficha
     * @param idFicha
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarFichasEconomicas(String idFicha) throws SQLException, TransactionSysException{
        String query = "SELECT * FROM fichas_economicas INNER JOIN fichas ON fichas_economicas.id_ficha = fichas.id_ficha AND fichas.id_ficha = ?";
        Object[] params = new String[1];
        params[0] = idFicha;
        return getListOfMaps(query, params);
    }

    /**
     * Retorna una lista con FichasCotitularidades donde
     * fichas.id_ficha = idFicha y fichas_cotitularidades.id_ficha = fichas.id_ficha
     * @param idFicha
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarFichasCotitularidades(String idFicha) throws SQLException, TransactionSysException{
        String query = "SELECT * FROM fichas_cotitularidades INNER JOIN fichas ON fichas_cotitularidades.id_ficha = fichas.id_ficha AND fichas.id_ficha = ?";
        Object[] params = new String[1];
        params[0] = idFicha;
        return getListOfMaps(query, params);
    }

    /**
     * Retorna una lista con FichasBienesComunes donde
     * fichas.id_ficha = idFicha y fichas_bienes_comunes.id_ficha = fichas.id_ficha
     * @param idFicha
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    public List buscarFichasBienesComunes(String idFicha) throws SQLException, TransactionSysException{
        String query = "SELECT * FROM fichas_bienes_comunes INNER JOIN fichas ON fichas_bienes_comunes.id_ficha = fichas.id_ficha AND fichas.id_ficha = ?";
        Object[] params = new String[1];
        params[0] = idFicha;
        return getListOfMaps(query, params);
    }

    /**
     * Retorna una lista donde cada elemento de la lista es un Map que contiene
     * las columnas de la tabla, el nombre de cada columna es un Key del Map.
     * @param query
     * @param params
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    private List getListOfMaps(String query, Object[] params) throws SQLException, TransactionSysException
    {
        final List list = new ArrayList();
        executeQuery(query, new RsCallback()
            {
                public void nextResult(ResultSet rs) throws SQLException
                {
                    Map columns = new LinkedHashMap();
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++)
                    {
                        columns.put(rs.getMetaData().getColumnName(i + 1), rs.getObject(i + 1));
                    }
                    list.add(columns);
                }
            }, params
        );
        return list;
    }

    /**
     * Retorna una lista donde cada elemento de la lista es un Map que contiene
     * otro Map con Key columnas, este SubMap contiene las columnas de la tabla,
     * el nombre de cada columna es un Key del Map.
     * @param query
     * @param params
     * @return
     * @throws SQLException
     * @throws TransactionSysException
     */
    private List getListOfSubMaps(String query, Object[] params) throws SQLException, TransactionSysException
    {
        final List list = new ArrayList();
        executeQuery(query, new RsCallback()
            {
                public void nextResult(ResultSet rs) throws SQLException
                {
                    Map contenido = new HashMap();
                    Map columns = new LinkedHashMap();
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++)
                    {
                        columns.put(rs.getMetaData().getColumnName(i + 1), rs.getObject(i + 1));
                    }
                    contenido.put("columnas", columns);
                    list.add(contenido);
                }
            }, params
        );
        return list;
    }

    /**
     * Hace una llamada a DBUtils.executeQuery enviandole los parámetros recibidos
     * más context.
     * @param sql
     * @param rsCallback
     * @param params
     * @throws SQLException
     * @throws TransactionSysException
     */
    private void executeQuery(String sql, RsCallback rsCallback, Object[] params) throws SQLException, TransactionSysException
    {
        DBUtils.executeQuery(sql, context, rsCallback, params);
    }
}