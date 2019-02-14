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
public class ImaSatelitalBean implements Serializable {

    private String codImagen = "";
    private String descripcion = "";

    /**
     * @return the codImagen
     */
    public String getCodImagen() {
        return codImagen;
    }

    /**
     * @param codImagen the codImagen to set
     */
    public void setCodImagen(String codImagen) {
        this.codImagen = codImagen;
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
