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
public class TipInteriorBean implements Serializable {

    private String codTipInterior = "";
    private String descripcion = "";

    /**
     * @return the codTipInterior
     */
    public String getCodTipInterior() {
        return codTipInterior;
    }

    /**
     * @param codTipInterior the codTipInterior to set
     */
    public void setCodTipInterior(String codTipInterior) {
        this.codTipInterior = codTipInterior;
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
