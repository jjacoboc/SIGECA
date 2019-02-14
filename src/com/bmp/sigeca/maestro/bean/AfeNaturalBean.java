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
public class AfeNaturalBean implements Serializable {

    private String codAfeNatural = "";
    private String descripcion = "";

    /**
     * @return the codAfeNatural
     */
    public String getCodAfeNatural() {
        return codAfeNatural;
    }

    /**
     * @param codAfeNatural the codAfeNatural to set
     */
    public void setCodAfeNatural(String codAfeNatural) {
        this.codAfeNatural = codAfeNatural;
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
