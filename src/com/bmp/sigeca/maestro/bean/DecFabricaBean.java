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
public class DecFabricaBean implements Serializable {

    private String codDecFabrica = "";
    private String descripcion = "";

    /**
     * @return the codDecFabrica
     */
    public String getCodDecFabrica() {
        return codDecFabrica;
    }

    /**
     * @param codDecFabrica the codDecFabrica to set
     */
    public void setCodDecFabrica(String codDecFabrica) {
        this.codDecFabrica = codDecFabrica;
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
