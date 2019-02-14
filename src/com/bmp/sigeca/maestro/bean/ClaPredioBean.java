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
public class ClaPredioBean implements Serializable {

    private String codClaPredio = "";
    private String descripcion = "";

    /**
     * @return the codClaPredio
     */
    public String getCodClaPredio() {
        return codClaPredio;
    }

    /**
     * @param codClaPredio the codClaPredio to set
     */
    public void setCodClaPredio(String codClaPredio) {
        this.codClaPredio = codClaPredio;
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
