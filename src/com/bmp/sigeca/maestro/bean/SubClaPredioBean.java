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
public class SubClaPredioBean implements Serializable {

    private String codSubClaPredio = "";
    private String descripcion = "";

    /**
     * @return the codSubClaPredio
     */
    public String getCodSubClaPredio() {
        return codSubClaPredio;
    }

    /**
     * @param codSubClaPredio the codSubClaPredio to set
     */
    public void setCodSubClaPredio(String codSubClaPredio) {
        this.codSubClaPredio = codSubClaPredio;
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
