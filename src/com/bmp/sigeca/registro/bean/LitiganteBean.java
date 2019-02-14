/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.bean;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class LitiganteBean implements Serializable {

    private String codTipDocIdentidad = "";
    private String desTipDocIdentidad = "";
    private String numDocumento = "";
    private String nomCompleto = "";
    private String codContribuyente = "";

    /**
     * @return the codTipDocIdentidad
     */
    public String getCodTipDocIdentidad() {
        return codTipDocIdentidad;
    }

    /**
     * @param codTipDocIdentidad the codTipDocIdentidad to set
     */
    public void setCodTipDocIdentidad(String codTipDocIdentidad) {
        this.codTipDocIdentidad = codTipDocIdentidad;
    }

    /**
     * @return the desTipDocIdentidad
     */
    public String getDesTipDocIdentidad() {
        return desTipDocIdentidad;
    }

    /**
     * @param desTipDocIdentidad the desTipDocIdentidad to set
     */
    public void setDesTipDocIdentidad(String desTipDocIdentidad) {
        this.desTipDocIdentidad = desTipDocIdentidad;
    }

    /**
     * @return the numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * @param numDocumento the numDocumento to set
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * @return the nomCompleto
     */
    public String getNomCompleto() {
        return nomCompleto;
    }

    /**
     * @param nomCompleto the nomCompleto to set
     */
    public void setNomCompleto(String nomCompleto) {
        this.nomCompleto = nomCompleto;
    }

    /**
     * @return the codContribuyente
     */
    public String getCodContribuyente() {
        return codContribuyente;
    }

    /**
     * @param codContribuyente the codContribuyente to set
     */
    public void setCodContribuyente(String codContribuyente) {
        this.codContribuyente = codContribuyente;
    }
}
