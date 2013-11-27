/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class ListasDePrecios {
    private static ArrayList listadoDeListas=new ArrayList();
    private Integer id;
    private Double coeficiente;
    private String desccripcion;

    public ListasDePrecios() {
    }

    public ListasDePrecios(Integer id) {
        this.id = id;
        ListasDePrecios listaD=(ListasDePrecios)listadoDeListas.get(id);
        this.coeficiente=listaD.getCoeficiente();
        this.desccripcion=listaD.getDesccripcion();
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public String getDesccripcion() {
        return desccripcion;
    }

    public void setDesccripcion(String desccripcion) {
        this.desccripcion = desccripcion;
    }
    public static void cargarMap(){
         Transaccionable tra=new Conecciones();
        String sql="select * from coeficienteslistas order by id";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            listadoDeListas.clear();
            Integer numero=0;
            while(rs.next()){
                ListasDePrecios lista=new ListasDePrecios();
                lista.setId(rs.getInt("id"));
                lista.setDesccripcion(rs.getString("descripcion"));
                lista.setCoeficiente(rs.getDouble("coeficiente"));
                numero=lista.getId();
                listadoDeListas.add(lista);
                
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ListasDePrecios.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    public static ArrayList Listado(){
        return listadoDeListas;
    }
    
}
