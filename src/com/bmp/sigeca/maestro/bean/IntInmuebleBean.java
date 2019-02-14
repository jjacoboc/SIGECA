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
public class IntInmuebleBean implements Serializable {

    private String codIntInmueble = "";
    private String descripcion = "";

    /**
     * @return the codIntInmueble
     */
    public String getCodIntInmueble() {
        return codIntInmueble;
    }

    /**
     * @param codIntInmueble the codIntInmueble to set
     */
    public void setCodIntInmueble(String codIntInmueble) {
        this.codIntInmueble = codIntInmueble;
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
