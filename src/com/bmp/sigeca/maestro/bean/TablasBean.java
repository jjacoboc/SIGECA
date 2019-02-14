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
public class TablasBean implements Serializable{

    private String id_tabla = "";
    private String desc_tabla = "";

    public String getId_tabla() {
        return id_tabla;
    }

    public void setId_tabla(String id_tabla) {
        this.id_tabla = id_tabla;
    }

    public String getDesc_tabla() {
        return desc_tabla;
    }

    public void setDesc_tabla(String desc_tabla) {
        this.desc_tabla = desc_tabla;
    }
}
