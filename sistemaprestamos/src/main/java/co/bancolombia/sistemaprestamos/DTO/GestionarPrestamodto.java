package co.bancolombia.sistemaprestamos.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GestionarPrestamodto {

    @NotNull(message = "El id no puede ser nulo")
    @Positive(message = "Id debe ser positivo")
    @Digits(integer = 20, fraction = 0, message = "Id no es correcto")
    private Long id;
    public GestionarPrestamodto(Long id){
        this.id= id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


