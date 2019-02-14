/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.action;


import com.bmp.sigeca.reporte.bean.FichaReporteUsuarioBean;
import com.bmp.sigeca.reporte.service.ReporteUsuarioService;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Ricardo Avila
 */
public class ReporteUsuarioAction extends DispatchAction {

   protected Logger logg;



    public ActionForward reportePorUbicacion(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

          logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario Por Ubicacion");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteUsuarioService reporteUsuarioService = new ReporteUsuarioService();
                List<FichaReporteUsuarioBean> lst = reporteUsuarioService.getLstReportePorUbicacion(map);

               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/reportPorUbicacion.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_Por_Ubicacion.pdf;");
		response.setContentLength(bytes.length);

                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();

            }catch(Exception e){
                e.getMessage();
                e.printStackTrace();
            }

           return actionMapping.findForward("consulta");
    }

     public ActionForward reportePorTitulares(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

           logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario por titulares y area");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteUsuarioService reporteUsuarioService = new ReporteUsuarioService();
                List<FichaReporteUsuarioBean> lst = reporteUsuarioService.getLstReportePorTitularesArea(map);

               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/reportPorTitularesArea.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_Por_Titulares_Area.pdf;");
		response.setContentLength(bytes.length);

                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();

            }catch(Exception e){
                e.getMessage();
                e.printStackTrace();
            }


           return actionMapping.findForward("consulta");
    }

      public ActionForward reportePorCodigo(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

           logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario por titulares y area");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteUsuarioService reporteUsuarioService = new ReporteUsuarioService();
                List<FichaReporteUsuarioBean> lst = reporteUsuarioService.getLstReportePorCodigo(map);

               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/reportPorCodigo.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_Por_Codigo.pdf;");
		response.setContentLength(bytes.length);

                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();

            }catch(Exception e){
                e.getMessage();
                e.printStackTrace();
            }



           return actionMapping.findForward("consulta");
    }

       public ActionForward reportePorDocumento(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

           logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario por titulares y area");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteUsuarioService reporteUsuarioService = new ReporteUsuarioService();
                List<FichaReporteUsuarioBean> lst = reporteUsuarioService.getLstReportePorDocumento(map);

               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/reportPorDocumento.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_Por_Documento.pdf;");
		response.setContentLength(bytes.length);

                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();

            }catch(Exception e){
                e.getMessage();
                e.printStackTrace();
            }

           return actionMapping.findForward("consulta");
    }


        public ActionForward reportePorUbicacionPredio(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

             logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario por ubicacion y predio");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteUsuarioService reporteUsuarioService = new ReporteUsuarioService();
                List<FichaReporteUsuarioBean> lst = reporteUsuarioService.getLstReportePorUbicacionPredio(map);

               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/reportPorUbicacionPredio.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_Por_Ubicacion_Descripcion_Predio.pdf;");
		response.setContentLength(bytes.length);

                out.write(bytes, 0, bytes.length);
                out.flush();
                out.close();

            }catch(Exception e){
                e.getMessage();
                e.printStackTrace();
            }


           return actionMapping.findForward("consulta");
    }
}
