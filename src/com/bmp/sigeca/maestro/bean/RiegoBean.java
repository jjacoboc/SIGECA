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
public class RiegoBean implements Serializable {

    private String codTipRiego = "";
    private String descripcion = "";

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

    /**
     * @return the codTipRiego
     */
    public String getCodTipRiego() {
        return codTipRiego;
    }

    /**
     * @param codTipRiego the codTipRiego to set
     */
    public void setCodTipRiego(String codTipRiego) {
        this.codTipRiego = codTipRiego;
    }
}
