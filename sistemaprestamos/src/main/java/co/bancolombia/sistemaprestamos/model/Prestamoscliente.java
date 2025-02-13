package co.bancolombia.sistemaprestamos.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class Prestamoscliente {
    private long idprestamo;
    private BigDecimal monto;
    private BigDecimal interes;
    private Integer plazo;
    private String estado;
    private String novedad;
    private Date fecha;
    private Time hora;

    public Prestamoscliente(long idprestamo, BigDecimal monto, BigDecimal interes, Integer plazo, String estado, String novedad, Date fecha, Time hora) {
        this.idprestamo = idprestamo;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.estado = estado;
        this.novedad = novedad;
        this.fecha = fecha;
        this.hora = hora;
    }
    public Prestamoscliente(){

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

    public String getNovedad() {
        return novedad;
    }

    public void setNovedad(String novedad) {
        this.novedad = novedad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}


