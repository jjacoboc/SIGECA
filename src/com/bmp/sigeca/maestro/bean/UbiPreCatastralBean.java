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
public class UbiPreCatastralBean implements Serializable {

    private String codUbiPreCatastral = "";
    private String descripcion = "";

    /**
     * @return the codUbiPreCatastral
     */
    public String getCodUbiPreCatastral() {
        return codUbiPreCatastral;
    }

    /**
     * @param codUbiPreCatastral the codUbiPreCatastral to set
     */
    public void setCodUbiPreCatastral(String codUbiPreCatastral) {
        this.codUbiPreCatastral = codUbiPreCatastral;
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
