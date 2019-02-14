/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.bean;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class DocumentoBean implements Serializable {

    private String codTipDocumento = "";
    private String desTipDocumento = "";
    private String numDocumento = "";
    private String fecha = "";
    private String areAutorizada = "";

    /**
     * @return the codTipDocumento
     */
    public String getCodTipDocumento() {
        return codTipDocumento;
    }

    /**
     * @param codTipDocumento the codTipDocumento to set
     */
    public void setCodTipDocumento(String codTipDocumento) {
        this.codTipDocumento = codTipDocumento;
    }

    /**
     * @return the desTipDocumento
     */
    public String getDesTipDocumento() {
        return desTipDocumento;
    }

    /**
     * @param desTipDocumento the desTipDocumento to set
     */
    public void setDesTipDocumento(String desTipDocumento) {
        this.desTipDocumento = desTipDocumento;
    }

    /**
     * @return the numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * @param numDocumento the numDocumento to set
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the areAutorizada
     */
    public String getAreAutorizada() {
        return areAutorizada;
    }

    /**
     * @param areAutorizada the areAutorizada to set
     */
    public void setAreAutorizada(String areAutorizada) {
        this.areAutorizada = areAutorizada;
    }
}
