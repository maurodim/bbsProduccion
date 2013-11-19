/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Operaciones {
    private Integer id;
    private String descripcion;
    private Integer destino;
    private Integer valor;
    private static ArrayList listadoOp=new ArrayList();
    private static ArrayList listOp=new ArrayList();

    public static ArrayList getListOp() {
        return listOp;
    }

    public static ArrayList getListadoOp() {
        return listadoOp;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
    
    public static void cargarArrayCaja(){
        Transaccionable tra=new Conecciones();
        String sql="select * from tipomovimientos where destinoOperacion=2";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            listadoOp.clear();
            while(rs.next()){
                Operaciones operaciones=new Operaciones();
                operaciones.setId(rs.getInt("id"));
                operaciones.setDescripcion(rs.getString("descripcion"));
                operaciones.setDestino(rs.getInt("destinoOperacion"));
                operaciones.setValor(rs.getInt("multiploOp"));
                listadoOp.add(operaciones);
                
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public static void cargarArray(){
        Transaccionable tra=new Conecciones();
        String sql="select * from tipomovimientos";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            listOp.clear();
            while(rs.next()){
                Operaciones operaciones=new Operaciones();
                operaciones.setId(rs.getInt("id"));
                operaciones.setDescripcion(rs.getString("descripcion"));
                operaciones.setDestino(rs.getInt("destinoOperacion"));
                operaciones.setValor(rs.getInt("multiploOp"));
                System.err.println(" LISTADO OPERACIONES "+operaciones.getDescripcion());
                listOp.add(operaciones);
                
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
