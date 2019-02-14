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
public class ConDeclaranteBean implements Serializable {

    private String codConDeclarante = "";
    private String descripcion = "";

    /**
     * @return the codConDeclarante
     */
    public String getCodConDeclarante() {
        return codConDeclarante;
    }

    /**
     * @param codConDeclarante the codConDeclarante to set
     */
    public void setCodConDeclarante(String codConDeclarante) {
        this.codConDeclarante = codConDeclarante;
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
