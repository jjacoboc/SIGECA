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
public class ClaUsoActualBean implements Serializable {

    private String codClaUsoActual = "";
    private String descripcion = "";

    /**
     * @return the codClaUsoActual
     */
    public String getCodClaUsoActual() {
        return codClaUsoActual;
    }

    /**
     * @param codClaUsoActual the codClaUsoActual to set
     */
    public void setCodClaUsoActual(String codClaUsoActual) {
        this.codClaUsoActual = codClaUsoActual;
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
