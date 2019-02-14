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
public class AfeAntropicaBean implements Serializable {

    private String codAfeAntropica = "";
    private String descripcion = "";

    /**
     * @return the codAfeAntropica
     */
    public String getCodAfeAntropica() {
        return codAfeAntropica;
    }

    /**
     * @param codAfeAntropica the codAfeAntropica to set
     */
    public void setCodAfeAntropica(String codAfeAntropica) {
        this.codAfeAntropica = codAfeAntropica;
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
