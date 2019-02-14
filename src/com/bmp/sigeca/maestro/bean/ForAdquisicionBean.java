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
public class ForAdquisicionBean implements Serializable {

    private String codForAdquisicion = "";
    private String descripcion = "";

    /**
     * @return the codForAdquisicion
     */
    public String getCodForAdquisicion() {
        return codForAdquisicion;
    }

    /**
     * @param codForAdquisicion the codForAdquisicion to set
     */
    public void setCodForAdquisicion(String codForAdquisicion) {
        this.codForAdquisicion = codForAdquisicion;
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
