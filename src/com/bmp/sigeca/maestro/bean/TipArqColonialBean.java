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
public class TipArqColonialBean implements Serializable {

    private String codTipArqColonial = "";
    private String descripcion = "";

    /**
     * @return the codTipArqColonial
     */
    public String getCodTipArqColonial() {
        return codTipArqColonial;
    }

    /**
     * @param codTipArqColonial the codTipArqColonial to set
     */
    public void setCodTipArqColonial(String codTipArqColonial) {
        this.codTipArqColonial = codTipArqColonial;
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
