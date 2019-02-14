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
public class ConConductorBean implements Serializable {

    private String codConConductor = "";
    private String descripcion = "";

    /**
     * @return the codConConductor
     */
    public String getCodConConductor() {
        return codConConductor;
    }

    /**
     * @param codConConductor the codConConductor to set
     */
    public void setCodConConductor(String codConConductor) {
        this.codConConductor = codConConductor;
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
