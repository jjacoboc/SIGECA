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
public class TipTitularBean implements Serializable {

    private String codTipTitular = "";
    private String descripcion = "";

    /**
     * @return the codTipTitular
     */
    public String getCodTipTitular() {
        return codTipTitular;
    }

    /**
     * @param codTipTitular the codTipTitular to set
     */
    public void setCodTipTitular(String codTipTitular) {
        this.codTipTitular = codTipTitular;
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
