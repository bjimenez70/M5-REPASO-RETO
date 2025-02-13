package co.bancolombia.sistemaprestamos.model;

import java.math.BigDecimal;

public class Datosprestamo {
    private long idprestamo;
    private BigDecimal monto;
    private BigDecimal interes;
    private Integer plazo;
    private String estado;
    private long idcliente;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;

    public Datosprestamo(long idprestamo, BigDecimal monto, BigDecimal interes, Integer plazo, String estado,long idcliente ,String nombre, String email, String telefono, String direccion) {
        this.idprestamo = idprestamo;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.estado = estado;
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public Datosprestamo(){

    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }


    public long getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(long idprestamo) {
        this.idprestamo = idprestamo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Datosprestamo{" +
                "idprestamo=" + idprestamo + '\n' +
                ", monto=" + monto + '\n' +
                ", interes=" + interes +'\n' +
                ", plazo=" + plazo + '\n' +
                ", estado='" + estado + '\n' +
                ", idcliente=" +idcliente+ '\n' +
                ", nombre='" + nombre + '\n' +
                ", email='" + email + '\n' +
                ", telefono='" + telefono + '\n' +
                ", direccion='" + direccion + '\n' +
                '}';
    }

}
