/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.bean;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class TipDocumentoBean implements Serializable {

    private String codTipDocumento = "";
    private String descripcion = "";

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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
