/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import interfaces.Transaccionable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author mauro
 */
public class InformeArticulos {
    public void EmisionDeInformeDeArticulos() throws SQLException{
         HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet();
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        String sql="select *,(select colores.descripcion from colores where colores.numero=articulos.codigoColor)as color from articulos";
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        int col=0;
        int a=0;
            if(a==0){
                fila=hoja.createRow(a);
            
            celda=fila.createCell(1);
            celda.setCellStyle(titulo);
            celda.setCellValue("CODIGO DE BARRA");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("CODIGO ASIGNADO");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("DESCRIPCION");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("TALLE ");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("COLOR");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("PRECIO");
            
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja.createRow(a);
            celda=fila.createCell(1);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda.setCellValue(rs.getInt("ID"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(rs.getString("BARRAS"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda3.setCellValue(rs.getString("NOMBRE"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getInt("talle"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(rs.getString("color"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda6.setCellValue(rs.getDouble("PRECIO"));
            
        }
 
        rs.close();
        //texto+="\r\n";
        String ruta="C://Informes//Articulos.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void EmisionDeInformeDeArticulosStock() throws SQLException{
         HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet();
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        String sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo,(select colores.descripcion from colores where colores.numero=articulos.codigoColor) as descripcionColor from articulos where INHABILITADO=0 order by ID";
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        int col=0;
        int a=0;
            if(a==0){
                fila=hoja.createRow(a);
            
            celda=fila.createCell(1);
            celda.setCellStyle(titulo);
            celda.setCellValue("CODIGO DE BARRA");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("CODIGO ASIGNADO");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("DESCRIPCION");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("TALLE ");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("COLOR");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("STOCK");
            
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja.createRow(a);
            celda=fila.createCell(1);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda.setCellValue(rs.getInt("ID"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(rs.getString("BARRAS"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda3.setCellValue(rs.getString("NOMBRE"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getInt("talle"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(rs.getString("descripcionColor"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda6.setCellValue(rs.getDouble("stock"));
            
        }
 
        rs.close();
        //texto+="\r\n";
        String ruta="C://Informes//Stock de Articulos.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
