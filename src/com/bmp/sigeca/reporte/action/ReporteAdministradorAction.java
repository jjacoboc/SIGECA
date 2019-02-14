/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.action;


import com.bmp.sigeca.reporte.bean.FichaReporteAdministradorBean;
import com.bmp.sigeca.reporte.service.ReporteAdministradorService;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ReporteAdministradorAction extends DispatchAction {

     protected Logger logg;
      public ActionForward reporte01Detallado(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

          logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario Por Ubicacion");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteAdministradorService reporteAdministradorService = new ReporteAdministradorService();
                List<FichaReporteAdministradorBean> lst = reporteAdministradorService.getLstReporte01Detallado(map);

               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/report01Detallado.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_01_Detallado.pdf;");
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

     public ActionForward reporte02Detallado(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

           logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario por titulares y area");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteAdministradorService reporteAdministradorService = new ReporteAdministradorService();
                List<FichaReporteAdministradorBean> lst = reporteAdministradorService.getLstReporte02Detallado(map);

               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/reporte02Detallado.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_02_Detallado.pdf;");
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

      public ActionForward reporte01General(ActionMapping actionMapping,
           ActionForm actionForm,
           HttpServletRequest request,
           HttpServletResponse response) throws java.lang.Exception {

           logg = Logger.getLogger(this.getClass());
          logg.debug(" Log en Reporte Usuario por titulares y area");

            try{
                DynaActionForm form = (DynaActionForm)actionForm;
                HashMap map = (HashMap)form.getMap();


                ReporteAdministradorService reporteAdministradorService = new ReporteAdministradorService();
                List<FichaReporteAdministradorBean> lst = reporteAdministradorService.getLstReporte01General(map);


               //generamos el reporte
                JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lst);
                JasperReport jasperReport = (JasperReport)JRLoader.loadObject (request.getRealPath("/jasper/reporte01General.jasper"));

                JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap(), ds);
                byte[] bytes = JasperExportManager.exportReportToPdf(print);


                //generando el reporte PDF
                OutputStream out = response.getOutputStream();
                response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment;filename=Reporte_Usuario_01_General.pdf;");
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
