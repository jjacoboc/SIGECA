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
public class TipArquitecturaBean implements Serializable {

    private String codTipArquitectura = "";
    private String descripcion = "";

    /**
     * @return the codTipArquitectura
     */
    public String getCodTipArquitectura() {
        return codTipArquitectura;
    }

    /**
     * @param codTipArquitectura the codTipArquitectura to set
     */
    public void setCodTipArquitectura(String codTipArquitectura) {
        this.codTipArquitectura = codTipArquitectura;
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
