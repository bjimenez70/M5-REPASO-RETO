package co.bancolombia.sistemaprestamos.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public class CreaPrestamodto {
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "Valor debes ser positivo")
    @Digits(integer = 13, fraction = 2, message = "Numero de digitos no es correcto")
    private BigDecimal monto;
    @NotNull(message = "El plazo no puede ser nulo")
    @Positive(message = "plazo debes ser positivo")
    @Range(min=1, max=72, message = "Plazo no permitido")
    private Integer plazo;
    @NotNull(message = "La identificacion no puede ser nulo")
    @Positive(message = "identificacion debe ser positivo")
    @Digits(integer = 15, fraction = 0, message = "identificacion no es correcto")
    private long idcliente;
    public CreaPrestamodto(BigDecimal monto, Integer plazo, long idcliente){
        this.monto = monto;
        this.plazo =plazo;
        this.idcliente = idcliente;

    }
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

}
