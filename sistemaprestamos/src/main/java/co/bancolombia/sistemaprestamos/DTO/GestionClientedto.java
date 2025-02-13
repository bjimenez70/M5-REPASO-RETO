package co.bancolombia.sistemaprestamos.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GestionClientedto {

    @NotNull(message = "El id cliente no puede ser nulo")
    @Positive(message = "Id cliente debe ser positivo")
    @Digits(integer = 15, fraction = 0, message = "Id cliente no es correcto")
    private Long id;
    public GestionClientedto(Long id){
        this.id= id;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
