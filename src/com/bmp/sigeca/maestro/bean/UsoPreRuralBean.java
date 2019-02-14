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
public class UsoPreRuralBean implements Serializable {

    private String codUso = "";
    private String descripcion = "";

    /**
     * @return the codUso
     */
    public String getCodUso() {
        return codUso;
    }

    /**
     * @param codUso the codUso to set
     */
    public void setCodUso(String codUso) {
        this.codUso = codUso;
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
