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
public class PerfilBean implements Serializable {

    private String codPerfil = "";
    private String descripcion = "";

    /**
     * @return the codPerfil
     */
    public String getCodPerfil() {
        return codPerfil;
    }

    /**
     * @param codPerfil the codPerfil to set
     */
    public void setCodPerfil(String codPerfil) {
        this.codPerfil = codPerfil;
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
