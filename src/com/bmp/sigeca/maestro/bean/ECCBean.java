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
public class ECCBean implements Serializable {

    private String codECC = "";
    private String descripcion = "";

    /**
     * @return the codECC
     */
    public String getCodECC() {
        return codECC;
    }

    /**
     * @param codECC the codECC to set
     */
    public void setCodECC(String codECC) {
        this.codECC = codECC;
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
