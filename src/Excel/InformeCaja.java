/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import interfaceGraficas.Inicio;
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
public class InformeCaja {
    public static void Generar() throws SQLException{
        HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("VENTAS");
        HSSFSheet hoja1=libro.createSheet("ARTICULOS");
        HSSFSheet hoja2=libro.createSheet("SEÑAS");
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFCell celda7;
        HSSFCell celda8;
        HSSFCell celda9;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        String sql="select *,(select tipomovimientos.descripcion from tipomovimientos where tipomovimientos.id=movimientoscaja.tipoMovimiento) as tipo from movimientoscaja where idCaja ="+Inicio.caja.getNumero();
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
            celda.setCellValue("Numero Comprobante");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Tipo");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Efectivo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Debito");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Credito");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            
            }
            while(rs.next()){
            a++;
            col=rs.getInt("tipoMovimiento");
            fila=hoja.createRow(a);
            celda=fila.createCell(1);
            celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda.setCellValue(rs.getInt("numeroComprobante"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(rs.getString("tipo"));

            switch(col){
                case 1:
                    int pagad=rs.getInt("pagado");
                    switch(pagad){
                        case 1:
                            celda3=fila.createCell(3);
                            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            celda3.setCellValue(rs.getDouble("monto"));
                            break;
                        case 2:
                            celda4=fila.createCell(4);
                            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            celda4.setCellValue(rs.getDouble("monto"));
                            break;
                        default:
                            celda5=fila.createCell(5);
                            celda5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            celda5.setCellValue(rs.getDouble("monto"));
                            break;
                    }
                    break;
                    
                    
                default:
                    celda3=fila.createCell(3);
                    celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda3.setCellValue(rs.getDouble("monto"));  
                    break;
            }
            
        }
 
        a=0;
        fila=hoja1.createRow(a);
           sql="select *,(select listcli.RAZON_SOCI from listcli where listcli.COD_CLIENT=movimientosarticulos.numeroCliente)as cliente,(select articulos.BARRAS from articulos where articulos.ID=movimientosarticulos.idArticulo)as articulos,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo)as descripcion from movimientosarticulos where fecha like '"+Inicio.fechaDia+"%'";
           rs=tra.leerConjuntoDeRegistros(sql);
            celda=fila.createCell(1);
            celda.setCellStyle(titulo);
            celda.setCellValue("Numero Comprobante");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Cliente");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Articulo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Descripcion");
            
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Cantidad");
            /*
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            */
            while(rs.next()){
                a++;
                fila=hoja1.createRow(a);
                celda=fila.createCell(1);
                celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda.setCellValue(rs.getInt("numeroComprobante"));
                celda2=fila.createCell(2);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda2.setCellValue(rs.getString("cliente"));
                celda3=fila.createCell(3);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda3.setCellValue(rs.getString("articulos"));
                celda4=fila.createCell(4);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda4.setCellValue(rs.getString("descripcion"));
                Double cant=rs.getDouble("cantidad");
                celda5=fila.createCell(5);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda5.setCellValue(cant);
            /*
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            */
            }
            a=0;
        fila=hoja2.createRow(a);
           sql="select *,(select listcli.RAZON_SOCI from listcli where listcli.id=movimientossena.codigoCliente)as nombreCliente,(select usuarios.nombre from usuarios where usuarios.numero=movimientossena.codigoUsuario)as nombreUsuario from movimientossena where aplicado=0";
           rs=tra.leerConjuntoDeRegistros(sql);
            celda=fila.createCell(1);
            celda.setCellStyle(titulo);
            celda.setCellValue("Numero Comprobante");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Cliente");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Monto");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Recibio");
            
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha Recepción");
            /*
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            */
            while(rs.next()){
                a++;
                fila=hoja2.createRow(a);
                celda=fila.createCell(1);
                celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda.setCellValue(rs.getInt("numeroComprobante"));
                celda2=fila.createCell(2);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda2.setCellValue(rs.getString("nombreCliente"));
                celda3=fila.createCell(3);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda3.setCellValue(rs.getDouble("monto"));
                celda4=fila.createCell(4);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda4.setCellValue(rs.getString("nombreUsuario"));
                //Double cant=rs.getDouble("cantidad");
                celda5=fila.createCell(5);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda5.setCellValue(Numeros.ConvertirFecha(rs.getDate("fecha")));
            /*
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            */
            }
        //texto+="\r\n";
            rs.close();
        String ruta="C://Informes//"+Inicio.fechaDia+" - informeDeCaja.xls";
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
    public static void GenerarPorFechas(String fechaDesde,String fechaHasta) throws SQLException{
               HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet();
        HSSFSheet hoja1=libro.createSheet();
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFCell celda7;
        HSSFCell celda8;
        HSSFCell celda9;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        String sql="select *,(select tipomovimientos.descripcion from tipomovimientos where tipomovimientos.id=movimientoscaja.tipoMovimiento) as tipo from movimientoscaja where fecha between '"+fechaDesde+"' and '"+fechaHasta+"'";
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
            celda.setCellValue("Numero Comprobante");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Tipo");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Efectivo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Debito");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Credito");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            
            }
            while(rs.next()){
            a++;
            col=rs.getInt("tipoMovimiento");
            fila=hoja.createRow(a);
            celda=fila.createCell(1);
            celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda.setCellValue(rs.getInt("numeroComprobante"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(rs.getString("tipo"));

            switch(col){
                case 1:
                    int pagad=rs.getInt("pagado");
                    switch(pagad){
                        case 1:
                            celda3=fila.createCell(3);
                            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            celda3.setCellValue(rs.getDouble("monto"));
                            break;
                        case 2:
                            celda4=fila.createCell(4);
                            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            celda4.setCellValue(rs.getDouble("monto"));
                            break;
                        default:
                            celda5=fila.createCell(5);
                            celda5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                            celda5.setCellValue(rs.getDouble("monto"));
                            break;
                    }
                    break;
                    
                    
                default:
                    celda3=fila.createCell(3);
                    celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    celda3.setCellValue(rs.getDouble("monto"));  
                    break;
            }
            
        }
 
        a=0;
        fila=hoja1.createRow(a);
           sql="select *,(select listcli.RAZON_SOCI from listcli where listcli.COD_CLIENT=movimientosarticulos.numeroCliente limit 0,1)as cliente,(select articulos.BARRAS from articulos where articulos.ID=movimientosarticulos.idArticulo limit 0,1)as articulos,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo limit 0,1)as descripcion from movimientosarticulos where fecha between '"+fechaDesde+"' and '"+fechaHasta+"'";
           rs=tra.leerConjuntoDeRegistros(sql);
            celda=fila.createCell(1);
            celda.setCellStyle(titulo);
            celda.setCellValue("Numero Comprobante");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Cliente");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Articulo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Descripcion");
            
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Cantidad");
            /*
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            */
            while(rs.next()){
                a++;
                fila=hoja1.createRow(a);
                celda=fila.createCell(1);
                celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda.setCellValue(rs.getInt("numeroComprobante"));
                celda2=fila.createCell(2);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda2.setCellValue(rs.getString("cliente"));
                celda3=fila.createCell(3);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda3.setCellValue(rs.getString("articulos"));
                celda4=fila.createCell(4);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda4.setCellValue(rs.getString("descripcion"));
                Double cant=rs.getDouble("cantidad");
                celda5=fila.createCell(5);
                celda.setCellType(HSSFCell.CELL_TYPE_STRING);
                celda5.setCellValue(cant);
            /*
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Gastos");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Observaciones");
            */
            }
        //texto+="\r\n";
            rs.close();
        String ruta="C://Informes//"+fechaDesde+"_"+fechaHasta+" - informeDeCaja.xls";
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

