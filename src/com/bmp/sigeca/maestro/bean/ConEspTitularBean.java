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
public class ConEspTitularBean implements Serializable {

    private String codConEspTitular = "";
    private String descripcion = "";

    /**
     * @return the codConEspTitular
     */
    public String getCodConEspTitular() {
        return codConEspTitular;
    }

    /**
     * @param codConEspTitular the codConEspTitular to set
     */
    public void setCodConEspTitular(String codConEspTitular) {
        this.codConEspTitular = codConEspTitular;
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
