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
public class TablasCodigosBean implements Serializable{

    private String id_tabla = "";
    private String codigo = "";
    private String desc_codigo = "";

    public String getId_tabla() {
        return id_tabla;
    }

    public void setId_tabla(String id_tabla) {
        this.id_tabla = id_tabla;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDesc_codigo() {
        return desc_codigo;
    }

    public void setDesc_codigo(String desc_codigo) {
        this.desc_codigo = desc_codigo;
    }
}
