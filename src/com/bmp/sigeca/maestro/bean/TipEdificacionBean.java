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
public class TipEdificacionBean implements Serializable {

    private String codTipEdificacion = "";
    private String descripcion = "";

    /**
     * @return the codTipEdificacion
     */
    public String getCodTipEdificacion() {
        return codTipEdificacion;
    }

    /**
     * @param codTipEdificacion the codTipEdificacion to set
     */
    public void setCodTipEdificacion(String codTipEdificacion) {
        this.codTipEdificacion = codTipEdificacion;
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
