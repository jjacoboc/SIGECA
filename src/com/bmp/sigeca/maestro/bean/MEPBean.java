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
public class MEPBean implements Serializable {

    private String codMEP = "";
    private String descripcion = "";

    /**
     * @return the codMEP
     */
    public String getCodMEP() {
        return codMEP;
    }

    /**
     * @param codMEP the codMEP to set
     */
    public void setCodMEP(String codMEP) {
        this.codMEP = codMEP;
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
