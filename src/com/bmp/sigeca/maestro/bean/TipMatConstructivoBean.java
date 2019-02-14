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
public class TipMatConstructivoBean implements Serializable {

    private String codTipMatConstructivo = "";
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
     * @return the codTipMatConstructivo
     */
    public String getCodTipMatConstructivo() {
        return codTipMatConstructivo;
    }

    /**
     * @param codTipMatConstructivo the codTipMatConstructivo to set
     */
    public void setCodTipMatConstructivo(String codTipMatConstructivo) {
        this.codTipMatConstructivo = codTipMatConstructivo;
    }
}
