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
public class ConEspPredioBean implements Serializable {

    private String codConEspPredio = "";
    private String descripcion = "";

    /**
     * @return the codConEspPredio
     */
    public String getCodConEspPredio() {
        return codConEspPredio;
    }

    /**
     * @param codConEspPredio the codConEspPredio to set
     */
    public void setCodConEspPredio(String codConEspPredio) {
        this.codConEspPredio = codConEspPredio;
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
