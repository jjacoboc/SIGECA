/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonatan Jacobo
 */
public class FichasIndividualesDAO extends GenericDAO {

    /** Crea una nueva instancia de FichasIndividualesDAO */
    public FichasIndividualesDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public int guardarFichaIndividual(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO FICHAS_INDIVIDUALES(id_ficha,evaluacion,en_colindante,en_jardin_aislamiento,en_area_publica,contenido_en,en_area_intangible,estado_llenado,");
            sql.append("area_verificada,nro_habitantes,area_declarada,nro_familias,area_titulo,mantenimiento,observaciones,condicion_declarante,cod_uso,clasificacion,");
            sql.append("porc_bc_terr_legal,porc_bc_const_legal,porc_bc_terr_fisico,porc_bc_const_fisico) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("id_ficha"));
            pstm.setString(2, (String) map.get("codEvaPreCatastral"));
            pstm.setDouble(3, (map.get("areTerInvLotColindante")!=null && !"".equals((String)map.get("areTerInvLotColindante")))?Double.parseDouble((String) map.get("areTerInvLotColindante")):0);
            pstm.setDouble(4, (map.get("areTerInvJarAislamiento")!=null && !"".equals((String)map.get("areTerInvJarAislamiento")))?Double.parseDouble((String) map.get("areTerInvJarAislamiento")):0);
            pstm.setDouble(5, (map.get("areTerInvArePublica")!=null && !"".equals((String)map.get("areTerInvArePublica")))?Double.parseDouble((String) map.get("areTerInvArePublica")):0);
            pstm.setString(6, (String) map.get("codUbiPreCatastral"));
            pstm.setDouble(7, (map.get("areTerInvAreIntangible")!=null && !"".equals((String)map.get("areTerInvAreIntangible")))?Double.parseDouble((String) map.get("areTerInvAreIntangible")):0);
            pstm.setString(8, (String) map.get("codEstLleFicha"));
            pstm.setDouble(9, (map.get("areTerVerificada")!=null && !"".equals((String)map.get("areTerVerificada")))?Double.parseDouble((String) map.get("areTerVerificada")):0);
            pstm.setInt(10, (map.get("numHabitantes")!=null && !"".equals((String)map.get("numHabitantes")))?Integer.parseInt((String) map.get("numHabitantes")):0);
            pstm.setDouble(11, (map.get("areTerDeclarada")!=null && !"".equals((String)map.get("areTerDeclarada")))?Double.parseDouble((String) map.get("areTerDeclarada")):0);
            pstm.setInt(12, (map.get("numFamilias")!=null && !"".equals((String)map.get("numFamilias")))?Integer.parseInt((String) map.get("numFamilias")):0);
            pstm.setDouble(13, (map.get("areTerTitulo")!=null && !"".equals((String)map.get("areTerTitulo")))?Double.parseDouble((String) map.get("areTerTitulo")):0);
            pstm.setString(14, (String) map.get("codMantenimiento"));
            pstm.setString(15, (String) map.get("observacion"));
            pstm.setString(16, (String) map.get("codConDeclarante"));
            pstm.setString(17, (String) map.get("codUsoPreCatastral"));
            pstm.setString(18, (String) map.get("codClaPredio"));
            pstm.setDouble(19, (map.get("terLegal")!=null && !"".equals((String)map.get("terLegal")))?Double.parseDouble((String) map.get("terLegal")):0);
            pstm.setDouble(20, (map.get("conLegal")!=null && !"".equals((String)map.get("conLegal")))?Double.parseDouble((String) map.get("conLegal")):0);
            pstm.setDouble(21, (map.get("terFisico")!=null && !"".equals((String)map.get("terFisico")))?Double.parseDouble((String) map.get("terFisico")):0);
            pstm.setDouble(22, (map.get("conFisica")!=null && !"".equals((String)map.get("conFisica")))?Double.parseDouble((String) map.get("conFisica")):0);

            result = pstm.executeUpdate();
            pstm.close();
            pstm = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException();
        }
        return result;
    }

    public int actualizarFichaIndividual(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        int result = 0;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE FICHAS_INDIVIDUALES SET evaluacion=?,en_colindante=?,en_jardin_aislamiento=?,en_area_publica=?,contenido_en=?,en_area_intangible=?,estado_llenado=?,");
            sql.append("area_verificada=?,nro_habitantes=?,area_declarada=?,nro_familias=?,area_titulo=?,mantenimiento=?,observaciones=?,condicion_declarante=?,cod_uso=?,clasificacion=?,");
            sql.append("porc_bc_terr_legal=?,porc_bc_const_legal=?,porc_bc_terr_fisico=?,porc_bc_const_fisico=? ");
            sql.append("WHERE id_ficha=? ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1, (String) map.get("codEvaPreCatastral"));
            pstm.setDouble(2, (map.get("areTerInvLotColindante")!=null && !"".equals((String)map.get("areTerInvLotColindante")))?Double.parseDouble((String) map.get("areTerInvLotColindante")):0);
            pstm.setDouble(3, (map.get("areTerInvJarAislamiento")!=null && !"".equals((String)map.get("areTerInvJarAislamiento")))?Double.parseDouble((String) map.get("areTerInvJarAislamiento")):0);
            pstm.setDouble(4, (map.get("areTerInvArePublica")!=null && !"".equals((String)map.get("areTerInvArePublica")))?Double.parseDouble((String) map.get("areTerInvArePublica")):0);
            pstm.setString(5, (String) map.get("codUbiPreCatastral"));
            pstm.setDouble(6, (map.get("areTerInvAreIntangible")!=null && !"".equals((String)map.get("areTerInvLotColindante")))?Double.parseDouble((String) map.get("areTerInvAreIntangible")):0);
            pstm.setString(7, (String) map.get("codEstLleFicha"));
            pstm.setDouble(8, (map.get("areTerVerificada")!=null && !"".equals((String)map.get("areTerVerificada")))?Double.parseDouble((String) map.get("areTerVerificada")):0);
            pstm.setInt(9, (map.get("numHabitantes")!=null && !"".equals((String)map.get("numHabitantes")))?Integer.parseInt((String) map.get("numHabitantes")):0);
            pstm.setDouble(10, (map.get("areTerDeclarada")!=null && !"".equals((String)map.get("areTerDeclarada")))?Double.parseDouble((String) map.get("areTerDeclarada")):0);
            pstm.setInt(11, (map.get("numFamilias")!=null && !"".equals((String)map.get("numFamilias")))?Integer.parseInt((String) map.get("numFamilias")):0);
            pstm.setDouble(12, (map.get("areTerTitulo")!=null && !"".equals((String)map.get("areTerTitulo")))?Double.parseDouble((String) map.get("areTerTitulo")):0);
            pstm.setString(13, (String) map.get("codMantenimiento"));
            pstm.setString(14, (String) map.get("observacion"));
            pstm.setString(15, (String) map.get("codConDeclarante"));
            pstm.setString(16, (String) map.get("codUsoPreCatastral"));
            pstm.setString(17, (String) map.get("codClaPredio"));
            pstm.setDouble(18, (map.get("terLegal")!=null && !"".equals((String)map.get("terLegal")))?Double.parseDouble((String) map.get("terLegal")):0);
            pstm.setDouble(19, (map.get("conLegal")!=null && !"".equals((String)map.get("conLegal")))?Double.parseDouble((String) map.get("conLegal")):0);
            pstm.setDouble(20, (map.get("terFisico")!=null && !"".equals((String)map.get("terFisico")))?Double.parseDouble((String) map.get("terFisico")):0);
            pstm.setDouble(21, (map.get("conFisica")!=null && !"".equals((String)map.get("conFisica")))?Double.parseDouble((String) map.get("conFisica")):0);
            pstm.setString(22, (String) map.get("id_ficha"));

            result = pstm.executeUpdate();
            pstm.close();
            pstm = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException();
        }
        return result;
    }

    public HashMap obtenerFichaIndividual(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap ficha = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT DISTINCT en_colindante, en_area_publica, en_jardin_aislamiento, en_area_publica ");
            sql.append("FROM FICHAS_INDIVIDUALES ");
            sql.append("WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                ficha = new HashMap();
                ficha.put("areTerInvLotColindante", rst.getString(1));
                ficha.put("areTerInvArePublica", rst.getString(2));
                ficha.put("areTerInvJarAislamiento", rst.getString(3));
                ficha.put("areTerInvAreIntangible", rst.getString(4));
            }

            pstm.close();
            pstm=null;
            rst.close();
            rst = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            if(rst!=null){
                try{
                    rst.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return ficha;
    }

    public boolean existeFichaIndividual(HashMap map) throws DAOException {
        PreparedStatement pstm = null;
        ResultSet rst = null;
        boolean existe = false;
        try {
            StringBuilder sql = new StringBuilder("");
            sql.append("SELECT id_ficha FROM FICHAS_INDIVIDUALES WHERE id_ficha = ? ");

            pstm = context.getConnection().prepareStatement(sql.toString().trim());
            pstm.setString(1, (String)map.get("id_ficha"));

            rst = pstm.executeQuery();

            if (rst.next()) {
                existe = true;
            }

            pstm.close();
            pstm = null;
            rst.close();
            rst = null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (Exception ignore) {
                }
            }
            if (rst != null) {
                try {
                    rst.close();
                } catch (Exception ignore) {
                }
            }
            throw new DAOException(ex);
        }
        return existe;
    }
}