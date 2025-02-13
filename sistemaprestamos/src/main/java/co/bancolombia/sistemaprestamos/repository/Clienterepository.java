package co.bancolombia.sistemaprestamos.repository;

import co.bancolombia.sistemaprestamos.model.Cliente;
import co.bancolombia.sistemaprestamos.model.Datosprestamo;
import co.bancolombia.sistemaprestamos.model.Prestamoscliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Clienterepository extends JpaRepository<Cliente, Long> {
        @Query(value = "select p.id_prestamo , p.monto , p.interes , p.plazo,e.descripcion , t.tipo_novedad, t.fecha, t.hora" +
        " from prestamo p JOIN estado_prestamo e ON e.id_estado = p.estado" +
        " join transaccion t on p.id_prestamo = t.prestamo_asociado" +
        " where p.id_prestamo in" +
        " (select p.id_prestamo " +        " from prestamo p " +
        " where p.cliente =?1" +        " order by p.id_prestamo desc limit 3)" +
        " order by p.id_prestamo, t.fecha, t.hora", nativeQuery = true)
        List<Prestamoscliente> dacliente(Long Idcliente);
        @Query(value = "SELECT  p.id_prestamo , p.monto , p.interes , p.plazo , e.descripcion , " +
        "c.id_cliente, c.nombre , c.email , c.telefono , c.direccion  from prestamo p " +
        " JOIN estado_prestamo e ON e.id_estado = p.estado" +
        " JOIN cliente c on c.id_cliente = p.cliente" +
        " Where p.cliente =?1", nativeQuery = true)
        List<Datosprestamo> prestamoscliente(Long Idcliente);


}
