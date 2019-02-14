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
public class IntConservacionBean implements Serializable {

    private String codIntConservacion = "";
    private String descripcion = "";

    /**
     * @return the codIntConservacion
     */
    public String getCodIntConservacion() {
        return codIntConservacion;
    }

    /**
     * @param codIntConservacion the codIntConservacion to set
     */
    public void setCodIntConservacion(String codIntConservacion) {
        this.codIntConservacion = codIntConservacion;
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
