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
public class TipViaBean implements Serializable {

    private String codTipVia = "";
    private String descripcion = "";

    /**
     * @return the codTipVia
     */
    public String getCodTipVia() {
        return codTipVia;
    }

    /**
     * @param codTipVia the codTipVia to set
     */
    public void setCodTipVia(String codTipVia) {
        this.codTipVia = codTipVia;
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
