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
public class FilEstilisticaBean implements Serializable {

    private String codFilEstilistica = "";
    private String descripcion = "";

    /**
     * @return the codFilEstilistica
     */
    public String getCodFilEstilistica() {
        return codFilEstilistica;
    }

    /**
     * @param codFilEstilistica the codFilEstilistica to set
     */
    public void setCodFilEstilistica(String codFilEstilistica) {
        this.codFilEstilistica = codFilEstilistica;
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
