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
public class TablaBean implements Serializable {

    private String codTabla = "";
    private String nomFisico = "";
    private String descripcion = "";
    private String comentario = "";

    /**
     * @return the codTabla
     */
    public String getCodTabla() {
        return codTabla;
    }

    /**
     * @param codTabla the codTabla to set
     */
    public void setCodTabla(String codTabla) {
        this.codTabla = codTabla;
    }

    /**
     * @return the nomFisico
     */
    public String getNomFisico() {
        return nomFisico;
    }

    /**
     * @param nomFisico the nomFisico to set
     */
    public void setNomFisico(String nomFisico) {
        this.nomFisico = nomFisico;
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

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
