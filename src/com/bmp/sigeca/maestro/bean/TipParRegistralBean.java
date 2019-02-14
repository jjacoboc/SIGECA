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
public class TipParRegistralBean implements Serializable {

    private String codTipParRegistral = "";
    private String descripcion = "";

    /**
     * @return the codTipParRegistral
     */
    public String getCodTipParRegistral() {
        return codTipParRegistral;
    }

    /**
     * @param codTipParRegistral the codTipParRegistral to set
     */
    public void setCodTipParRegistral(String codTipParRegistral) {
        this.codTipParRegistral = codTipParRegistral;
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
