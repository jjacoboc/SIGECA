/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.util;

/**
 *
 * @author jjacobo
 */
public class StringUtil {

    public static final String EMPTY_STRING="";
    public static final String ZERO_STRING="0";
    
    public StringUtil() {
    }
        
    public static boolean esVacio(String cadena){
        return((cadena==null)||(cadena.trim().length()==0) );
    }
    
    public static String nullAsEmptyString(String cadena){
        if(cadena==null) return EMPTY_STRING;
        return cadena;
    }

    public static String emptyStringAsNull(String cadena){
        if("".equals(cadena)) return null;
        return cadena;
    }
    
    /**
     * Si la cadena pasada es vacia (devuelve true para la funcion esVacio) devuelve una cadena numÃ©rica
     * que representa un cero entero :"0". Si no, devuelve la misma cadena
     */
    public static String emptyAsZero(String cadena){
        if(esVacio(cadena)) return ZERO_STRING;
        else return cadena;
    }

    public static String invertirFecha(String date){
        
        if(!"".equals(date) && !date.equals(null)){
            String c = Character.toString(date.charAt(2));
            String newDate = "";
            if("/".equals(c) || "-".equals(c) || ".".equals(c)){ //formato dd/mm/aaaa
                String day = date.substring(0,2);
                String month = date.substring(3,5);
                String year = date.substring(6,10);
                newDate = year+"/"+month+"/"+day;
            }else{ //formato aaaa/mm/dd
                String day = date.substring(8,10);
                String month = date.substring(5,7);
                String year = date.substring(0,4);
                newDate = day+"/"+month+"/"+year;
            }
            return newDate;
        }else
            return date;
    }
    
    public static String getDia(int d){
        String cad = null;
        if(d/10 > 0)
            cad = ""+d;
        else
            cad = "0"+d;
        return cad;
    }
  
    public static String getDiaSemana(int value){      
        String cad = null;
        switch(value){
            case 1 : cad = "Domingo"; break;
            case 2 : cad = "Lunes"; break;
            case 3 : cad = "Martes"; break;
            case 4 : cad = "MiÃ©rcoles"; break;
            case 5 : cad = "Jueves"; break;
            case 6 : cad = "Viernes"; break;
            case 7 : cad = "SÃ¡bado"; break; 
        }      
        return cad;
    }
  
    public static String getMes(int m){
        String cad = null;
	switch (m) {
		case 0 : cad = "Enero";break;
		case 1 : cad = "Febrero";break;
		case 2 : cad = "Marzo";break;
		case 3 : cad = "Abril";break;
		case 4 : cad = "Mayo";break;
		case 5 : cad = "Junio";break;
		case 6 : cad = "Julio";break;
		case 7 : cad = "Agosto";break;
		case 8 : cad = "Septiembre";break;
		case 9 : cad = "Octubre";break;
		case 10 : cad = "Noviembre";break;
        case 11 : cad = "Diciembre";break;
	}
	return cad;		
    }
  
    public static String getMes2(int m){		
        String cad = null;
        m++;
        if(m/10 > 0)
            cad = ""+m;
        else
            cad = "0"+m;
        return cad;
    }
    
    /**
     * Devuelve la cantidad de meses comprendidos entre la Fecha de Inicio y la Fecha de Fin inclusive.
     * @param String fecha1: Fecha de inicio
     * @param String fecha2: Fecha de Fin
     * @return int meses: Cantidad de meses.
     */
    public static int getMesesEntre(String fecha1, String fecha2){
        int m1 = 0;
        int a1 = 0;
        int m2 = 0;
        int a2 = 0;
        int meses = 0;
        if(fecha1!=null && !"".equals(fecha1)){
            m1 = Integer.parseInt(fecha1.substring(3,5));
            a1 = Integer.parseInt(fecha1.substring(6,10));
        }else{
            m1 = 0;
            a1 = 0;
        }
        if(fecha2!=null && !"".equals(fecha2)){
            m2 = Integer.parseInt(fecha2.substring(3,5));
            a2 = Integer.parseInt(fecha2.substring(6,10));
        }else{
            m2 = 0;
            a2 = 0;
        }
        if(a1==0 && a2==0 && m1==0 && m2==0){
            meses = 0;
        }else{
            meses = (a2*12 + m2) - (a1*12 + m1);
        }
        return meses;
    }

    public static String putLeftZeros(String cadena, int tamaño){
        try{
            int cantidad = tamaño - cadena.length();
            String zeros = "";
            for(int i=1;i<=cantidad;i++){
                zeros = zeros + "0";
            }
            cadena = zeros.concat(cadena);
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return cadena;
    }
}
