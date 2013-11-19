/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import Administracion.TipoAcceso;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;
import objetos.Menus;

/**
 *
 * @author mauro
 */
public class Usuarios extends TipoAcceso{
    private int numeroId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
    private String localidad;
    private String nombreDeUsuario;
    private String clave;
    private Integer nivelDeAutorizacion;
    private Sucursales sucursal;
    private Menus menu;

    public Integer getNivelDeAutorizacion() {
        return nivelDeAutorizacion;
    }

    public void setNivelDeAutorizacion(Integer nivelDeAutorizacion) {
        this.nivelDeAutorizacion = nivelDeAutorizacion;
    }
    
    
    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    
    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }
    

    public Usuarios(int numero) {
        this.numeroId = numero;
    }

    public Usuarios(String nombreDeUsuario, String clave) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.clave = clave;
        super.setNivel(1);
    }

    public Usuarios() {
       
    }

    public int getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(int numero) {
        this.numeroId = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    
    public ArrayList listarUsuario(){
        ArrayList listadoUsuarios=new ArrayList();
        try {
            
            String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5 from usuarios";
            String sql1="";
            Usuarios us=null;
            Transaccionable traUs=new Conecciones();
            ResultSet rr=traUs.leerConjuntoDeRegistros(sql);
            
            while(rr.next()){
                us=new Usuarios();
                us.nombre=rr.getString("nombre");
                us.direccion=rr.getString("direccion");
                us.localidad=rr.getString("localidad");
                us.mail=rr.getString("mail");
                us.numeroId=rr.getInt("numero");
                us.telefono=rr.getString("telefono");
                us.nombreDeUsuario=rr.getString("nombreUsuario");
                us.clave=rr.getString("clave");
                us.setNivel(rr.getInt("autorizacion"));
                us.setNivelDeAutorizacion(rr.getInt("autorizacion"));
                System.err.println("USUARIOS "+us.nombre);
                us.setMenu(new Menus(rr.getBoolean("menu1"),rr.getBoolean("menu2"),rr.getBoolean("menu3"),rr.getBoolean("menu4"),rr.getBoolean("menu5")));
                listadoUsuarios.add(us);
                
            }
            
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoUsuarios;
    }

    @Override
    public Boolean registrarIngreso(Object objeto) {
        return super.registrarIngreso(objeto);
    }

    @Override
    public Boolean registrarSalida(Object objeto) {
        return super.registrarSalida(objeto);
    }

    @Override
    public Object validarClave(String usuario, String clave) {
        Transaccionable tra=new Conecciones();
        Usuarios usu=null;
        String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5 from usuarios where nombreUsuario like '"+usuario+"' and clave like '"+clave+"'";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       
        
        try {
            while(rs.next()){
            usu=new Usuarios();
            usu.setNivelDeAutorizacion(rs.getInt("autorizacion"));
            usu.setNombre(rs.getString("nombre"));
            usu.setNumero(rs.getInt("numero"));
            usu.setNumeroId(rs.getInt("numero"));
            usu.setSucursal(new Sucursales(rs.getInt("sucursal")));
            
                    usu.setMenu(new Menus(rs.getBoolean("menu1"),rs.getBoolean("menu2"),rs.getBoolean("menu3"),rs.getBoolean("menu4"),rs.getBoolean("menu5")));                    
               
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }

    @Override
    public Boolean modificarDatosUsuario(Object objeto) {
        return super.modificarDatosUsuario(objeto);
    }

    @Override
    public Boolean nuevoUsuario(Object objeto) {
        return super.nuevoUsuario(objeto);
    }

    @Override
    public ArrayList listarUsuarios() {
        return super.listarUsuarios();
    }

    @Override
    public Object cargarUsuario(Integer numeroUsuario) {
        Transaccionable tra=new Conecciones();
        Usuarios usu=null;
        String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5 from usuarios where numero="+numeroUsuario;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       
        
        try {
            while(rs.next()){
            usu=new Usuarios();
            usu.setNivelDeAutorizacion(rs.getInt("autorizacion"));
            usu.setNombre(rs.getString("nombre"));
            usu.setNumero(rs.getInt("numero"));
            usu.setDireccion(rs.getString("direccion"));
            usu.setClave(rs.getString("clave"));
            usu.setTelefono(rs.getString("telefono"));
            usu.setMail(rs.getString("mail"));
            usu.setSucursal(new Sucursales(rs.getInt("sucursal")));
           
            
                    usu.setMenu(new Menus(rs.getBoolean("menu1"),rs.getBoolean("menu2"),rs.getBoolean("menu3"),rs.getBoolean("menu4"),rs.getBoolean("menu5")));                    
               
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }
    
}
