/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.clientes;


import interfaces.Transaccionable;
import interfacesPrograma.Busquedas;
import interfacesPrograma.Facturar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;


/**
 *
 * @author Administrador
 */
public class ClientesTango implements Busquedas,Facturar{
        private String codigoCliente;
        private String razonSocial;
        private Double saldo;
        private Integer condicionDePago;
        private Date fechaActualizacion;
        private String direccion;
        private String localidad;
        private String telefono;
        private String observaciones;
        private Boolean contactado;
        private Date fechaPedido;
        private String numeroPedido;
        private Date fechaListado;
        private Integer numeroListado;
        private Date fechaHdr;
        private Integer numeroHdr;
        private String numeroDeCuit;
        private String empresa;
        private Integer condicionDeVenta;
        private Integer listaDePrecios;
        private Double descuento;
        private String condicionIva;
        private Double coeficienteListaDeprecios;
        private Integer codigoId;
        private Double cupoDeCredito;
        private Double saldoActual;
        private static Hashtable listadoClientes=new Hashtable();
        private static Hashtable listadoPorNom=new Hashtable();
        
        
        

    public Double getCupoDeCredito() {
        return cupoDeCredito;
    }

    public void setCupoDeCredito(Double cupoDeCredito) {
        this.cupoDeCredito = cupoDeCredito;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }
        

