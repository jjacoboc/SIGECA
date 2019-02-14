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
public class UsoBCBean implements Serializable {

    private String codUso = "";
    private String desUso = "";

    public String getCodUso() {
        return codUso;
    }

    public void setCodUso(String codUso) {
        this.codUso = codUso;
    }

    public String getDesUso() {
        return desUso;
    }

    public void setDesUso(String desUso) {
        this.desUso = desUso;
    }

}
