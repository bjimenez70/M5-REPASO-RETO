package co.bancolombia.sistemaprestamos.repository;

import co.bancolombia.sistemaprestamos.model.Cliente;


import co.bancolombia.sistemaprestamos.model.Cliente;
import co.bancolombia.sistemaprestamos.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface Transaccionrepository extends JpaRepository<Transaccion, Long> {

    }
