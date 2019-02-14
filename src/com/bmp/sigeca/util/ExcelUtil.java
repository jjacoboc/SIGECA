/*
 * ExcelUtil.java
 *
 * Created on 9 de junio de 2007, 08:05 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.bmp.sigeca.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import jxl.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

/**
 *
 * @author Jonatan
 */
public class ExcelUtil {
    
    /** Creates a new instance of ExcelUtil */
    public ExcelUtil() {
        super();
    }
    
    public static void write(String matrix[][], int rows, int cols, String tittle) throws WriteException{
        
        try {
            int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int mes = Calendar.getInstance().get(Calendar.MONTH); 
            int anho = Calendar.getInstance().get(Calendar.YEAR);
            String fecha = StringUtil.getDia(dia)+"."+StringUtil.getMes2(mes)+"."+anho;
            WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\"+tittle+fecha+".xls"));
            WritableSheet sheet = workbook.createSheet(tittle, 0);
            Label label = null;
            
            WritableFont headerFont = new WritableFont(WritableFont.TIMES,9,WritableFont.BOLD,false);
            WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
            headerFormat.setAlignment(Alignment.CENTRE);
            headerFormat.setBackground(Colour.YELLOW);
            headerFormat.setBorder(Border.ALL,BorderLineStyle.DOUBLE);
            
            WritableFont dataFont = new WritableFont(WritableFont.TIMES,8);
            WritableCellFormat dataFormat = new WritableCellFormat(dataFont);
            dataFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
            
            WritableFont tittleFont = new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD,false,UnderlineStyle.SINGLE);
            WritableCellFormat tittleFormat = new WritableCellFormat(tittleFont);
            tittleFormat.setAlignment(Alignment.CENTRE);
            tittleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            
            CellView cf = new CellView();
            cf.setAutosize(true);
            
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(i==0){
                        label = new Label(j,i,matrix[i][j],headerFormat);
                        sheet.addCell(label);
                    }else{
                        label = new Label(j,i,matrix[i][j],dataFormat);
                        sheet.addCell(label);
                    }
                }
            }
            for(int j=0;j<cols;j++){
                sheet.setColumnView(j,cf);
            }
            
            sheet.insertRow(0);
            sheet.insertRow(1);
            sheet.insertRow(2);
            sheet.insertRow(3);
            sheet.insertRow(4);
            sheet.insertRow(5);
            sheet.insertRow(6);
            sheet.insertRow(7);
            sheet.insertRow(8);
            sheet.mergeCells(0,0,cols-1,5);
            sheet.mergeCells(0,6,cols-1,8);
            label = new Label(0,6,tittle,tittleFormat);
            sheet.addCell(label);
            
            WritableImage wi = new WritableImage(0, 0, 2.4, 6, new File("D:\\Proyectos\\PryAndean\\build\\web\\images\\logoAndean.png"));
            sheet.addImage(wi);
            
            sheet.getSettings().setPaperSize(PaperSize.A4);            
            sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE);
            sheet.getSettings().setHorizontalCentre(true);
            sheet.getSettings().setPrintArea(0,0,cols-1,rows+8);
            
            workbook.write();
            workbook.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         
    }
}
