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
public class TipDocIdentidadBean implements Serializable {

    private String codTipDocIdentidad = "";
    private String descripcion = "";

    /**
     * @return the codTipDocIdentidad
     */
    public String getCodTipDocIdentidad() {
        return codTipDocIdentidad;
    }

    /**
     * @param codTipDocIdentidad the codTipDocIdentidad to set
     */
    public void setCodTipDocIdentidad(String codTipDocIdentidad) {
        this.codTipDocIdentidad = codTipDocIdentidad;
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
