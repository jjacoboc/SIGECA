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
public class EstLleFichaBean implements Serializable {

    private String codEstLleFicha = "";
    private String descripcion = "";

    /**
     * @return the codEstLleFicha
     */
    public String getCodEstLleFicha() {
        return codEstLleFicha;
    }

    /**
     * @param codEstLleFicha the codEstLleFicha to set
     */
    public void setCodEstLleFicha(String codEstLleFicha) {
        this.codEstLleFicha = codEstLleFicha;
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
