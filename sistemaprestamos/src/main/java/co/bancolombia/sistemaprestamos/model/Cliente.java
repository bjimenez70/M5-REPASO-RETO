package co.bancolombia.sistemaprestamos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id_cliente")
    private Long idCliente;

    private String nombre;
    private String email;
    private String telefono;
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamos;


    public Cliente (Long id, String nombre, String email, String telefono, String direccion) {
        this.idCliente = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Cliente() {
    }

    public Long getId() {
        return idCliente;
    }

    public void setId(Long id) {
        this.idCliente = id;
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

    public List <Prestamo> getPrestamos() {
        return prestamos;
    }
}
