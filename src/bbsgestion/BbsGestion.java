/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbsgestion;

import Compras.Remitos;
import Sucursales.Usuarios;
import interfaceGraficas.Inicio;
import interfaceGraficas.LoguinBbsGestion;
import interfaces.Comprobable;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class BbsGestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        ArrayList usuariosList=new ArrayList();
        Usuarios usuarios=new Usuarios();
        usuariosList=usuarios.listarUsuario();
        */
        File folder=new File("C:\\Gestion");
        File archivo=null;
        FileReader fr=null;
        BufferedReader br=null;
        
        if(!folder.isDirectory()){
            System.out.println("EXISTE EL DIRECTORIO");
            folder.mkdirs();
        }else{
            System.out.println("NOOOOOOOOOOOOOOO EXISTE EL DIRECTORIO");
            
        }
        try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("C:\\Gestion\\erroresDeConeccion.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
          Transaccionable tra=new Conecciones();
         while((linea=br.readLine())!=null)
            System.out.println(linea);
           
            if(tra.guardarRegistro(linea));
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        LoguinBbsGestion lBb=new LoguinBbsGestion();
        lBb.setVisible(true);
        lBb.pack();
    }
}
