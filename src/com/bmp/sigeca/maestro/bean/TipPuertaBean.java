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
public class TipPuertaBean implements Serializable {

    private String codTipPuerta = "";
    private String descripcion = "";

    /**
     * @return the codTipPuerta
     */
    public String getCodTipPuerta() {
        return codTipPuerta;
    }

    /**
     * @param codTipPuerta the codTipPuerta to set
     */
    public void setCodTipPuerta(String codTipPuerta) {
        this.codTipPuerta = codTipPuerta;
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
