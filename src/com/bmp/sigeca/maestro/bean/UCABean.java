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
public class UCABean implements Serializable {

    private String codUCA = "";
    private String descripcion = "";

    /**
     * @return the codUCA
     */
    public String getCodUCA() {
        return codUCA;
    }

    /**
     * @param codUCA the codUCA to set
     */
    public void setCodUCA(String codUCA) {
        this.codUCA = codUCA;
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
