package co.bancolombia.sistemaprestamos.service;


import co.bancolombia.sistemaprestamos.DTO.CreaPrestamodto;
import co.bancolombia.sistemaprestamos.DTO.GestionClientedto;
import co.bancolombia.sistemaprestamos.DTO.GestionarPrestamodto;
import co.bancolombia.sistemaprestamos.model.*;
import co.bancolombia.sistemaprestamos.repository.Clienterepository;
import co.bancolombia.sistemaprestamos.repository.Prestamorepository;
import co.bancolombia.sistemaprestamos.repository.Transaccionrepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class Prestamoservice {
    private final Prestamorepository prestamorepository;
    private final Clienterepository clienterepository;
    private final Transaccionrepository transaccionRepository;

    public Prestamoservice(Prestamorepository prestamoRepository, Clienterepository clienteRepository, Transaccionrepository transaccionRepository) {
        this.prestamorepository = prestamoRepository;
        this.clienterepository = clienteRepository;
        this.transaccionRepository = transaccionRepository;
    }

    //Adicionar prestamos
    @Transactional
    public Prestamo save(CreaPrestamodto creaDTO) {
        Cliente cliente = clienterepository.findById(creaDTO.getIdcliente()).orElseThrow(() ->
                new NoSuchElementException("Cliente no existe"));
        //Crea prestamos
        Prestamo prestamo = new Prestamo();
        prestamo.setMonto(creaDTO.getMonto());
        prestamo.setPlazo(creaDTO.getPlazo());
        prestamo.setInteres(BigDecimal.valueOf(2.5));
        prestamo.setCliente(cliente);
        prestamo.setEstado(1);
        //Prestamo prestamonew = prestamoRepository.save(prestamo);
        if (prestamorepository.save(prestamo) == null) {
            throw new RuntimeException("Prestamo no creado");
        }
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(LocalDate.now());
        transaccion.setHora(LocalTime.now());
        transaccion.setTipoNovedad("Prestamo nuevo creado");
        transaccion.setValor(creaDTO.getMonto());
        transaccion.setPrestamoAsociado(prestamo);
        //grabat base de datos
        transaccionRepository.save(transaccion);
        return prestamo;


    }

    //aprobar prestamos
    @Transactional
    public Prestamo aprobado(GestionarPrestamodto gestionDTO) {
        Prestamo prestamo = prestamorepository.findById(gestionDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Id no existe"));
        if (!prestamo.getEstado().equals(1)) {
            throw new RuntimeException("Prestamo en estado incorrecto");
        }
        prestamo.setEstado(2);
        prestamorepository.save(prestamo);

        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(LocalDate.now());
        transaccion.setHora(LocalTime.now());
        transaccion.setTipoNovedad("Prestamo Aprobado");
        transaccion.setValor(prestamo.getMonto());
        transaccion.setPrestamoAsociado(prestamo);
        //grabat base de datos
        transaccionRepository.save(transaccion);

        return prestamo;
    }

    //rechazar prestamos
    @Transactional
    public Prestamo rechazar(GestionarPrestamodto gestionDTO) {
        Prestamo prestamo = prestamorepository.findById(gestionDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Id no existe"));
        if (!prestamo.getEstado().equals(1)) {
            throw new RuntimeException("Prestamo en estado incorrecto");
        }
        prestamo.setEstado(3);
        prestamorepository.save(prestamo);
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(LocalDate.now());
        transaccion.setHora(LocalTime.now());
        transaccion.setTipoNovedad("Prestamo Rechazado");
        transaccion.setValor(prestamo.getMonto());
        transaccion.setPrestamoAsociado(prestamo);
        //grabar base de datos
        transaccionRepository.save(transaccion);
        return prestamo;
    }

    //Crear simulacion de plan de pagos
    @Transactional
    public List<Planpagos> plan(GestionarPrestamodto gestionDTO) {
        Prestamo prestamo = prestamorepository.findById(gestionDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Id no existe"));
        List<Planpagos> listplanpagos = new ArrayList<>();
        BigDecimal kapital = prestamo.getMonto().divide(BigDecimal.valueOf(prestamo.getPlazo()), 0, RoundingMode.HALF_UP);
        BigDecimal interes;
        BigDecimal kapitalajustado;
        BigDecimal saldo = prestamo.getMonto();


        for (int i = 1; i <= prestamo.getPlazo(); i++) {
            interes = saldo.multiply(prestamo.getInteres().divide(BigDecimal.valueOf(100)));
            saldo = saldo.subtract(kapital);
            if (i == prestamo.getPlazo() - 1) {
                kapitalajustado = saldo.subtract(kapital);
                saldo = saldo.subtract(kapitalajustado);
            }
            listplanpagos.add(new Planpagos(i, kapital, interes, kapital.add(interes), saldo));
        }
        return listplanpagos;
    }

    //consulta estado
    @Transactional
    public String estadoprestamo(GestionarPrestamodto gestionDTO) {
        Prestamo prestamo = prestamorepository.findById(gestionDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Id no existe"));

        return prestamorepository.descripcionestado(gestionDTO.getId());
    }

    //consulta datos prestamo
    @Transactional
    public String datosprestamo(GestionarPrestamodto gestionDTO) {
        Prestamo prestamo = prestamorepository.findById(gestionDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Id no existe"));
        Datosprestamo datopres = prestamorepository.datosprestamo(gestionDTO.getId());
        return " \n" + datopres;
    }

    //consulta ultimos 3 prestamos cliente y todas sus transacciones
    @Transactional
    public List<Prestamoscliente> prestamosclientes(GestionClientedto gestionDTO) {
        Cliente cliente = clienterepository.findById(gestionDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Id cliente no existe"));

        List<Prestamoscliente> prestamosclienteList = new ArrayList<>();
        prestamosclienteList = clienterepository.dacliente(gestionDTO.getId());
        if (prestamosclienteList.isEmpty()) {
            throw new RuntimeException("Cliente no tiene Prestamo");
        }


        return prestamosclienteList;
    }

    //consulta prestamos cliente
    @Transactional
    public List<Datosprestamo> lisprestamosclientes(GestionClientedto gestionDTO) {
        Cliente cliente = clienterepository.findById(gestionDTO.getId()).orElseThrow(() ->
                new NoSuchElementException("Id cliente no existe"));

        List<Datosprestamo> prestamosclientelis = new ArrayList<>();
        prestamosclientelis = clienterepository.prestamoscliente(gestionDTO.getId());
        if (prestamosclientelis.isEmpty()) {
            throw new RuntimeException("Cliente no tiene Prestamo");
        }

        return prestamosclientelis;
    }
}