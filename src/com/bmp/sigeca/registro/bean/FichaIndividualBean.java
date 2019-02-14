/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.registro.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class FichaIndividualBean implements Serializable {

    /* UBICACION DEL PREDIO CATASTRAL */
    private String indListaVia = "";
    private List listaVia = null;
    private String codTipEdificacionPre = "";
    private String nomEdificacionPre = "";
    private String codTipInteriorPre = "";
    private String numInteriorPre = "";
    private String codHUPre = "";
    private String nomHUPre = "";
    private String sectorPre = "";
    private String manzanaPre = "";
    private String lotePre = "";
    private String sublotePre = "";
    /* ****************************** */
    /* IDENTIFICACION DEL TITULAR CATASTRAL */
    private String codTipTitular = "";
    private String codTipDocIdentidad = "";
    private String numDocIdentidad = "";
    private String codEstCivil = "";
    private String nombre = "";
    private String apePaterno = "";
    private String apeMaterno = "";
    private String numRuc = "";
    private String razSocial = "";
    private String codConEspTitular = "";
    private String codPerJuridica = "";
    private String numResExoneracion = "";
    private String numBolPensionista = "";
    private String fecIniExoneracion = "";
    private String fecFinExoneracion = "";
    /* ************************************ */
    /* DOMICILIO FISCAL DEL TITULAR CATASTRAL */
    private String codDepartamento = "";
    private String codProvincia = "";
    private String codDistrito = "";
    private String telefonoTit = "";
    private String anexoTit = "";
    private String faxTit = "";
    private String correoTit = "";
    private String codViaTit = "";
    private String codTipViaTit = "";
    private String nomViaTit = "";
    private String numMunicipalTit = "";
    private String nomEdificacionTit = "";
    private String numInterior = "";
    private String codHUTit = "";
    private String nomHUTit = "";
    private String sectorTit = "";
    private String manzanaTit = "";
    private String loteTit = "";
    private String subloteTit = "";
    /* ************************************** */
    /* CARACTERISTICAS DE LA TITULARIDAD */
    private String codConTitular = "";
    private String codForAdquisicion = "";
    private String fecAdquisicion = "";
    private String codConEspPredio = "";
    private String numResExoPredio = "";
    private String porcentaje = "";
    private String fecInicio = "";
    private String fecVencimiento = "";
    /* ********************************* */
    /* DESCRIPCION DEL PREDIO */
    private String codClaPredio = "";
    private String codSubClaPredio = "";
    private String codUbiPreCatastral = "";
    private String codUso = "";
    private String usoPreCatastral = "";
    private String estructuracion = "";
    private String zonificacion = "";
    private String areTerTitulo = "";
    private String areTerDeclarada = "";
    private String areTerVerificada = "";
    private String medCamFrente = "";
    private String medTitFrente = "";
    private String colCamFrente = "";
    private String colTitFrente = "";
    private String medCamDerecha = "";
    private String medTitDerecha = "";
    private String colCamDerecha = "";
    private String colTitDerecha = "";
    private String medCamIzquierda = "";
    private String medTitIzquierda = "";
    private String colCamIzquierda = "";
    private String colTitIzquierda = "";
    private String medCamFondo = "";
    private String medTitFondo = "";
    private String colCamFondo = "";
    private String colTitFondo = "";
    /* ********************** */
    /* SERVICIOS BASICOS */
    private String luz = "";
    private String numSumLuz = "";
    private String agua = "";
    private String numConAgua = "";
    private String telefono = "";
    private String numTelefono = "";
    private String desague = "";
    /* ***************** */
    /* CONSTRUCCIONES */
    private String indListaConstruccion = "";
    private List listaConstruccion = null;
    private String terLegal = "";
    private String terFisico = "";
    private String conLegal = "";
    private String conFisica = "";
    /* ************** */
    /* OBRAS COMPLEMENTARIAS */
    private String indListaObra = "";
    private List listaObra = null;
    /* ********************* */
    /* DOCUMENTOS */
    private String indListaDocumento = "";
    private List listaDocumento = null;
    private String nomNotaria = "";
    private String kardex = "";
    private String fecEscPublica = "";
    /* ********** */
    /* INSCRIPCIÓN DEL PREDIO CATASTRAL EN EL REGISTRO DE PREDIOS */
    private String codTipParRegistral = "";
    private String numPartida = "";
    private String fojas = "";
    private String asiento = "";
    private String fecInsPredio = "";
    private String codDecFabrica = "";
    private String asInsFabrica = "";
    private String fecInsFabrica = "";
    /* ********************************************************** */
    /* EVALUACIÓN DEL PREDIO CATASTRAL */
    private String codEvaPreCatastral = "";
    private String areTerInvLotColindante = "";
    private String areTerInvArePublica = "";
    private String areTerInvJarAislamiento = "";
    private String areTerInvAreIntangible = "";
    /* ******************************* */
    /* INFORMACIÓN COMPLEMENTARIA */
    private String indListaLitigante = "";
    private List listaLitigante = null;
    private String codConDeclarante = "";
    private String codEstLleFicha = "";
    private String numHabitantes = "";
    private String numFamilias = "";
    private String codMantenimiento = "";
    /* ************************** */
    /* OBSERVACIONES */
    private String observacion = "";

    /**
     * @return the indListaVia
     */
    public String getIndListaVia() {
        return indListaVia;
    }

    /**
     * @param indListaVia the indListaVia to set
     */
    public void setIndListaVia(String indListaVia) {
        this.indListaVia = indListaVia;
    }

    /**
     * @return the listaVia
     */
    public List getListaVia() {
        return listaVia;
    }

    /**
     * @param listaVia the listaVia to set
     */
    public void setListaVia(List listaVia) {
        this.listaVia = listaVia;
    }

    /**
     * @return the codTipEdificacionPre
     */
    public String getCodTipEdificacionPre() {
        return codTipEdificacionPre;
    }

    /**
     * @param codTipEdificacionPre the codTipEdificacionPre to set
     */
    public void setCodTipEdificacionPre(String codTipEdificacionPre) {
        this.codTipEdificacionPre = codTipEdificacionPre;
    }

    /**
     * @return the nomEdificacionPre
     */
    public String getNomEdificacionPre() {
        return nomEdificacionPre;
    }

    /**
     * @param nomEdificacionPre the nomEdificacionPre to set
     */
    public void setNomEdificacionPre(String nomEdificacionPre) {
        this.nomEdificacionPre = nomEdificacionPre;
    }

    /**
     * @return the codTipInteriorPre
     */
    public String getCodTipInteriorPre() {
        return codTipInteriorPre;
    }

    /**
     * @param codTipInteriorPre the codTipInteriorPre to set
     */
    public void setCodTipInteriorPre(String codTipInteriorPre) {
        this.codTipInteriorPre = codTipInteriorPre;
    }

    /**
     * @return the numInteriorPre
     */
    public String getNumInteriorPre() {
        return numInteriorPre;
    }

    /**
     * @param numInteriorPre the numInteriorPre to set
     */
    public void setNumInteriorPre(String numInteriorPre) {
        this.numInteriorPre = numInteriorPre;
    }

    /**
     * @return the codHUPre
     */
    public String getCodHUPre() {
        return codHUPre;
    }

    /**
     * @param codHUPre the codHUPre to set
     */
    public void setCodHUPre(String codHUPre) {
        this.codHUPre = codHUPre;
    }

    /**
     * @return the nomHUPre
     */
    public String getNomHUPre() {
        return nomHUPre;
    }

    /**
     * @param nomHUPre the nomHUPre to set
     */
    public void setNomHUPre(String nomHUPre) {
        this.nomHUPre = nomHUPre;
    }

    /**
     * @return the sectorPre
     */
    public String getSectorPre() {
        return sectorPre;
    }

    /**
     * @param sectorPre the sectorPre to set
     */
    public void setSectorPre(String sectorPre) {
        this.sectorPre = sectorPre;
    }

    /**
     * @return the manzanaPre
     */
    public String getManzanaPre() {
        return manzanaPre;
    }

    /**
     * @param manzanaPre the manzanaPre to set
     */
    public void setManzanaPre(String manzanaPre) {
        this.manzanaPre = manzanaPre;
    }

    /**
     * @return the lotePre
     */
    public String getLotePre() {
        return lotePre;
    }

    /**
     * @param lotePre the lotePre to set
     */
    public void setLotePre(String lotePre) {
        this.lotePre = lotePre;
    }

    /**
     * @return the sublotePre
     */
    public String getSublotePre() {
        return sublotePre;
    }

    /**
     * @param sublotePre the sublotePre to set
     */
    public void setSublotePre(String sublotePre) {
        this.sublotePre = sublotePre;
    }

    /**
     * @return the codTipTitular
     */
    public String getCodTipTitular() {
        return codTipTitular;
    }

    /**
     * @param codTipTitular the codTipTitular to set
     */
    public void setCodTipTitular(String codTipTitular) {
        this.codTipTitular = codTipTitular;
    }

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
     * @return the numDocIdentidad
     */
    public String getNumDocIdentidad() {
        return numDocIdentidad;
    }

    /**
     * @param numDocIdentidad the numDocIdentidad to set
     */
    public void setNumDocIdentidad(String numDocIdentidad) {
        this.numDocIdentidad = numDocIdentidad;
    }

    /**
     * @return the codEstCivil
     */
    public String getCodEstCivil() {
        return codEstCivil;
    }

    /**
     * @param codEstCivil the codEstCivil to set
     */
    public void setCodEstCivil(String codEstCivil) {
        this.codEstCivil = codEstCivil;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apePaterno
     */
    public String getApePaterno() {
        return apePaterno;
    }

    /**
     * @param apePaterno the apePaterno to set
     */
    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    /**
     * @return the apeMaterno
     */
    public String getApeMaterno() {
        return apeMaterno;
    }

    /**
     * @param apeMaterno the apeMaterno to set
     */
    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    /**
     * @return the numRuc
     */
    public String getNumRuc() {
        return numRuc;
    }

    /**
     * @param numRuc the numRuc to set
     */
    public void setNumRuc(String numRuc) {
        this.numRuc = numRuc;
    }

    /**
     * @return the razSocial
     */
    public String getRazSocial() {
        return razSocial;
    }

    /**
     * @param razSocial the razSocial to set
     */
    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

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
     * @return the codPerJuridica
     */
    public String getCodPerJuridica() {
        return codPerJuridica;
    }

    /**
     * @param codPerJuridica the codPerJuridica to set
     */
    public void setCodPerJuridica(String codPerJuridica) {
        this.codPerJuridica = codPerJuridica;
    }

    /**
     * @return the numResExoneracion
     */
    public String getNumResExoneracion() {
        return numResExoneracion;
    }

    /**
     * @param numResExoneracion the numResExoneracion to set
     */
    public void setNumResExoneracion(String numResExoneracion) {
        this.numResExoneracion = numResExoneracion;
    }

    /**
     * @return the numBolPensionista
     */
    public String getNumBolPensionista() {
        return numBolPensionista;
    }

    /**
     * @param numBolPensionista the numBolPensionista to set
     */
    public void setNumBolPensionista(String numBolPensionista) {
        this.numBolPensionista = numBolPensionista;
    }

    /**
     * @return the fecIniExoneracion
     */
    public String getFecIniExoneracion() {
        return fecIniExoneracion;
    }

    /**
     * @param fecIniExoneracion the fecIniExoneracion to set
     */
    public void setFecIniExoneracion(String fecIniExoneracion) {
        this.fecIniExoneracion = fecIniExoneracion;
    }

    /**
     * @return the fecFinExoneracion
     */
    public String getFecFinExoneracion() {
        return fecFinExoneracion;
    }

    /**
     * @param fecFinExoneracion the fecFinExoneracion to set
     */
    public void setFecFinExoneracion(String fecFinExoneracion) {
        this.fecFinExoneracion = fecFinExoneracion;
    }

    /**
     * @return the codDepartamento
     */
    public String getCodDepartamento() {
        return codDepartamento;
    }

    /**
     * @param codDepartamento the codDepartamento to set
     */
    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    /**
     * @return the codProvincia
     */
    public String getCodProvincia() {
        return codProvincia;
    }

    /**
     * @param codProvincia the codProvincia to set
     */
    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    /**
     * @return the codDistrito
     */
    public String getCodDistrito() {
        return codDistrito;
    }

    /**
     * @param codDistrito the codDistrito to set
     */
    public void setCodDistrito(String codDistrito) {
        this.codDistrito = codDistrito;
    }

    /**
     * @return the telefonoTit
     */
    public String getTelefonoTit() {
        return telefonoTit;
    }

    /**
     * @param telefonoTit the telefonoTit to set
     */
    public void setTelefonoTit(String telefonoTit) {
        this.telefonoTit = telefonoTit;
    }

    /**
     * @return the anexoTit
     */
    public String getAnexoTit() {
        return anexoTit;
    }

    /**
     * @param anexoTit the anexoTit to set
     */
    public void setAnexoTit(String anexoTit) {
        this.anexoTit = anexoTit;
    }

    /**
     * @return the faxTit
     */
    public String getFaxTit() {
        return faxTit;
    }

    /**
     * @param faxTit the faxTit to set
     */
    public void setFaxTit(String faxTit) {
        this.faxTit = faxTit;
    }

    /**
     * @return the correoTit
     */
    public String getCorreoTit() {
        return correoTit;
    }

    /**
     * @param correoTit the correoTit to set
     */
    public void setCorreoTit(String correoTit) {
        this.correoTit = correoTit;
    }

    /**
     * @return the codViaTit
     */
    public String getCodViaTit() {
        return codViaTit;
    }

    /**
     * @param codViaTit the codViaTit to set
     */
    public void setCodViaTit(String codViaTit) {
        this.codViaTit = codViaTit;
    }

    /**
     * @return the codTipViaTit
     */
    public String getCodTipViaTit() {
        return codTipViaTit;
    }

    /**
     * @param codTipViaTit the codTipViaTit to set
     */
    public void setCodTipViaTit(String codTipViaTit) {
        this.codTipViaTit = codTipViaTit;
    }

    /**
     * @return the nomViaTit
     */
    public String getNomViaTit() {
        return nomViaTit;
    }

    /**
     * @param nomViaTit the nomViaTit to set
     */
    public void setNomViaTit(String nomViaTit) {
        this.nomViaTit = nomViaTit;
    }

    /**
     * @return the numMunicipalTit
     */
    public String getNumMunicipalTit() {
        return numMunicipalTit;
    }

    /**
     * @param numMunicipalTit the numMunicipalTit to set
     */
    public void setNumMunicipalTit(String numMunicipalTit) {
        this.numMunicipalTit = numMunicipalTit;
    }

    /**
     * @return the nomEdificacionTit
     */
    public String getNomEdificacionTit() {
        return nomEdificacionTit;
    }

    /**
     * @param nomEdificacionTit the nomEdificacionTit to set
     */
    public void setNomEdificacionTit(String nomEdificacionTit) {
        this.nomEdificacionTit = nomEdificacionTit;
    }

    /**
     * @return the numInterior
     */
    public String getNumInterior() {
        return numInterior;
    }

    /**
     * @param numInterior the numInterior to set
     */
    public void setNumInterior(String numInterior) {
        this.numInterior = numInterior;
    }

    /**
     * @return the codHUTit
     */
    public String getCodHUTit() {
        return codHUTit;
    }

    /**
     * @param codHUTit the codHUTit to set
     */
    public void setCodHUTit(String codHUTit) {
        this.codHUTit = codHUTit;
    }

    /**
     * @return the nomHUTit
     */
    public String getNomHUTit() {
        return nomHUTit;
    }

    /**
     * @param nomHUTit the nomHUTit to set
     */
    public void setNomHUTit(String nomHUTit) {
        this.nomHUTit = nomHUTit;
    }

    /**
     * @return the sectorTit
     */
    public String getSectorTit() {
        return sectorTit;
    }

    /**
     * @param sectorTit the sectorTit to set
     */
    public void setSectorTit(String sectorTit) {
        this.sectorTit = sectorTit;
    }

    /**
     * @return the manzanaTit
     */
    public String getManzanaTit() {
        return manzanaTit;
    }

    /**
     * @param manzanaTit the manzanaTit to set
     */
    public void setManzanaTit(String manzanaTit) {
        this.manzanaTit = manzanaTit;
    }

    /**
     * @return the loteTit
     */
    public String getLoteTit() {
        return loteTit;
    }

    /**
     * @param loteTit the loteTit to set
     */
    public void setLoteTit(String loteTit) {
        this.loteTit = loteTit;
    }

    /**
     * @return the subloteTit
     */
    public String getSubloteTit() {
        return subloteTit;
    }

    /**
     * @param subloteTit the subloteTit to set
     */
    public void setSubloteTit(String subloteTit) {
        this.subloteTit = subloteTit;
    }

    /**
     * @return the codConTitular
     */
    public String getCodConTitular() {
        return codConTitular;
    }

    /**
     * @param codConTitular the codConTitular to set
     */
    public void setCodConTitular(String codConTitular) {
        this.codConTitular = codConTitular;
    }

    /**
     * @return the codForAdquisicion
     */
    public String getCodForAdquisicion() {
        return codForAdquisicion;
    }

    /**
     * @param codForAdquisicion the codForAdquisicion to set
     */
    public void setCodForAdquisicion(String codForAdquisicion) {
        this.codForAdquisicion = codForAdquisicion;
    }

    /**
     * @return the fecAdquisicion
     */
    public String getFecAdquisicion() {
        return fecAdquisicion;
    }

    /**
     * @param fecAdquisicion the fecAdquisicion to set
     */
    public void setFecAdquisicion(String fecAdquisicion) {
        this.fecAdquisicion = fecAdquisicion;
    }

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
     * @return the numResExoPredio
     */
    public String getNumResExoPredio() {
        return numResExoPredio;
    }

    /**
     * @param numResExoPredio the numResExoPredio to set
     */
    public void setNumResExoPredio(String numResExoPredio) {
        this.numResExoPredio = numResExoPredio;
    }

    /**
     * @return the porcentaje
     */
    public String getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * @return the fecInicio
     */
    public String getFecInicio() {
        return fecInicio;
    }

    /**
     * @param fecInicio the fecInicio to set
     */
    public void setFecInicio(String fecInicio) {
        this.fecInicio = fecInicio;
    }

    /**
     * @return the fecVencimiento
     */
    public String getFecVencimiento() {
        return fecVencimiento;
    }

    /**
     * @param fecVencimiento the fecVencimiento to set
     */
    public void setFecVencimiento(String fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    /**
     * @return the codClaPredio
     */
    public String getCodClaPredio() {
        return codClaPredio;
    }

    /**
     * @param codClaPredio the codClaPredio to set
     */
    public void setCodClaPredio(String codClaPredio) {
        this.codClaPredio = codClaPredio;
    }

    /**
     * @return the codSubClaPredio
     */
    public String getCodSubClaPredio() {
        return codSubClaPredio;
    }

    /**
     * @param codSubClaPredio the codSubClaPredio to set
     */
    public void setCodSubClaPredio(String codSubClaPredio) {
        this.codSubClaPredio = codSubClaPredio;
    }

    /**
     * @return the codUbiPreCatastral
     */
    public String getCodUbiPreCatastral() {
        return codUbiPreCatastral;
    }

    /**
     * @param codUbiPreCatastral the codUbiPreCatastral to set
     */
    public void setCodUbiPreCatastral(String codUbiPreCatastral) {
        this.codUbiPreCatastral = codUbiPreCatastral;
    }

    /**
     * @return the codUso
     */
    public String getCodUso() {
        return codUso;
    }

    /**
     * @param codUso the codUso to set
     */
    public void setCodUso(String codUso) {
        this.codUso = codUso;
    }

    /**
     * @return the usoPreCatastral
     */
    public String getUsoPreCatastral() {
        return usoPreCatastral;
    }

    /**
     * @param usoPreCatastral the usoPreCatastral to set
     */
    public void setUsoPreCatastral(String usoPreCatastral) {
        this.usoPreCatastral = usoPreCatastral;
    }

    /**
     * @return the estructuracion
     */
    public String getEstructuracion() {
        return estructuracion;
    }

    /**
     * @param estructuracion the estructuracion to set
     */
    public void setEstructuracion(String estructuracion) {
        this.estructuracion = estructuracion;
    }

    /**
     * @return the zonificacion
     */
    public String getZonificacion() {
        return zonificacion;
    }

    /**
     * @param zonificacion the zonificacion to set
     */
    public void setZonificacion(String zonificacion) {
        this.zonificacion = zonificacion;
    }

    /**
     * @return the areTerTitulo
     */
    public String getAreTerTitulo() {
        return areTerTitulo;
    }

    /**
     * @param areTerTitulo the areTerTitulo to set
     */
    public void setAreTerTitulo(String areTerTitulo) {
        this.areTerTitulo = areTerTitulo;
    }

    /**
     * @return the areTerDeclarada
     */
    public String getAreTerDeclarada() {
        return areTerDeclarada;
    }

    /**
     * @param areTerDeclarada the areTerDeclarada to set
     */
    public void setAreTerDeclarada(String areTerDeclarada) {
        this.areTerDeclarada = areTerDeclarada;
    }

    /**
     * @return the areTerVerificada
     */
    public String getAreTerVerificada() {
        return areTerVerificada;
    }

    /**
     * @param areTerVerificada the areTerVerificada to set
     */
    public void setAreTerVerificada(String areTerVerificada) {
        this.areTerVerificada = areTerVerificada;
    }

    /**
     * @return the medCamFrente
     */
    public String getMedCamFrente() {
        return medCamFrente;
    }

    /**
     * @param medCamFrente the medCamFrente to set
     */
    public void setMedCamFrente(String medCamFrente) {
        this.medCamFrente = medCamFrente;
    }

    /**
     * @return the medTitFrente
     */
    public String getMedTitFrente() {
        return medTitFrente;
    }

    /**
     * @param medTitFrente the medTitFrente to set
     */
    public void setMedTitFrente(String medTitFrente) {
        this.medTitFrente = medTitFrente;
    }

    /**
     * @return the colCamFrente
     */
    public String getColCamFrente() {
        return colCamFrente;
    }

    /**
     * @param colCamFrente the colCamFrente to set
     */
    public void setColCamFrente(String colCamFrente) {
        this.colCamFrente = colCamFrente;
    }

    /**
     * @return the colTitFrente
     */
    public String getColTitFrente() {
        return colTitFrente;
    }

    /**
     * @param colTitFrente the colTitFrente to set
     */
    public void setColTitFrente(String colTitFrente) {
        this.colTitFrente = colTitFrente;
    }

    /**
     * @return the medCamDerecha
     */
    public String getMedCamDerecha() {
        return medCamDerecha;
    }

    /**
     * @param medCamDerecha the medCamDerecha to set
     */
    public void setMedCamDerecha(String medCamDerecha) {
        this.medCamDerecha = medCamDerecha;
    }

    /**
     * @return the medTitDerecha
     */
    public String getMedTitDerecha() {
        return medTitDerecha;
    }

    /**
     * @param medTitDerecha the medTitDerecha to set
     */
    public void setMedTitDerecha(String medTitDerecha) {
        this.medTitDerecha = medTitDerecha;
    }

    /**
     * @return the colCamDerecha
     */
    public String getColCamDerecha() {
        return colCamDerecha;
    }

    /**
     * @param colCamDerecha the colCamDerecha to set
     */
    public void setColCamDerecha(String colCamDerecha) {
        this.colCamDerecha = colCamDerecha;
    }

    /**
     * @return the colTitDerecha
     */
    public String getColTitDerecha() {
        return colTitDerecha;
    }

    /**
     * @param colTitDerecha the colTitDerecha to set
     */
    public void setColTitDerecha(String colTitDerecha) {
        this.colTitDerecha = colTitDerecha;
    }

    /**
     * @return the medCamIzquierda
     */
    public String getMedCamIzquierda() {
        return medCamIzquierda;
    }

    /**
     * @param medCamIzquierda the medCamIzquierda to set
     */
    public void setMedCamIzquierda(String medCamIzquierda) {
        this.medCamIzquierda = medCamIzquierda;
    }

    /**
     * @return the medTitIzquierda
     */
    public String getMedTitIzquierda() {
        return medTitIzquierda;
    }

    /**
     * @param medTitIzquierda the medTitIzquierda to set
     */
    public void setMedTitIzquierda(String medTitIzquierda) {
        this.medTitIzquierda = medTitIzquierda;
    }

    /**
     * @return the colCamIzquierda
     */
    public String getColCamIzquierda() {
        return colCamIzquierda;
    }

    /**
     * @param colCamIzquierda the colCamIzquierda to set
     */
    public void setColCamIzquierda(String colCamIzquierda) {
        this.colCamIzquierda = colCamIzquierda;
    }

    /**
     * @return the colTitIzquierda
     */
    public String getColTitIzquierda() {
        return colTitIzquierda;
    }

    /**
     * @param colTitIzquierda the colTitIzquierda to set
     */
    public void setColTitIzquierda(String colTitIzquierda) {
        this.colTitIzquierda = colTitIzquierda;
    }

    /**
     * @return the medCamFondo
     */
    public String getMedCamFondo() {
        return medCamFondo;
    }

    /**
     * @param medCamFondo the medCamFondo to set
     */
    public void setMedCamFondo(String medCamFondo) {
        this.medCamFondo = medCamFondo;
    }

    /**
     * @return the medTitFondo
     */
    public String getMedTitFondo() {
        return medTitFondo;
    }

    /**
     * @param medTitFondo the medTitFondo to set
     */
    public void setMedTitFondo(String medTitFondo) {
        this.medTitFondo = medTitFondo;
    }

    /**
     * @return the colCamFondo
     */
    public String getColCamFondo() {
        return colCamFondo;
    }

    /**
     * @param colCamFondo the colCamFondo to set
     */
    public void setColCamFondo(String colCamFondo) {
        this.colCamFondo = colCamFondo;
    }

    /**
     * @return the colTitFondo
     */
    public String getColTitFondo() {
        return colTitFondo;
    }

    /**
     * @param colTitFondo the colTitFondo to set
     */
    public void setColTitFondo(String colTitFondo) {
        this.colTitFondo = colTitFondo;
    }

    /**
     * @return the luz
     */
    public String getLuz() {
        return luz;
    }

    /**
     * @param luz the luz to set
     */
    public void setLuz(String luz) {
        this.luz = luz;
    }

    /**
     * @return the numSumLuz
     */
    public String getNumSumLuz() {
        return numSumLuz;
    }

    /**
     * @param numSumLuz the numSumLuz to set
     */
    public void setNumSumLuz(String numSumLuz) {
        this.numSumLuz = numSumLuz;
    }

    /**
     * @return the agua
     */
    public String getAgua() {
        return agua;
    }

    /**
     * @param agua the agua to set
     */
    public void setAgua(String agua) {
        this.agua = agua;
    }

    /**
     * @return the numConAgua
     */
    public String getNumConAgua() {
        return numConAgua;
    }

    /**
     * @param numConAgua the numConAgua to set
     */
    public void setNumConAgua(String numConAgua) {
        this.numConAgua = numConAgua;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the numTelefono
     */
    public String getNumTelefono() {
        return numTelefono;
    }

    /**
     * @param numTelefono the numTelefono to set
     */
    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    /**
     * @return the desague
     */
    public String getDesague() {
        return desague;
    }

    /**
     * @param desague the desague to set
     */
    public void setDesague(String desague) {
        this.desague = desague;
    }

    /**
     * @return the indListaConstruccion
     */
    public String getIndListaConstruccion() {
        return indListaConstruccion;
    }

    /**
     * @param indListaConstruccion the indListaConstruccion to set
     */
    public void setIndListaConstruccion(String indListaConstruccion) {
        this.indListaConstruccion = indListaConstruccion;
    }

    /**
     * @return the listaConstruccion
     */
    public List getListaConstruccion() {
        return listaConstruccion;
    }

    /**
     * @param listaConstruccion the listaConstruccion to set
     */
    public void setListaConstruccion(List listaConstruccion) {
        this.listaConstruccion = listaConstruccion;
    }

    /**
     * @return the terLegal
     */
    public String getTerLegal() {
        return terLegal;
    }

    /**
     * @param terLegal the terLegal to set
     */
    public void setTerLegal(String terLegal) {
        this.terLegal = terLegal;
    }

    /**
     * @return the terFisico
     */
    public String getTerFisico() {
        return terFisico;
    }

    /**
     * @param terFisico the terFisico to set
     */
    public void setTerFisico(String terFisico) {
        this.terFisico = terFisico;
    }

    /**
     * @return the conLegal
     */
    public String getConLegal() {
        return conLegal;
    }

    /**
     * @param conLegal the conLegal to set
     */
    public void setConLegal(String conLegal) {
        this.conLegal = conLegal;
    }

    /**
     * @return the conFisica
     */
    public String getConFisica() {
        return conFisica;
    }

    /**
     * @param conFisica the conFisica to set
     */
    public void setConFisica(String conFisica) {
        this.conFisica = conFisica;
    }

    /**
     * @return the indListaObra
     */
    public String getIndListaObra() {
        return indListaObra;
    }

    /**
     * @param indListaObra the indListaObra to set
     */
    public void setIndListaObra(String indListaObra) {
        this.indListaObra = indListaObra;
    }

    /**
     * @return the listaObra
     */
    public List getListaObra() {
        return listaObra;
    }

    /**
     * @param listaObra the listaObra to set
     */
    public void setListaObra(List listaObra) {
        this.listaObra = listaObra;
    }

    /**
     * @return the indListaDocumento
     */
    public String getIndListaDocumento() {
        return indListaDocumento;
    }

    /**
     * @param indListaDocumento the indListaDocumento to set
     */
    public void setIndListaDocumento(String indListaDocumento) {
        this.indListaDocumento = indListaDocumento;
    }

    /**
     * @return the listaDocumento
     */
    public List getListaDocumento() {
        return listaDocumento;
    }

    /**
     * @param listaDocumento the listaDocumento to set
     */
    public void setListaDocumento(List listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    /**
     * @return the nomNotaria
     */
    public String getNomNotaria() {
        return nomNotaria;
    }

    /**
     * @param nomNotaria the nomNotaria to set
     */
    public void setNomNotaria(String nomNotaria) {
        this.nomNotaria = nomNotaria;
    }

    /**
     * @return the kardex
     */
    public String getKardex() {
        return kardex;
    }

    /**
     * @param kardex the kardex to set
     */
    public void setKardex(String kardex) {
        this.kardex = kardex;
    }

    /**
     * @return the fecEscPublica
     */
    public String getFecEscPublica() {
        return fecEscPublica;
    }

    /**
     * @param fecEscPublica the fecEscPublica to set
     */
    public void setFecEscPublica(String fecEscPublica) {
        this.fecEscPublica = fecEscPublica;
    }

    /**
     * @return the codTipParRegistral
     */
    public String getCodTipParRegistral() {
        return codTipParRegistral;
    }

    /**
     * @param codTipParRegistral the codTipParRegistral to set
     */
    public void setCodTipParRegistral(String codTipParRegistral) {
        this.codTipParRegistral = codTipParRegistral;
    }

    /**
     * @return the numPartida
     */
    public String getNumPartida() {
        return numPartida;
    }

    /**
     * @param numPartida the numPartida to set
     */
    public void setNumPartida(String numPartida) {
        this.numPartida = numPartida;
    }

    /**
     * @return the fojas
     */
    public String getFojas() {
        return fojas;
    }

    /**
     * @param fojas the fojas to set
     */
    public void setFojas(String fojas) {
        this.fojas = fojas;
    }

    /**
     * @return the asiento
     */
    public String getAsiento() {
        return asiento;
    }

    /**
     * @param asiento the asiento to set
     */
    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    /**
     * @return the fecInsPredio
     */
    public String getFecInsPredio() {
        return fecInsPredio;
    }

    /**
     * @param fecInsPredio the fecInsPredio to set
     */
    public void setFecInsPredio(String fecInsPredio) {
        this.fecInsPredio = fecInsPredio;
    }

    /**
     * @return the codDecFabrica
     */
    public String getCodDecFabrica() {
        return codDecFabrica;
    }

    /**
     * @param codDecFabrica the codDecFabrica to set
     */
    public void setCodDecFabrica(String codDecFabrica) {
        this.codDecFabrica = codDecFabrica;
    }

    /**
     * @return the asInsFabrica
     */
    public String getAsInsFabrica() {
        return asInsFabrica;
    }

    /**
     * @param asInsFabrica the asInsFabrica to set
     */
    public void setAsInsFabrica(String asInsFabrica) {
        this.asInsFabrica = asInsFabrica;
    }

    /**
     * @return the fecInsFabrica
     */
    public String getFecInsFabrica() {
        return fecInsFabrica;
    }

    /**
     * @param fecInsFabrica the fecInsFabrica to set
     */
    public void setFecInsFabrica(String fecInsFabrica) {
        this.fecInsFabrica = fecInsFabrica;
    }

    /**
     * @return the codEvaPreCatastral
     */
    public String getCodEvaPreCatastral() {
        return codEvaPreCatastral;
    }

    /**
     * @param codEvaPreCatastral the codEvaPreCatastral to set
     */
    public void setCodEvaPreCatastral(String codEvaPreCatastral) {
        this.codEvaPreCatastral = codEvaPreCatastral;
    }

    /**
     * @return the areTerInvLotColindante
     */
    public String getAreTerInvLotColindante() {
        return areTerInvLotColindante;
    }

    /**
     * @param areTerInvLotColindante the areTerInvLotColindante to set
     */
    public void setAreTerInvLotColindante(String areTerInvLotColindante) {
        this.areTerInvLotColindante = areTerInvLotColindante;
    }

    /**
     * @return the areTerInvArePublica
     */
    public String getAreTerInvArePublica() {
        return areTerInvArePublica;
    }

    /**
     * @param areTerInvArePublica the areTerInvArePublica to set
     */
    public void setAreTerInvArePublica(String areTerInvArePublica) {
        this.areTerInvArePublica = areTerInvArePublica;
    }

    /**
     * @return the areTerInvJarAislamiento
     */
    public String getAreTerInvJarAislamiento() {
        return areTerInvJarAislamiento;
    }

    /**
     * @param areTerInvJarAislamiento the areTerInvJarAislamiento to set
     */
    public void setAreTerInvJarAislamiento(String areTerInvJarAislamiento) {
        this.areTerInvJarAislamiento = areTerInvJarAislamiento;
    }

    /**
     * @return the areTerInvAreIntangible
     */
    public String getAreTerInvAreIntangible() {
        return areTerInvAreIntangible;
    }

    /**
     * @param areTerInvAreIntangible the areTerInvAreIntangible to set
     */
    public void setAreTerInvAreIntangible(String areTerInvAreIntangible) {
        this.areTerInvAreIntangible = areTerInvAreIntangible;
    }

    /**
     * @return the indListaLitigante
     */
    public String getIndListaLitigante() {
        return indListaLitigante;
    }

    /**
     * @param indListaLitigante the indListaLitigante to set
     */
    public void setIndListaLitigante(String indListaLitigante) {
        this.indListaLitigante = indListaLitigante;
    }

    /**
     * @return the listaLitigante
     */
    public List getListaLitigante() {
        return listaLitigante;
    }

    /**
     * @param listaLitigante the listaLitigante to set
     */
    public void setListaLitigante(List listaLitigante) {
        this.listaLitigante = listaLitigante;
    }

    /**
     * @return the codConDeclarante
     */
    public String getCodConDeclarante() {
        return codConDeclarante;
    }

    /**
     * @param codConDeclarante the codConDeclarante to set
     */
    public void setCodConDeclarante(String codConDeclarante) {
        this.codConDeclarante = codConDeclarante;
    }

    /**
     * @return the codEstLleFicha
     */
    public String getCodEstLleFicha() {
        return codEstLleFicha;
    }

    /**
     * @param codEstLleFicha the codEstLleFicha to set
     */
    public void setCodEstLleFicha(String codEstLleFicha) {
        this.codEstLleFicha = codEstLleFicha;
    }

    /**
     * @return the numHabitantes
     */
    public String getNumHabitantes() {
        return numHabitantes;
    }

    /**
     * @param numHabitantes the numHabitantes to set
     */
    public void setNumHabitantes(String numHabitantes) {
        this.numHabitantes = numHabitantes;
    }

    /**
     * @return the numFamilias
     */
    public String getNumFamilias() {
        return numFamilias;
    }

    /**
     * @param numFamilias the numFamilias to set
     */
    public void setNumFamilias(String numFamilias) {
        this.numFamilias = numFamilias;
    }

    /**
     * @return the codMantenimiento
     */
    public String getCodMantenimiento() {
        return codMantenimiento;
    }

    /**
     * @param codMantenimiento the codMantenimiento to set
     */
    public void setCodMantenimiento(String codMantenimiento) {
        this.codMantenimiento = codMantenimiento;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    /* ************************** */
}
