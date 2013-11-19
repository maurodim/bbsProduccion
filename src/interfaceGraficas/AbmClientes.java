/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraficas;

import Conversores.Numeros;
import facturacion.clientes.ClientesTango;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.util.ArrayList;
import java.util.Iterator;
import tablas.MiModeloTablaArticulos;

/**
 *
 * @author mauro
 */
public class AbmClientes extends javax.swing.JInternalFrame {
    private ArrayList listadoClientes=new ArrayList();
    /**
     * Creates new form AbmClientes
     */
    public AbmClientes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setTitle("MODIFICACION DE CLIENTES");

        MiModeloTablaArticulos miTabla=new MiModeloTablaArticulos();
        Busquedas bus=new ClientesTango();
        listadoClientes=bus.listar("");
        Iterator listC=listadoClientes.listIterator();
        miTabla.addColumn("COD CLIENTE");
        miTabla.addColumn("RAZON SOCIAL");
        miTabla.addColumn("DIRECCION");
        miTabla.addColumn("TELEFONO");
        miTabla.addColumn("CUPO DE CREDITO");
        miTabla.addColumn("SALDO");
        miTabla.addColumn("LISTA DE PRECIO");
        Object[] fila=new Object[7];
        ClientesTango cliente=new ClientesTango();
        while(listC.hasNext()){
            cliente=(ClientesTango)listC.next();
            fila[0]=cliente.getCodigoId();
            fila[1]=cliente.getRazonSocial();
            fila[2]=cliente.getDireccion();
            fila[3]=cliente.getTelefono();
            fila[4]=cliente.getCupoDeCredito();
            fila[5]=cliente.getSaldo();
            fila[6]=cliente.getListaDePrecios();
            miTabla.addRow(fila);
        }
        jTable1.setModel(miTabla);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int posicion=this.jTable1.getSelectedRow();
        ClientesTango cliente=new ClientesTango();
        cliente=(ClientesTango)listadoClientes.get(posicion);
        cliente.setRazonSocial(String.valueOf(this.jTable1.getValueAt(posicion,1)));
        cliente.setDireccion(String.valueOf(this.jTable1.getValueAt(posicion, 2)));
        cliente.setTelefono(String.valueOf(this.jTable1.getValueAt(posicion,3)));
        cliente.setCupoDeCredito(Numeros.ConvertirStringADouble(String.valueOf(this.jTable1.getValueAt(posicion,4))));
        if(cliente.getCupoDeCredito()>0){
            cliente.setCondicionDeVenta(2);
        }else{
            cliente.setCondicionDeVenta(1);
        }
        cliente.setListaDePrecios((Integer.parseInt(String.valueOf(this.jTable1.getValueAt(posicion,6)))));
        Facturar fact=new ClientesTango();
        fact.modificarDatosDelCliente(cliente);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
