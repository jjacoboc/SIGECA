/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.persistence;

import com.bmp.sigeca.common.persistence.DAOException;
import com.bmp.sigeca.common.persistence.GenericDAO;
import com.bmp.sigeca.common.persistence.TablaDAO;
import com.bmp.sigeca.common.persistence.TransactionContext;
import com.bmp.sigeca.resource.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrador
 */
public class PredioHistDAO extends GenericDAO {

    /** Crea una nueva instancia de PredioHistDAO */
    public PredioHistDAO(TransactionContext context) {
        super(context);
        this.context = context;
        log = Logger.getLogger(this.getClass());
    }

    public long guardarPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        long pk = Long.MIN_VALUE;
        try {
            TablaDAO tablaDAO = new TablaDAO(context);
            pk = (Long)tablaDAO.getNextPK("codPredio","TPREDIO_HIST");

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO TPREDIO_HIST(numFicha,codTipEdificacion,nomEdificacion,codTipInterior,numInterior,");
            sql.append("codHabUrbana,nomHabUrbana,sector,manzana,lote,sublote,nomPredio,nomValle,nomSector,codProyecto,");
            sql.append("numFoto,numOrtofoto,codImagen,uniCatAnterior,codClaPredio,codSubClaPredio,codUbiPreCatastral,");
            sql.append("codUsoPreCatastral,desUsoPreCatastral,estructuracion,zonificacion,areTerTitulo,areTerDeclarada,");
            sql.append("areTerVerificada,medCamFrente,medCamDerecha,medCamIzquierda,medCamFondo,medTitFrente,medTitDerecha,");
            sql.append("medTitIzquierda,medTitFondo,colCamFrente,colCamDerecha,colCamIzquierda,colCamFondo,colTitFrente,");
            sql.append("colTitDerecha,colTitIzquierda,colTitFondo,codUso,codClaUsoActual,luz,agua,telefono,desague,numSumLuz,");
            sql.append("numConAgua,numTelefono,codConTitular,codForAdqPredio,fecAdquisicion,codConEspPredio,numResExoPredio,");
            sql.append("porcentaje,fecInicio,fecVencimiento,fecOcupacion,codInsRegPublicos,numInscripcion,fecInscripcion,codPredio,");
            sql.append("codConIns,codCatInmueble,codMonumento,nomMonumento,areMonumento,uniAreMonumento,perimetro,codFilCronologica,");
            sql.append("preArquitectura,codTipArquitectura,codTipMatConstructivo,codAfeNatural,codAfeAntropofica,codIntConservacion,");
            sql.append("patrimonio,denominacion,codFicha) ");
            sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,");
            sql.append("str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,str_to_date(?,'%d/%m/%Y %h:%i:%S'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

            pstm = context.getConnection().prepareStatement(sql.toString());

            pstm.setString(1,(String)map.get("numFicha"));
            pstm.setString(2,(String)map.get("codTipEdificacion"));
            pstm.setString(3,(String)map.get("nomEdificacion"));
            pstm.setString(4,(String)map.get("codTipInterior"));
            pstm.setString(5,(String)map.get("numInterior"));
            pstm.setString(6,(String)map.get("codHabUrbana"));
            pstm.setString(7,(String)map.get("nomHabUrbana"));
            pstm.setString(8,(String)map.get("sector"));
            pstm.setString(9,(String)map.get("manzana"));
            pstm.setString(10,(String)map.get("lote"));
            pstm.setString(11,(String)map.get("sublote"));
            pstm.setString(12,(String)map.get("nomPredio"));
            pstm.setString(13,(String)map.get("nomValle"));
            pstm.setString(14,(String)map.get("nomSector"));
            pstm.setString(15,(String)map.get("codProyecto"));
            pstm.setString(16,(String)map.get("numFoto"));
            pstm.setString(17,(String)map.get("numOrtofoto"));
            pstm.setString(18,(String)map.get("codImagen"));
            pstm.setString(19,(String)map.get("uniCatAnterior"));
            pstm.setString(20,(String)map.get("codClaPredio"));
            pstm.setString(21,(String)map.get("codSubClaPredio"));
            pstm.setString(22,(String)map.get("codUbiPreCatastral"));
            pstm.setString(23,(String)map.get("codUsoPreCatastral"));
            pstm.setString(24,(String)map.get("desUsoPreCatastral"));
            pstm.setString(25,(String)map.get("estructuracion"));
            pstm.setString(26,(String)map.get("zonificacion"));
            pstm.setString(27,(String)map.get("areTerTitulo"));
            pstm.setString(28,(String)map.get("areTerDeclarada"));
            pstm.setString(29,(String)map.get("areTerVerificada"));
            pstm.setString(30,(String)map.get("medCamFrente"));
            pstm.setString(31,(String)map.get("medCamDerecha"));
            pstm.setString(32,(String)map.get("medCamIzquierda"));
            pstm.setString(33,(String)map.get("medCamFondo"));
            pstm.setString(34,(String)map.get("medTitFrente"));
            pstm.setString(35,(String)map.get("medTitDerecha"));
            pstm.setString(36,(String)map.get("medTitIzquierda"));
            pstm.setString(37,(String)map.get("medTitFondo"));
            pstm.setString(38,(String)map.get("colCamFrente"));
            pstm.setString(39,(String)map.get("colCamDerecha"));
            pstm.setString(40,(String)map.get("colCamIzquierda"));
            pstm.setString(41,(String)map.get("colCamFondo"));
            pstm.setString(42,(String)map.get("colTitFrente"));
            pstm.setString(43,(String)map.get("colTitDerecha"));
            pstm.setString(44,(String)map.get("colTitIzquierda"));
            pstm.setString(45,(String)map.get("colTitFondo"));
            pstm.setString(46,(String)map.get("codUso"));
            pstm.setString(47,(String)map.get("codClaUsoActual"));
            if(map.containsKey("luz") && map.get("luz")!=null && "on".equals((String)map.get("luz"))){
                pstm.setString(48,Properties.Si);
            }else{
                pstm.setString(48,Properties.No);
            }
            if(map.containsKey("agua") && map.get("agua")!=null && "on".equals((String)map.get("agua"))){
                pstm.setString(49,Properties.Si);
            }else{
                pstm.setString(49,Properties.No);
            }
            if(map.containsKey("telefono") && map.get("telefono")!=null && "on".equals((String)map.get("telefono"))){
                pstm.setString(50,Properties.Si);
            }else{
                pstm.setString(50,Properties.No);
            }
            if(map.containsKey("desague") && map.get("desague")!=null && "on".equals((String)map.get("desague"))){
                pstm.setString(51,Properties.Si);
            }else{
                pstm.setString(51,Properties.No);
            }
            pstm.setString(52,(String)map.get("numSumLuz"));
            pstm.setString(53,(String)map.get("numConAgua"));
            pstm.setString(54,(String)map.get("numTelefono"));
            pstm.setString(55,(String)map.get("codConTitular"));
            pstm.setString(56,(String)map.get("codForAdqPredio"));
            pstm.setString(57,(String)map.get("fecAdquisicion"));
            pstm.setString(58,(String)map.get("codConEspPredio"));
            pstm.setString(59,(String)map.get("numResExoPredio"));
            pstm.setString(60,(String)map.get("porcentaje"));
            pstm.setString(61,(String)map.get("fecInicio"));
            pstm.setString(62,(String)map.get("fecVencimiento"));
            pstm.setString(63,(String)map.get("fecOcupacion"));
            pstm.setString(64,(String)map.get("codInsRegPublicos"));
            pstm.setString(65,(String)map.get("numInscripcion"));
            pstm.setString(66,(String)map.get("fecInscripcion"));
            pstm.setString(67,Long.toString(pk));
            pstm.setString(68,(String)map.get("codConIns"));
            pstm.setString(69,(String)map.get("codCatInmueble"));
            pstm.setString(70,(String)map.get("codMonumento"));
            pstm.setString(71,(String)map.get("nomMonumento"));
            pstm.setString(72,(String)map.get("areMonumento"));
            pstm.setString(73,(String)map.get("uniAreMonumento"));
            pstm.setString(74,(String)map.get("perimetro"));
            pstm.setString(75,(String)map.get("codFilCronologica"));
            pstm.setString(76,(String)map.get("preArquitectura"));
            pstm.setString(77,(String)map.get("codTipArquitectura"));
            pstm.setString(78,(String)map.get("codTipMatConstructivo"));
            pstm.setString(79,(String)map.get("codAfeNatural"));
            pstm.setString(80,(String)map.get("codAfeAntropofica"));
            pstm.setString(81,(String)map.get("codIntConservacion"));
            pstm.setString(82,(String)map.get("patrimonio"));
            pstm.setString(83,(String)map.get("denominacion"));
            pstm.setString(84,(String)map.get("codFicha"));

            result = pstm.executeUpdate();
            if(result!=0) result = pk;
            
            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public long actualizarPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        long result = 0;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE TPREDIO_HIST SET codTipEdificacion=?,nomEdificacion=?,codTipInterior=?,numInterior=?,codHabUrbana=?,nomHabUrbana=?,");
            sql.append("sector=?,manzana=?,lote=?,sublote=?,nomPredio=?,nomValle=?,nomSector=?,codProyecto=?,numFoto=?,numOrtofoto=?,codImagen=?,");
            sql.append("uniCatAnterior=?,codClaPredio=?,codSubClaPredio=?,codUbiPreCatastral=?,codUsoPreCatastral=?,desUsoPreCatastral=?,estructuracion=?,");
            sql.append("zonificacion=?,areTerTitulo=?,areTerDeclarada=?,areTerVerificada=?,medCamFrente=?,medCamDerecha=?,medCamIzquierda=?,medCamFondo=?,");
            sql.append("medTitFrente=?,medTitDerecha=?,medTitIzquierda=?,medTitFondo=?,colCamFrente=?,colCamDerecha=?,colCamIzquierda=?,colCamFondo=?,");
            sql.append("colTitFrente=?,colTitDerecha=?,colTitIzquierda=?,colTitFondo=?,codUso=?,codClaUsoActual=?,luz=?,agua=?,telefono=?,desague=?,");
            sql.append("numSumLuz=?,numConAgua=?,numTelefono=?,codConTitular=?,codForAdqPredio=?,fecAdquisicion=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("codConEspPredio=?,numResExoPredio=?,porcentaje=?,fecInicio=str_to_date(?,'%d/%m/%Y %h:%i:%S'),fecVencimiento=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("fecOcupacion=str_to_date(?,'%d/%m/%Y %h:%i:%S'),codInsRegPublicos=?,numInscripcion=?,fecInscripcion=str_to_date(?,'%d/%m/%Y %h:%i:%S'),");
            sql.append("codConIns=?,codCatInmueble=?,codMonumento=?,nomMonumento=?,areMonumento=?,uniAreMonumento=?,perimetro=?,codFilCronologica=?, ");
            sql.append("preArquitectura=?,codTipArquitectura=?,codTipMatConstructivo=?,codAfeNatural=?,codAfeAntropofica=?,codIntConservacion=?,patrimonio=?,");
            sql.append("denominacion=? ");
            sql.append("WHERE codPredio=? AND numFicha=? AND codFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            
            pstm.setString(1,(String)map.get("codTipEdificacion"));
            pstm.setString(2,(String)map.get("nomEdificacion"));
            pstm.setString(3,(String)map.get("codTipInterior"));
            pstm.setString(4,(String)map.get("numInterior"));
            pstm.setString(5,(String)map.get("codHabUrbana"));
            pstm.setString(6,(String)map.get("nomHabUrbana"));
            pstm.setString(7,(String)map.get("sector"));
            pstm.setString(8,(String)map.get("manzana"));
            pstm.setString(9,(String)map.get("lote"));
            pstm.setString(10,(String)map.get("sublote"));
            pstm.setString(11,(String)map.get("nomPredio"));
            pstm.setString(12,(String)map.get("nomValle"));
            pstm.setString(13,(String)map.get("nomSector"));
            pstm.setString(14,(String)map.get("codProyecto"));
            pstm.setString(15,(String)map.get("numFoto"));
            pstm.setString(16,(String)map.get("numOrtofoto"));
            pstm.setString(17,(String)map.get("codImagen"));
            pstm.setString(18,(String)map.get("uniCatAnterior"));
            pstm.setString(19,(String)map.get("codClaPredio"));
            pstm.setString(20,(String)map.get("codSubClaPredio"));
            pstm.setString(21,(String)map.get("codUbiPreCatastral"));
            pstm.setString(22,(String)map.get("codUsoPreCatastral"));
            pstm.setString(23,(String)map.get("desUsoPreCatastral"));
            pstm.setString(24,(String)map.get("estructuracion"));
            pstm.setString(25,(String)map.get("zonificacion"));
            pstm.setString(26,(String)map.get("areTerTitulo"));
            pstm.setString(27,(String)map.get("areTerDeclarada"));
            pstm.setString(28,(String)map.get("areTerVerificada"));
            pstm.setString(29,(String)map.get("medCamFrente"));
            pstm.setString(30,(String)map.get("medCamDerecha"));
            pstm.setString(31,(String)map.get("medCamIzquierda"));
            pstm.setString(32,(String)map.get("medCamFondo"));
            pstm.setString(33,(String)map.get("medTitFrente"));
            pstm.setString(34,(String)map.get("medTitDerecha"));
            pstm.setString(35,(String)map.get("medTitIzquierda"));
            pstm.setString(36,(String)map.get("medTitFondo"));
            pstm.setString(37,(String)map.get("colCamFrente"));
            pstm.setString(38,(String)map.get("colCamDerecha"));
            pstm.setString(39,(String)map.get("colCamIzquierda"));
            pstm.setString(40,(String)map.get("colCamFondo"));
            pstm.setString(41,(String)map.get("colTitFrente"));
            pstm.setString(42,(String)map.get("colTitDerecha"));
            pstm.setString(43,(String)map.get("colTitIzquierda"));
            pstm.setString(44,(String)map.get("colTitFondo"));
            pstm.setString(45,(String)map.get("codUso"));
            pstm.setString(46,(String)map.get("codClaUsoActual"));
            if(map.containsKey("luz") && map.get("luz")!=null && "on".equals((String)map.get("luz"))){
                pstm.setString(47,Properties.Si);
            }else{
                pstm.setString(47,Properties.No);
            }
            if(map.containsKey("agua") && map.get("agua")!=null && "on".equals((String)map.get("agua"))){
                pstm.setString(48,Properties.Si);
            }else{
                pstm.setString(48,Properties.No);
            }
            if(map.containsKey("telefono") && map.get("telefono")!=null && "on".equals((String)map.get("telefono"))){
                pstm.setString(49,Properties.Si);
            }else{
                pstm.setString(49,Properties.No);
            }
            if(map.containsKey("desague") && map.get("desague")!=null && "on".equals((String)map.get("desague"))){
                pstm.setString(50,Properties.Si);
            }else{
                pstm.setString(50,Properties.No);
            }
            pstm.setString(51,(String)map.get("numSumLuz"));
            pstm.setString(52,(String)map.get("numConAgua"));
            pstm.setString(53,(String)map.get("numTelefono"));
            pstm.setString(54,(String)map.get("codConTitular"));
            pstm.setString(55,(String)map.get("codForAdqPredio"));
            pstm.setString(56,(String)map.get("fecAdquisicion"));
            pstm.setString(57,(String)map.get("codConEspPredio"));
            pstm.setString(58,(String)map.get("numResExoPredio"));
            pstm.setString(59,(String)map.get("porcentaje"));
            pstm.setString(60,(String)map.get("fecInicio"));
            pstm.setString(61,(String)map.get("fecVencimiento"));
            pstm.setString(62,(String)map.get("fecOcupacion"));
            pstm.setString(63,(String)map.get("codInsRegPublicos"));
            pstm.setString(64,(String)map.get("numInscripcion"));
            pstm.setString(65,(String)map.get("fecInscripcion"));
            pstm.setString(66,(String)map.get("codConIns"));
            pstm.setString(67,(String)map.get("codCatInmueble"));
            pstm.setString(68,(String)map.get("codMonumento"));
            pstm.setString(69,(String)map.get("nomMonumento"));
            pstm.setString(70,(String)map.get("areMonumento"));
            pstm.setString(71,(String)map.get("uniAreMonumento"));
            pstm.setString(72,(String)map.get("perimetro"));
            pstm.setString(73,(String)map.get("codFilCronologica"));
            pstm.setString(74,(String)map.get("preArquitectura"));
            pstm.setString(75,(String)map.get("codTipArquitectura"));
            pstm.setString(76,(String)map.get("codTipMatConstructivo"));
            pstm.setString(77,(String)map.get("codAfeNatural"));
            pstm.setString(78,(String)map.get("codAfeAntropofica"));
            pstm.setString(79,(String)map.get("codIntConservacion"));
            pstm.setString(80,(String)map.get("patrimonio"));
            pstm.setString(81,(String)map.get("denominacion"));
            pstm.setString(82,(String)map.get("codPredio"));
            pstm.setString(83,(String)map.get("numFicha"));
            pstm.setString(84,(String)map.get("codFicha"));


            result = pstm.executeUpdate();

            pstm.close();
            pstm=null;
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            if(pstm!=null){
                try{
                    pstm.close();
                    pstm=null;
                }catch(Exception ignore){}
            }
            throw new DAOException();
        }
        return result;
    }

    public HashMap obtenerPredio(HashMap map) throws DAOException{
        PreparedStatement pstm = null;
        ResultSet rst = null;
        HashMap predio = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT codPredio,numFicha,codTipEdificacion,nomEdificacion,codTipInterior,numInterior,codHabUrbana,nomHabUrbana,");
            sql.append("sector,manzana,lote,sublote,nomPredio,nomValle,nomSector,codProyecto,numFoto,numOrtofoto,codImagen,uniCatAnterior,");
            sql.append("codClaPredio,codSubClaPredio,codUbiPreCatastral,codUsoPreCatastral,desUsoPreCatastral,estructuracion,zonificacion,");
            sql.append("areTerTitulo,areTerDeclarada,areTerVerificada,medCamFrente,medCamDerecha,medCamIzquierda,medCamFondo,medTitFrente,");
            sql.append("medTitDerecha,medTitIzquierda,medTitFondo,colCamFrente,colCamDerecha,colCamIzquierda,colCamFondo,colTitFrente,");
            sql.append("colTitDerecha,colTitIzquierda,colTitFondo,codUso,codClaUsoActual,luz,agua,telefono,desague,numSumLuz,numConAgua,");
            sql.append("numTelefono,codConTitular,codForAdqPredio,date_format(fecAdquisicion,'%d/%m/%Y'),codConEspPredio,numResExoPredio,");
            sql.append("porcentaje,date_format(fecInicio,'%d/%m/%Y'),date_format(fecVencimiento,'%d/%m/%Y'),date_format(fecOcupacion,'%d/%m/%Y'),");
            sql.append("codInsRegPublicos,numInscripcion,date_format(fecInscripcion,'%d/%m/%Y'),codConIns,codCatInmueble,codMonumento,");
            sql.append("nomMonumento,areMonumento,uniAreMonumento,perimetro,codFilCronologica,preArquitectura,codTipArquitectura,");
            sql.append("codTipMatConstructivo,codAfeNatural,codAfeAntropofica,codIntConservacion,patrimonio,denominacion ");
            sql.append("FROM TPREDIO_HIST ");
            sql.append("WHERE numFicha=? AND codFicha=?");

            pstm = context.getConnection().prepareStatement(sql.toString());
            pstm.setString(1,(String)map.get("numFicha"));
            pstm.setString(2,(String)map.get("codFicha"));

            rst = pstm.executeQuery();

            if(rst.next()){
                predio = new HashMap();
                predio.put("codPredio", rst.getString(1));
                predio.put("numFicha", rst.getString(2));
                predio.put("codTipEdificacion", rst.getString(3));
                predio.put("nomEdificacion", rst.getString(4));
                predio.put("codTipInterior", rst.getString(5));
                predio.put("numInterior", rst.getString(6));
                predio.put("codHabUrbana", rst.getString(7));
                predio.put("nomHabUrbana", rst.getString(8));
                predio.put("sector", rst.getString(9));
                predio.put("manzana", rst.getString(10));
                predio.put("lote", rst.getString(11));
                predio.put("sublote", rst.getString(12));
                predio.put("nomPredio", rst.getString(13));
                predio.put("nomValle", rst.getString(14));
                predio.put("nomSector", rst.getString(15));
                predio.put("codProyecto", rst.getString(16));
                predio.put("numFoto", rst.getString(17));
                predio.put("numOrtofoto", rst.getString(18));
                predio.put("codImagen", rst.getString(19));
                predio.put("uniCatAnterior", rst.getString(20));
                predio.put("codClaPredio", rst.getString(21));
                predio.put("codSubClaPredio", rst.getString(22));
                predio.put("codUbiPreCatastral", rst.getString(23));
                predio.put("codUsoPreCatastral", rst.getString(24));
                predio.put("desUsoPreCatastral", rst.getString(25));
                predio.put("estructuracion", rst.getString(26));
                predio.put("zonificacion", rst.getString(27));
                predio.put("areTerTitulo", rst.getString(28));
                predio.put("areTerDeclarada", rst.getString(29));
                predio.put("areTerVerificada", rst.getString(30));
                predio.put("medCamFrente", rst.getString(31));
                predio.put("medCamDerecha", rst.getString(32));
                predio.put("medCamIzquierda", rst.getString(33));
                predio.put("medCamFondo", rst.getString(34));
                predio.put("medTitFrente", rst.getString(35));
                predio.put("medTitDerecha", rst.getString(36));
                predio.put("medTitIzquierda", rst.getString(37));
                predio.put("medTitFondo", rst.getString(38));
                predio.put("colCamFrente", rst.getString(39));
                predio.put("colCamDerecha", rst.getString(40));
                predio.put("colCamIzquierda", rst.getString(41));
                predio.put("colCamFondo", rst.getString(42));
                predio.put("colTitFrente", rst.getString(43));
                predio.put("colTitDerecha", rst.getString(44));
                predio.put("colTitIzquierda", rst.getString(45));
                predio.put("colTitFondo", rst.getString(46));
                predio.put("codUso", rst.getString(47));
                predio.put("codClaUsoActual", rst.getString(48));
                if(Properties.Si.equals(rst.getString(49))){
                    predio.put("luz", Properties.On);
                }else{
                    predio.put("luz", Properties.Off);
                }
                if(Properties.Si.equals(rst.getString(50))){
                    predio.put("agua", Properties.On);
                }else{
                    predio.put("agua", Properties.Off);
                }
                if(Properties.Si.equals(rst.getString(51))){
                    predio.put("telefono", Properties.On);
                }else{
                    predio.put("telefono", Properties.Off);
                }
                if(Properties.Si.equals(rst.getString(52))){
                    predio.put("desague", Properties.On);
                }else{
                    predio.put("desague", Properties.Off);
                }
                predio.put("numSumLuz", rst.getString(53));
                predio.put("numConAgua", rst.getString(54));
                predio.put("numTelefono", rst.getString(55));
                predio.put("codConTitular", rst.getString(56));
                predio.put("codForAdqPredio", rst.getString(57));
                predio.put("fecAdquisicion", rst.getString(58));
                predio.put("codConEspPredio", rst.getString(59));
                predio.put("numResExoPredio", rst.getString(60));
                predio.put("porcentaje", rst.getString(61));
                predio.put("fecInicio", rst.getString(62));
                predio.put("fecVencimiento", rst.getString(63));
                predio.put("fecOcupacion", rst.getString(64));
                predio.put("codInsRegPublicos", rst.getString(65));
                predio.put("numInscripcion", rst.getString(66));
                predio.put("fecInscripcion", rst.getString(67));
                predio.put("codConIns", rst.getString(68));
                predio.put("codCatInmueble", rst.getString(69));
                predio.put("codMonumento", rst.getString(70));
                predio.put("nomMonumento", rst.getString(71));
                predio.put("areMonumento", rst.getString(72));
                predio.put("uniAreMonumento", rst.getString(73));
                predio.put("perimetro", rst.getString(74));
                predio.put("codFilCronologica", rst.getString(75));
                predio.put("preArquitectura", rst.getString(76));
                predio.put("codTipArquitectura", rst.getString(77));
                predio.put("codTipMatConstructivo", rst.getString(78));
                predio.put("codAfeNatural", rst.getString(79));
                predio.put("codAfeAntropofica", rst.getString(80));
                predio.put("codIntConservacion", rst.getString(81));
                predio.put("patrimonio", rst.getString(82));
                predio.put("denominacion", rst.getString(83));
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
        return predio;
    }
}