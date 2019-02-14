/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.bean;

/**
 *
 * @author Ricardo Avila
 */
public class FichaReporteUsuarioBean {

    private String cuc= "";
     private String  titularCatastral = "";
     private String  nroDocumento = "";
     private String crc = "";
     private String  ubicacionPredio= "";
     private Double  areaLote = new Double(0);
     private Double  areaConstruida= new Double(0);
     private String clasificacionPredio= "";
     private String  usoPredioCatastral= "";
     public ReporteUsuarioBean reporteBean;

    public ReporteUsuarioBean getReporteBean() {
        return reporteBean;
    }

    public void setReporteBean(ReporteUsuarioBean reporteBean) {
        this.reporteBean = reporteBean;
    }


    public Double getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(Double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public Double getAreaLote() {
        return areaLote;
    }

    public void setAreaLote(Double areaLote) {
        this.areaLote = areaLote;
    }

    public String getClasificacionPredio() {
        return clasificacionPredio;
    }

    public void setClasificacionPredio(String clasificacionPredio) {
        this.clasificacionPredio = clasificacionPredio;
    }

    public String getCrc() {

        if(crc.equals(""))
            return cuc;
        
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getCuc() {
        return cuc;
    }

    public void setCuc(String cuc) {
        this.cuc = cuc;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTitularCatastral() {
        return titularCatastral;
    }

    public void setTitularCatastral(String titularCatastral) {
        this.titularCatastral = titularCatastral;
    }

    public String getUbicacionPredio() {
        return ubicacionPredio;
    }

    public void setUbicacionPredio(String ubicacionPredio) {
        this.ubicacionPredio = ubicacionPredio;
    }

    public String getUsoPredioCatastral() {
        return usoPredioCatastral;
    }

    public void setUsoPredioCatastral(String usoPredioCatastral) {
        this.usoPredioCatastral = usoPredioCatastral;
    }

}
