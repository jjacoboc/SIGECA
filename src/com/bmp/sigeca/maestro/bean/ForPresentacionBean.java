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
public class ForPresentacionBean implements Serializable {

    private String codForPresentacion = "";
    private String descripcion = "";

    /**
     * @return the codForPresentacion
     */
    public String getCodForPresentacion() {
        return codForPresentacion;
    }

    /**
     * @param codForPresentacion the codForPresentacion to set
     */
    public void setCodForPresentacion(String codForPresentacion) {
        this.codForPresentacion = codForPresentacion;
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
