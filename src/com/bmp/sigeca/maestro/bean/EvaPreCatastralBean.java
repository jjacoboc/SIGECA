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
public class EvaPreCatastralBean implements Serializable {

    private String codEvaPreCatastral = "";
    private String descripcion = "";

    /**
     * @return the codEvaPreCatastral
     */
    public String getCodEvaPreCatastral() {
        return codEvaPreCatastral;
    }

    /**
     * @param codEvaPreCatastral the codEvaPreCatastral to set
     */
    public void setCodEvaPreCatastral(String codEvaPreCatastral) {
        this.codEvaPreCatastral = codEvaPreCatastral;
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
