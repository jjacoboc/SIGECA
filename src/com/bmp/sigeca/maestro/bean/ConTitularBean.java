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
public class ConTitularBean implements Serializable {

    private String codConTitular = "";
    private String descripcion = "";

    /**
     * @return the codConTitular
     */
    public String getCodConTitular() {
        return codConTitular;
    }

    /**
     * @param codConTitular the codConTitular to set
     */
    public void setCodConTitular(String codConTitular) {
        this.codConTitular = codConTitular;
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
