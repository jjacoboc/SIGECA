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
public class MantenimientoBean implements Serializable {

    private String codMantenimiento = "";
    private String descripcion = "";

    /**
     * @return the codMantenimiento
     */
    public String getCodMantenimiento() {
        return codMantenimiento;
    }

    /**
     * @param codMantenimiento the codMantenimiento to set
     */
    public void setCodMantenimiento(String codMantenimiento) {
        this.codMantenimiento = codMantenimiento;
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
