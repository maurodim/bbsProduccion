/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import interfacesPrograma.Cajeables;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Comprobantes;
import objetos.Conecciones;
import objetos.Operaciones;

/**
 *
 * @author mauro
 */
public class Cajas extends Sucursales implements Cajeables{
    private int numero;
    private int tipoMovimiento;
    private Double saldoInicial;
    private static Date fechaInicio;
    private int numeroDeComprobante;
    private int tipoDeComprobante;
    private Double montoMovimiento;
    private Comprobantes comprobante;
    private Double cambioEnCaja;
    private Double saldoFinal;
    private Double totalVentas;
    private Double totalGastos;
    private Double transferenciaACaja;
    private Double diferencia;
    private Boolean reandida;
    private Boolean estado;
    private Integer tipo;
    private static ArrayList listBilletes=new ArrayList();
    private static ArrayList listadoOperaciones=new ArrayList();
    private static ArrayList listOperaciones=new ArrayList();
    private static ArrayList listadoCajas=new ArrayList();
    private String descripcionMovimiento;

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(Double totalGastos) {
        this.totalGastos = totalGastos;
    }

    public Double getTransferenciaACaja() {
        return transferenciaACaja;
    }

    public void setTransferenciaACaja(Double transferenciaACaja) {
        this.transferenciaACaja = transferenciaACaja;
    }

