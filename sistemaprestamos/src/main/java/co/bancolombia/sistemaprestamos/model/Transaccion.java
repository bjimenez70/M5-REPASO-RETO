package co.bancolombia.sistemaprestamos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long idTransaccion;

    @ManyToOne
    @JoinColumn(name = "prestamo_asociado", nullable = false)
    private Prestamo prestamoAsociado;

    @Column(name = "tipo_novedad")
    private String tipoNovedad;

    private BigDecimal valor;
    private LocalDate fecha;
    private LocalTime hora;

    public Transaccion(Prestamo prestamoAsociado, String tipoNovedad, BigDecimal valor, LocalDate fecha, LocalTime hora) {
        this.prestamoAsociado = prestamoAsociado;
        this.tipoNovedad = tipoNovedad;
        this.valor = valor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Transaccion() {
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Prestamo getPrestamoAsociado() {
        return prestamoAsociado;
    }

    public void setPrestamoAsociado(Prestamo prestamoAsociado) {
        this.prestamoAsociado = prestamoAsociado;
    }

    public String getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(String tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
