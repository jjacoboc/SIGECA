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
public class CatInmuebleBean implements Serializable {

    private String codCatInmueble = "";
    private String descripcion = "";

    /**
     * @return the codCatInmueble
     */
    public String getCodCatInmueble() {
        return codCatInmueble;
    }

    /**
     * @param codCatInmueble the codCatInmueble to set
     */
    public void setCodCatInmueble(String codCatInmueble) {
        this.codCatInmueble = codCatInmueble;
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
