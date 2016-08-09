/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 * AQUI SE VAN A GESTIONAR TODAS LAS CONECCIONES DEL SISTEMA, LOS DATOS COMO OBJETOS
 * Y LAS INTERFACES VAN A GUARDAR, ELIMINAR O ACTUALIZAR LAS TABLAS, SE VA A PASAR EL STRING POR LA INTERFAZ 
 * PARA QUE AQUI SE REALICE LA TRANSACCION
 * 
 */
public class Conecciones implements Transaccionable{
    private Connection con;
    private Statement st;

    public Conecciones() {
                MysqlDataSource dataSource=new MysqlDataSource();
		try{
			//Class.forName(driver1).newInstance();
                    dataSource.setUser("maurodim_prod");//maurodim_prod
                    dataSource.setDatabaseName("maurodim_produccion");//maurodim_produccion
                    dataSource.setPassword("SUtter001");//SUtter001
                    dataSource.setServerName("167.250.5.18");//201.235.253.65
                    con=dataSource.getConnection();
                    st=con.createStatement();
                 }catch(Exception ex){
                    
                String cod1=String.valueOf(ex);
                        
			System.out.println("NO SE PUDO CONECTAR A LA BASE "+ex);
		}

        
    }

    @Override
    public Boolean guardarRegistro(String sql) {
        Boolean coneccion=true;
        try {
            System.out.println("SENTENCIA "+sql);
            st.executeUpdate(sql);
            //this.st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            coneccion=false;
            FileWriter fichero=null;
            PrintWriter pw=null;
            try {
                fichero = new FileWriter("C:\\Gestion\\"+Inicio.fechaDia+" - erroresDeConeccion.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(sql+"\n");
            } catch (IOException ex1) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                         try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
            }
            
        }
        return coneccion;
    }

    @Override
    public Boolean guardarRegistros(ArrayList listadoDeSentencias) {
        String sql="";
        Boolean coneccionCorrecta=true;
        Iterator il=listadoDeSentencias.listIterator();
        while(il.hasNext()){
            sql=(String)il.next();
            try {
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
                coneccionCorrecta=false;
            }
        }
        
        return coneccionCorrecta;
    }

    @Override
    public ResultSet leerConjuntoDeRegistros(String sql) {
        ResultSet rs=null;
        try {
            st.execute(sql);
            rs=st.getResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO ENTRO LA CONECCION");
        }
        return rs;
    }

   

    }
    

