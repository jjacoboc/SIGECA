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
public class InstalacionBean implements Serializable {

    private String cod_instalacion = "";
    private String desc_instalacion = "";
    private String material = "";
    private String unidad = "";

    public String getCod_instalacion() {
        return cod_instalacion;
    }

    public void setCod_instalacion(String cod_instalacion) {
        this.cod_instalacion = cod_instalacion;
    }

    public String getDesc_instalacion() {
        return desc_instalacion;
    }

    public void setDesc_instalacion(String desc_instalacion) {
        this.desc_instalacion = desc_instalacion;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
