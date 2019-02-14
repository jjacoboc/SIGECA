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
public class EstCivilBean implements Serializable {

    private String codEstCivil = "";
    private String descripcion = "";

    /**
     * @return the codEstCivil
     */
    public String getCodEstCivil() {
        return codEstCivil;
    }

    /**
     * @param codEstCivil the codEstCivil to set
     */
    public void setCodEstCivil(String codEstCivil) {
        this.codEstCivil = codEstCivil;
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
