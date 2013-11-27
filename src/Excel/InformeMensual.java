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
public class InformeMensual {
  /*
   * generar aca la vista para acceder
   * select *,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=1)as ventas,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=12)as gastosCaja,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=11)as pagoAProveedores from movimientoscaja group by DAY(fecha)
   * 
   * informeMnesual2:
   * SELECT *,sum(monto)as tot FROM movimientoscaja group by DATE(fecha),tipoMovimiento
   * 
   * informemensualventa:
   * 
   * SELECT sum(tot)as totalVentas,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=1 group by DAY(fecha)
   * 
   * informemensualgastoscaja
   * 
   * select sum(tot)as totalGastos,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=12 group by DAY(fecha)
   * 
   * informemensualpagoproveedores
   * 
   * select sum(tot)as totalProv,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=11 group by DAY(fecha)
   * 
   * informemensualcobranzaclientes
   * 
   * select sum(tot)as totalCob,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=13 group by DAY(fecha)
   */
   public void GenerarInformeMensual(String desde,String hasta) throws SQLException{
       HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet();
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        String sql="select * from informemensualgeneral where fecha between '"+desde+"' and '"+hasta+"'";
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
            celda.setCellValue("Ventas");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Pago a Proveedores");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Gasto de caja");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Cobranza clientes");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            
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
            celda.setCellValue(rs.getDouble("ventas"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(rs.getDouble("gastos"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getDouble("pago"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getDouble("cobro"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(" "+rs.getDate("fecha"));
            //celda5.setCellValue(rs.getDate("fecha"));
            
        }
 
        rs.close();
        //texto+="\r\n";
        String ruta="C://Informes//informemensual.xls";
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
