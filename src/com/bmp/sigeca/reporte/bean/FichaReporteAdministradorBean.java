/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.reporte.bean;

/**
 *
 * @author Ricardo Avila
 */
public class FichaReporteAdministradorBean {

    private String fechaDigitador;
    private String digitador;
    private String numeroFicha;
    private String fichasInvolucradas;
    private String historial;
    private String fechaRevision;
    private String estado;

    private Integer totalLunes;
    private Integer totalMartes;
    private Integer totalMiercoles;
    private Integer totalJueves;
    private Integer totalViernes;
    private Integer totalSabado;
    private Integer totalDomingo;
    private Integer total;

    public ReporteAdministradorBean  reporteBean ;

    public void calcularTotal()
    {
        total = totalLunes +totalMartes + totalMiercoles + totalJueves +totalViernes +totalSabado +  totalDomingo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalDomingo() {
        return totalDomingo;
    }

    public void setTotalDomingo(Integer totalDomingo) {
        this.totalDomingo = totalDomingo;
    }

    public Integer getTotalJueves() {
        return totalJueves;
    }

    public void setTotalJueves(Integer totalJueves) {
        this.totalJueves = totalJueves;
    }

    public Integer getTotalLunes() {
        return totalLunes;
    }

    public void setTotalLunes(Integer totalLunes) {
        this.totalLunes = totalLunes;
    }

    public Integer getTotalMartes() {
        return totalMartes;
    }

    public void setTotalMartes(Integer totalMartes) {
        this.totalMartes = totalMartes;
    }

    public Integer getTotalMiercoles() {
        return totalMiercoles;
    }

    public void setTotalMiercoles(Integer totalMiercoles) {
        this.totalMiercoles = totalMiercoles;
    }

    public Integer getTotalSabado() {
        return totalSabado;
    }

    public void setTotalSabado(Integer totalSabado) {
        this.totalSabado = totalSabado;
    }

    public Integer getTotalViernes() {
        return totalViernes;
    }

    public void setTotalViernes(Integer totalViernes) {
        this.totalViernes = totalViernes;
    }


    public ReporteAdministradorBean getReporteBean() {
        return reporteBean;
    }

    public void setReporteBean(ReporteAdministradorBean reporteBean) {
        this.reporteBean = reporteBean;
    }

    
    public String getDigitador() {
        return digitador;
    }

    public void setDigitador(String digitador) {
        this.digitador = digitador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaDigitador() {
        return fechaDigitador;
    }

    public void setFechaDigitador(String fechaDigitador) {
        this.fechaDigitador = fechaDigitador;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getFichasInvolucradas() {
        return fichasInvolucradas;
    }

    public void setFichasInvolucradas(String fichasInvolucradas) {
        this.fichasInvolucradas = fichasInvolucradas;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

  
}
