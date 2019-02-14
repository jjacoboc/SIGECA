/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.maestro.bean;

import java.io.Serializable;

/**
 *
 * @author Jonatan Jacobo
 */
public class NotariaBean implements Serializable {

    private String idNotaria = "";
    private String nomNotaria = "";
    private String ubigeo = "";

    public String getIdNotaria() {
        return idNotaria;
    }

    public void setIdNotaria(String idNotaria) {
        this.idNotaria = idNotaria;
    }

    public String getNomNotaria() {
        return nomNotaria;
    }

    public void setNomNotaria(String nomNotaria) {
        this.nomNotaria = nomNotaria;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }
}
