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
public class ConNumeracionBean implements Serializable {

    private String codConNumeracion = "";
    private String descripcion = "";

    /**
     * @return the codConNumeracion
     */
    public String getCodConNumeracion() {
        return codConNumeracion;
    }

    /**
     * @param codConNumeracion the codConNumeracion to set
     */
    public void setCodConNumeracion(String codConNumeracion) {
        this.codConNumeracion = codConNumeracion;
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
