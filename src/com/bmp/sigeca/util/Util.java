/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bmp.sigeca.util;

import com.bmp.sigeca.registro.bean.FileBean;
import com.bmp.sigeca.resource.Properties;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author admin
 */
public class Util extends DispatchAction{

    public void crearArchivo(String filePath, String fileName, FileBean bfile)throws java.lang.Exception {

        try{
            ResourceBundle rb = ResourceBundle.getBundle(Properties.getParametros());
            File direc = new File(filePath);
            direc.mkdirs();
            File file = new File(bfile.getFilePath(), bfile.getFileName());
            DataInputStream in = new DataInputStream(new FileInputStream(file));

            File filetoCreate = new File(filePath, fileName);
            FileOutputStream fileOutStream = new FileOutputStream(filetoCreate);
            int length = 0;
            byte[] bbuf = new byte[Integer.parseInt(rb.getString("fileSize"))];
            while((in != null) && ((length = in.read(bbuf)) != -1)){
                fileOutStream.write(bbuf,0,length);
            }
            fileOutStream.flush();
            fileOutStream.close();
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public Date fecha(String s) {
        int year = 0;
        int month = 0;
        int day = 0;
        int firstSlash = 0;
        int secondSlash = 0;
        try{
            if (s == null) throw new java.lang.IllegalArgumentException();

            firstSlash = s.indexOf('/');
            secondSlash = s.indexOf('/', firstSlash+1);
            if ((firstSlash > 0) & (secondSlash > 0) & (secondSlash < s.length()-1)) {
                day = Integer.parseInt(s.substring(0, firstSlash));	    
                month = Integer.parseInt(s.substring(firstSlash+1, secondSlash))-1;
                year = Integer.parseInt(s.substring(secondSlash+1)) - 1900;
            } else {
                throw new java.lang.IllegalArgumentException();
            }
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
	return new Date(year, month, day);
    }
    
    public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z]{2,9}.)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }        
    }

    public boolean isCorrectFileName(String fileName) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]{1,})\\.[a-zA-Z]{3}");
        mat = pat.matcher(fileName);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }
    }
    
    public static int getDiffMonths(Date date1, Date date2) {
        long timeInMillis = date1.getTime() - date2.getTime();
        double number = 1000 * 60 * 60 * 24.0015 * 30.43675;
        double meses = timeInMillis / number;
        return (int)meses;
    }    
    
    public static int getDiffDays(Date date1, Date date2) {
        long timeInMillis = date1.getTime() - date2.getTime();
        double number = 1000 * 60 * 60 * 24.0015;
        double days = timeInMillis / number;
        return (int)days;
    }
}
