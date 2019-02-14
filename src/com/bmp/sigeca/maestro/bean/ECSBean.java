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
public class ECSBean implements Serializable {

    private String codECS = "";
    private String descripcion = "";

    /**
     * @return the codECS
     */
    public String getCodECS() {
        return codECS;
    }

    /**
     * @param codECS the codECS to set
     */
    public void setCodECS(String codECS) {
        this.codECS = codECS;
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
