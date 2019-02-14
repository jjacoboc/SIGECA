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
public class DocPresentadoBean implements Serializable {

    private String codDocPresentado = "";
    private String descripcion = "";

    /**
     * @return the codDocPresentado
     */
    public String getCodDocPresentado() {
        return codDocPresentado;
    }

    /**
     * @param codDocPresentado the codDocPresentado to set
     */
    public void setCodDocPresentado(String codDocPresentado) {
        this.codDocPresentado = codDocPresentado;
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