    public Double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Double diferencia) {
        this.diferencia = diferencia;
    }

    public Boolean getReandida() {
        return reandida;
    }

    public void setReandida(Boolean reandida) {
        this.reandida = reandida;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
   
    

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }
    

    public static ArrayList getListadoCajas() {
        return listadoCajas;
    }

    
    public ArrayList getListadoOperaciones() {
        return listadoOperaciones;
    }

    
    public ArrayList getListBilletes() {
        return listBilletes;
    }

    public void setListBilletes(ArrayList listBilletes) {
        this.listBilletes = listBilletes;
    }

    public Double getCambioEnCaja() {
        return cambioEnCaja;
    }

    public void setCambioEnCaja(Double cambioEnCaja) {
        this.cambioEnCaja = cambioEnCaja;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
    

    public Comprobantes getComprobante() {
        return comprobante;
    }

    public void setComprobante(Comprobantes comprobante) {
        this.comprobante = comprobante;
    }

    public Cajas(int tipoMovimiento, int numeroDeComprobante, int tipoDeComprobante, Double montoMovimiento) {

        this.tipoMovimiento = tipoMovimiento;
        this.numeroDeComprobante = numeroDeComprobante;
        this.tipoDeComprobante = tipoDeComprobante;
        this.montoMovimiento = montoMovimiento;
        this.tipo=0;
       if(listBilletes.size()==0){
            Billetes.cargarLista();
            listBilletes=Billetes.getListadoBill();
        }
        if(listadoOperaciones.size()==0){
        Operaciones.cargarArrayCaja();
        listadoOperaciones=Operaciones.getListadoOp();
        }
        if(listOperaciones.size()==0){
        Operaciones.cargarArray();
        listOperaciones=Operaciones.getListOp();
        }
        LeerCajaAdministradora();
    }

    public Cajas() {

        //Billetes.cargarLista();
        if(listBilletes.size()==0){
            Billetes.cargarLista();
            listBilletes=Billetes.getListadoBill();
        }
        if(listadoOperaciones.size()==0){
        Operaciones.cargarArrayCaja();
        listadoOperaciones=Operaciones.getListadoOp();
        }
        if(listOperaciones.size()==0){
        Operaciones.cargarArray();
        listOperaciones=Operaciones.getListOp();
        }
        this.cambioEnCaja=0.00;
        this.numeroDeComprobante=0;
        this.tipoDeComprobante=0;
        this.tipoMovimiento=0;
        this.tipo=0;
        LeerCajaAdministradora();
    }

    public Cajas(int numero) {

        this.numero = numero;
       if(listBilletes.size()==0){
            Billetes.cargarLista();
            listBilletes=Billetes.getListadoBill();
        }
        if(listadoOperaciones.size()==0){
        Operaciones.cargarArrayCaja();
        listadoOperaciones=Operaciones.getListadoOp();
        }
        if(listOperaciones.size()==0){
        Operaciones.cargarArray();
        listOperaciones=Operaciones.getListOp();
        }
        this.tipo=0;
        LeerCajaAdministradora();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public static Date getFechaInicio() {
        return fechaInicio;
    }

    public static void setFechaInicio(Date fechaInicio) {
        Cajas.fechaInicio = fechaInicio;
    }

    public int getNumeroDeComprobante() {
        return numeroDeComprobante;
    }

    public void setNumeroDeComprobante(int numeroDeComprobante) {
        this.numeroDeComprobante = numeroDeComprobante;
    }

    public int getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    public void setTipoDeComprobante(int tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    public Double getMontoMovimiento() {
        return montoMovimiento;
    }

    public void setMontoMovimiento(Double montoMovimiento) {
        this.montoMovimiento = montoMovimiento;
    }
    public void LeerCajaAdministradora(){
        String sql="select caja.numero from caja where tipo=1 and estado=0";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            Inicio.numeroCajaAdministradora=rs.getInt("numero");
                
            }
            System.out.println("CAJA ADMINISTRADORAAAAAAAAAA "+Inicio.numeroCajaAdministradora);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object AbrirCaja(Object caja) {
        if(listadoCajas.size() > 0)listadoCajas.clear();
        //listadoCajas.clear();
        Cajas cajaNueva=(Cajas) caja;
        Integer tipo=0;
        if(Inicio.usuario.getNivelDeAutorizacion()==1){
            tipo=1;
        }
        String sql="insert into caja (numeroSucursal,numeroUsuario,tipoMovimiento,saldoInicial,tipo) values ("+Inicio.sucursal.getNumero()+","+Inicio.usuario.getNumero()+",9,"+cajaNueva.saldoInicial+",tipo="+tipo+")";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                cajaNueva.numero=rs.getInt(1);
                System.out.println("ID CAJA "+cajaNueva.numero);
            }
            rs.close();
            sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,monto,tipoMovimiento,idCaja) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+cajaNueva.getSaldoInicial()+",9,"+cajaNueva.getNumero()+")";
            tra.guardarRegistro(sql);
            int pos=cajaNueva.getTipoMovimiento();
            Operaciones operacion=(Operaciones)listOperaciones.get(pos);
            String desc=operacion.getDescripcion();
            cajaNueva.setDescripcionMovimiento(desc);
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        listadoCajas.add(cajaNueva);
        return cajaNueva;
    }

    @Override
    public Boolean CerrarCaja(Object caja) {
        Cajas cajj=(Cajas)caja;
        Boolean verif=false;
        //String sql="insert into"cajj.getSaldoFinal()
        String sql="update caja set saldoFinal="+cajj.getSaldoFinal()+",totalVentas="+cajj.getTotalVentas()+",totalGastos="+cajj.getTotalGastos()+", transfACaja="+cajj.getTransferenciaACaja()+",diferencia="+cajj.getDiferencia()+",estado=1,fechaCierre='"+Inicio.fechaVal+"' where numero="+cajj.getNumero();
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        //tipoMovimiento 10
        sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+cajj.getNumeroDeComprobante()+","+cajj.getTipoDeComprobante()+","+cajj.getMontoMovimiento()+",10,"+Inicio.numeroCajaAdministradora+",0,0,1)";
        verif=tra.guardarRegistro(sql);
        return verif;
    }

    @Override
    public Double SaldoDeCaja(ArrayList listadoBilletes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean NuevoMovimiento(Object factura) {
        Cajas caj=(Cajas)factura;
        Boolean ch=false;
        listadoCajas.add(caj);
        System.err.println(Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+caj.getNumeroDeComprobante()+","+caj.getTipoDeComprobante()+","+caj.getMontoMovimiento()+","+caj.getTipoMovimiento()+","+caj.getNumero()+",0,");
        String sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+caj.getNumeroDeComprobante()+","+caj.getTipoDeComprobante()+","+caj.getMontoMovimiento()+","+caj.getTipoMovimiento()+","+caj.getNumero()+",0,0,1)";
        Transaccionable tra=new Conecciones();
        ch=tra.guardarRegistro(sql);
        
        return ch;
    }

    @Override
    public Object ModificarMovimiento(Integer idMovimiento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean EliminarMovimiento(Integer idMovimiento) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object ArquearCaja(Object caja) {
        if(listadoCajas.size() > 0)listadoCajas.clear();
        Cajas cajas=(Cajas)caja;
        Cajas cajass=null;
        Double saldoFinal=cajas.saldoInicial;
        String sql="select * from movimientoscaja where idCaja="+cajas.numero;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                cajass=new Cajas();
                cajass.setNumero(rs.getInt("numero"));
                cajass.setNumeroDeComprobante(rs.getInt("numeroComprobante"));
                cajass.setTipoMovimiento(rs.getInt("tipoMovimiento"));
                cajass.setMontoMovimiento(rs.getDouble("monto"));
                saldoFinal= saldoFinal + rs.getDouble("monto");
                int pos=cajass.getTipoMovimiento() -1;
                Operaciones operacion=(Operaciones)listOperaciones.get(pos);
                 String desc=operacion.getDescripcion();
                cajass.setDescripcionMovimiento(desc);
                listadoCajas.add(cajass);
                
            }
            rs.close();
            cajas.saldoFinal=saldoFinal;
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cajas;
    }

    @Override
    public Boolean VerificarCaja(int numeroDeUsuario, int numeroDeSucursal, String fecha) {
        Boolean verifi=false;
        String sql="select * from caja where numeroUsuario ="+numeroDeUsuario+" and numeroSucursal="+numeroDeSucursal+" and estado=0";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            verifi=true;
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return verifi;
    }

    @Override
    public Object CargarCaja(int numeroDeUsuario, int numeroDeSucursal, String fecha) {
        Cajas cajas=new Cajas();
        String sql="select * from caja where numeroUsuario ="+numeroDeUsuario+" and numeroSucursal="+numeroDeSucursal+" and fecha like '"+fecha+"%'";
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                cajas.numero=rs.getInt("numero");
                cajas.saldoInicial=rs.getDouble("saldoInicial");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cajas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cajas;
    }

    @Override
    public Object NuevoGasto(Object factura) {
       Cajas caj=(Cajas)factura;
        //Boolean ch=false;
        listadoCajas.add(caj);
        Double monto=caj.getMontoMovimiento() * (-1);
        String sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado,observaciones) values ("+Inicio.usuario.getNumeroId()+","+Inicio.sucursal.getNumero()+","+caj.getNumeroDeComprobante()+","+caj.getTipoDeComprobante()+","+monto+","+caj.getTipoMovimiento()+","+caj.getNumero()+",0,2,0,'"+caj.getDescripcionMovimiento()+"')";
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
        
        return caj;
    }
    
    
}
