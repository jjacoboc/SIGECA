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
public class EstElementoBean implements Serializable {

    private String codEstElemento = "";
    private String descripcion = "";

    /**
     * @return the codEstElemento
     */
    public String getCodEstElemento() {
        return codEstElemento;
    }

    /**
     * @param codEstElemento the codEstElemento to set
     */
    public void setCodEstElemento(String codEstElemento) {
        this.codEstElemento = codEstElemento;
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
