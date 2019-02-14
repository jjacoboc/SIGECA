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
public class FilCronologicaBean implements Serializable {

    private String codFilCronologica = "";
    private String descripcion = "";

    /**
     * @return the codFilCronologica
     */
    public String getCodFilCronologica() {
        return codFilCronologica;
    }

    /**
     * @param codFilCronologica the codFilCronologica to set
     */
    public void setCodFilCronologica(String codFilCronologica) {
        this.codFilCronologica = codFilCronologica;
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
