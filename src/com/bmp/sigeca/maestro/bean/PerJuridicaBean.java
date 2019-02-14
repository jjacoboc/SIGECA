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
public class PerJuridicaBean implements Serializable {

    private String codPerJuridica = "";
    private String descripcion = "";

    /**
     * @return the codPerJuridica
     */
    public String getCodPerJuridica() {
        return codPerJuridica;
    }

    /**
     * @param codPerJuridica the codPerJuridica to set
     */
    public void setCodPerJuridica(String codPerJuridica) {
        this.codPerJuridica = codPerJuridica;
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