    public Integer getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(Integer codigoId) {
        this.codigoId = codigoId;
    }
    public static void cargarMap(){
              
            
            Transaccionable tra=new Conecciones();
            String sql="select *,(select coeficienteslistas.coeficiente from coeficienteslistas where coeficienteslistas.id=listcli.NRO_LISTA)as coeficiente,(select sum(movimientosclientes.monto) from movimientosclientes where pagado=0 and movimientosclientes.numeroProveedor=listcli.codMMd)as saldo from listcli";
            System.out.println("CLIENTES "+sql);
            //String sql="select pedidos_carga1.COD_CLIENT,pedidos_carga1.RAZON_SOC,pedidos_carga1.NRO_PEDIDO,pedidos_carga1.numero,pedidos_carga1.LEYENDA_2 from pedidos_carga1 where RAZON_SOC like '"+cliente+"%' group by COD_CLIENT order by RAZON_SOC";
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            try{
                listadoClientes.clear();
                listadoPorNom.clear();
                String codigo="";
                String nombre="";
            while(rs.next()){               
                ClientesTango cli=new ClientesTango();
                cli.setCodigoId(rs.getInt("codMMd"));
                cli.setCodigoCliente(rs.getString("COD_CLIENT"));
                cli.setRazonSocial(rs.getString("RAZON_SOCI"));
                cli.setDireccion(rs.getString("DOMICILIO"));
                cli.setCondicionDeVenta(rs.getInt("COND_VTA"));
                cli.setListaDePrecios(rs.getInt("NRO_LISTA"));
                Double descuento=Double.parseDouble(rs.getString("PORC_DESC"));
                
                cli.setDescuento(descuento);
                cli.setNumeroDeCuit(rs.getString("IDENTIFTRI"));
                cli.setEmpresa(rs.getString("empresa"));
                cli.setCondicionIva(rs.getString("TIPO_IVA"));
                cli.setTelefono(rs.getString("TELEFONO_1"));
                cli.setLocalidad(rs.getString("LOCALIDAD"));
                cli.setCoeficienteListaDeprecios(rs.getDouble("coeficiente"));
                cli.setCupoDeCredito(rs.getDouble("CUPO_CREDI"));
                cli.setSaldo(rs.getDouble("saldo"));
                cli.setSaldoActual(rs.getDouble("saldo"));
                //cli.setNumeroPedido(rs.getString(3));
                //cli.setObservaciones(rs.getString(5));
                System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                codigo=cli.getCodigoCliente();
                nombre=cli.getRazonSocial();
                listadoClientes.put(codigo,cli);
                listadoPorNom.put(nombre,cli);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesTango.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }   

    public ClientesTango(String codigoCliente) {
        ClientesTango clientesTango=(ClientesTango)listadoClientes.get(codigoCliente);
                    
                    this.codigoId=clientesTango.getCodigoId();
                    this.codigoCliente=clientesTango.getCodigoCliente();
                    this.razonSocial=clientesTango.getRazonSocial();
                    this.direccion=clientesTango.getDireccion();
                    this.condicionDeVenta=clientesTango.getCondicionDeVenta();
                    this.listaDePrecios=clientesTango.getListaDePrecios();
                    Double descuent=clientesTango.getDescuento();                
                    this.descuento=descuent;
                    this.numeroDeCuit=clientesTango.getNumeroDeCuit();
                    this.empresa=clientesTango.getEmpresa();
                    this.condicionIva=clientesTango.getCondicionIva();
                    this.telefono=clientesTango.getTelefono();
                    this.localidad=clientesTango.getLocalidad();
                    this.coeficienteListaDeprecios=clientesTango.getCoeficienteListaDeprecios();
                    this.cupoDeCredito=clientesTango.getCupoDeCredito();
                    this.saldoActual=clientesTango.getSaldoActual();
                    this.saldo=clientesTango.getSaldo();
                    //cli.setNumeroPedido(rs.getString(3));
                    //cli.setObservaciones(rs.getString(5));
                    //System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                    //ped.add(cli);
            
    }

    public ClientesTango() {
    }
    

    public Double getCoeficienteListaDeprecios() {
        return coeficienteListaDeprecios;
    }

    public void setCoeficienteListaDeprecios(Double coeficienteListaDeprecios) {
        this.coeficienteListaDeprecios = coeficienteListaDeprecios;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
        

    public Integer getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(Integer condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }

    public Integer getListaDePrecios() {
        return listaDePrecios;
    }

    public void setListaDePrecios(Integer listaDePrecios) {
        
            this.listaDePrecios = listaDePrecios;
        
    }
        
        

    public String getNumeroDeCuit() {
        return numeroDeCuit;
    }

    public void setNumeroDeCuit(String numeroDeCuit) {
        this.numeroDeCuit = numeroDeCuit;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Boolean getContactado() {
        return contactado;
    }

    public void setContactado(Boolean contactado) {
        this.contactado = contactado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaHdr() {
        return fechaHdr;
    }

    public void setFechaHdr(Date fechaHdr) {
        this.fechaHdr = fechaHdr;
    }

    public Date getFechaListado() {
        return fechaListado;
    }

    public void setFechaListado(Date fechaListado) {
        this.fechaListado = fechaListado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Integer getNumeroHdr() {
        return numeroHdr;
    }

    public void setNumeroHdr(Integer numeroHdr) {
        this.numeroHdr = numeroHdr;
    }

    public Integer getNumeroListado() {
        return numeroListado;
    }

    public void setNumeroListado(Integer numeroListado) {
        this.numeroListado = numeroListado;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
        

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
        


    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCondicionDePago() {
        return condicionDePago;
    }

    public void setCondicionDePago(Integer condicionDePago) {
        this.condicionDePago = condicionDePago;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public void agregarNuevo(ClientesTango cli) throws SQLException{
        Transaccionable tra=new Conecciones();
        String sql="insert into listcli (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,1,'"+cli.getEmpresa()+"')";
        if(tra.guardarRegistro(sql)){
            
        }else{
            
        }
        
    }
    public ArrayList listarPorVehiculo(int numeroVehiculo,String fecha) throws SQLException{
        ArrayList lista=new ArrayList();
        //String sql="select * from clientes where RAZON_SOCI like";
        String sql="select pedidos_carga1.COD_CLIENT,pedidos_carga1.RAZON_SOC,pedidos_carga1.listado,pedidos_carga1.LEYENDA_2,pedidos_carga1.NRO_PEDIDO,(select clientes.DOMICILIO from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as domicilio,(select clientes.TELEFONO_1 from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as telefono,(select clientes.LOCALIDAD from clientes where clientes.COD_CLIENT like pedidos_carga1.COD_CLIENT and clientes.RAZON_SOCI like pedidos_carga1.RAZON_SOC group by RAZON_SOC)as localidad,if((select contactos.contactado from contactos where contactos.numerocliente like pedidos_carga1.COD_CLIENT and contactos.numeroListado=pedidos_carga1.listado)=1,true,false)as contactado from pedidos_carga1 where vehiculo="+numeroVehiculo+" and entrega like '"+fecha+"%' group by RAZON_SOC";
                // and entregaConv like '"+fecha+"'";
        
        return lista;
    }

    @Override
    public ArrayList listar(String cliente) {
        ArrayList ped=new ArrayList();
            ClientesTango rs=null;
            Transaccionable tra=new Conecciones();
            cliente=cliente.toUpperCase();
            Enumeration<ClientesTango> elementos=listadoPorNom.elements();
            while(elementos.hasMoreElements()){
                rs=(ClientesTango)elementos.nextElement();
                ClientesTango cli=new ClientesTango();
                 int pos=rs.getRazonSocial().indexOf(cliente);
                if(pos==-1){
                    
                }else{
                cli=rs;
                //cli.setNumeroPedido(rs.getString(3));
                //cli.setObservaciones(rs.getString(5));
                System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                ped.add(cli);
                }
            }
            return ped;
    }  
    

    @Override
    public void marcarContactado(Integer item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarDatosCliente(Object cliente) {
        ClientesTango cli=(ClientesTango)cliente;
        Boolean resultado=false;
        Transaccionable tra=new Conecciones();
        String sql="insert into listcli (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,2,'"+cli.getEmpresa()+"')";
        resultado=tra.guardarRegistro(sql);
        //return resultado;
    }

    @Override
    public ArrayList buscar(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList filtrar(String numeroCliente, String nombreCliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean guardar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevo(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leer(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imprimirComprobante(int tipoComprobante, Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listadoBusqueda(String criterio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean guardarNuevoCliente(Object cliente) {
        ClientesTango cli=(ClientesTango)cliente;
        Boolean resultado=false;
        Transaccionable tra=new Conecciones();
        String sql="insert into listcli (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,1,'"+cli.getEmpresa()+"')";
        resultado=tra.guardarRegistro(sql);
        return resultado;
    }

    @Override
    public Boolean modificarDatosDelCliente(Object cliente) {
        ClientesTango cli=(ClientesTango)cliente;
        Boolean resultado=false;
        Transaccionable tra=new Conecciones();
        
        //String sql="insert into listcli (COD_CLIENT,RAZON_SOCI,DOMICILIO,LOCALIDAD,TELEFONO_1,TIPO_IVA,IDENTIFTRI,COND_VTA,NRO_LISTA,empresa) values ('"+cli.getCodigoCliente()+"','"+cli.getRazonSocial()+"','"+cli.getDireccion()+"','SANTA FE','"+cli.getTelefono()+"',"+cli.getCondicionIva()+",'"+cli.getNumeroDeCuit()+"',1,1,'"+cli.getEmpresa()+"')";
        String sql="update listcli set RAZON_SOCI='"+cli.getRazonSocial()+"',DOMICILIO='"+cli.getDireccion()+"',TELEFONO_1='"+cli.getTelefono()+"',COND_VTA="+cli.getCondicionDeVenta()+",NRO_LISTA="+cli.getListaDePrecios()+",CUPO_CREDI="+cli.getCupoDeCredito()+" where codMMd ="+cli.getCodigoId();
        resultado=tra.guardarRegistro(sql);
        return resultado;
    }

    @Override
    public ArrayList listarClientes(String nombre) {
             ArrayList ped=new ArrayList();
            ClientesTango rs=null;
            Transaccionable tra=new Conecciones();
            nombre=nombre.toUpperCase();
            Enumeration<ClientesTango> elementos=listadoPorNom.elements();
            while(elementos.hasMoreElements()){
                rs=(ClientesTango)elementos.nextElement();
                ClientesTango cli=new ClientesTango();
                 int pos=rs.getRazonSocial().indexOf(nombre);
                if(pos==-1){
                    
                }else{
                cli=rs;
                //cli.setNumeroPedido(rs.getString(3));
                //cli.setObservaciones(rs.getString(5));
                System.out.println("CLIENTE "+cli.getRazonSocial() +"COMENTARIO "+cli.getCodigoCliente());
                ped.add(cli);
                }
            }
            return ped;

    }

    @Override
    public Object cargarPorCodigoDeBarra(String codigoDeBarra) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoAsignado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
}
