/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import Administracion.Administracion;
import Depositos.Depositos;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Sucursales extends Administracion{
    private int numero;
    private String descripcion;
    private String direccion;
    private String telefono;
    private Depositos depositos;
    private Cajas caja;
    private Usuarios usuario;
    

    public Sucursales() {
    }

    public Sucursales(int numero) {
        this.numero = numero;
        Transaccionable tra=new Conecciones();
        String sql="select * from sucursal where numero="+numero;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                this.depositos=new Depositos(rs.getInt("deposito"));
                this.descripcion=rs.getString("descripcion");
                this.direccion=rs.getString("direccion");
                this.telefono=rs.getString("telefono");
                            
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sucursales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Depositos getDepositos() {
        return depositos;
    }

    public void setDepositos(Depositos depositos) {
        this.depositos = depositos;
    }
    
    
}
