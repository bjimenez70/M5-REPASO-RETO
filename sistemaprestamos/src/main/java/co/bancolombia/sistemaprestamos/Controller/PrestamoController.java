package co.bancolombia.sistemaprestamos.Controller;

import co.bancolombia.sistemaprestamos.DTO.CreaPrestamodto;
import co.bancolombia.sistemaprestamos.DTO.GestionClientedto;
import co.bancolombia.sistemaprestamos.DTO.GestionarPrestamodto;
import co.bancolombia.sistemaprestamos.model.Datosprestamo;
import co.bancolombia.sistemaprestamos.model.Planpagos;
import co.bancolombia.sistemaprestamos.model.Prestamo;
import co.bancolombia.sistemaprestamos.model.Prestamoscliente;
import co.bancolombia.sistemaprestamos.service.Prestamoservice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")

public class PrestamoController {
    private final Prestamoservice prestamoService;

    public PrestamoController(Prestamoservice prestamoService) {
        this.prestamoService = prestamoService;
    }

    //Crear un prestamo.
    @PostMapping("/Solicitar")
    public ResponseEntity<String> crearPrestamo(@Valid @RequestBody CreaPrestamodto crearDTO) {
        Prestamo prestamo = prestamoService.save(crearDTO);
        return new ResponseEntity<>("Prestamo creado exitosamente. \nNúmero de prestamo: "
        + prestamo.getIdPrestamo(), HttpStatus.CREATED);
    }
    //aprobar un prestamo.
    //@PostMapping("/aprobar")
    @PutMapping("/aprobar")
    public ResponseEntity<String> aprobarPrestamo(@Valid @RequestBody GestionarPrestamodto gestionDTO) {
        Prestamo prestamo = prestamoService.aprobado(gestionDTO);
        return new ResponseEntity<>("Prestamo aprobado exitosamente. \nNúmero de prestamo: " + prestamo.getIdPrestamo(), HttpStatus.CREATED);
    }
    //rechazar un prestamo.
    @PutMapping("/rechazar")
    public ResponseEntity<String> rechazarPrestamo(@Valid @RequestBody GestionarPrestamodto gestionDTO) {
        Prestamo prestamo = prestamoService.rechazar(gestionDTO);
        return new ResponseEntity<>("Prestamo rechazado. \nNúmero de prestamo: " + prestamo.getIdPrestamo(), HttpStatus.CREATED);
    }
    //listar plan de pagos.
    @GetMapping("/planpagos")
    public ResponseEntity<List<Planpagos>> listarplanpagos(@Valid @RequestBody GestionarPrestamodto gestionDTO) {
        List<Planpagos> planpagos = prestamoService.plan(gestionDTO);
        //return new ResponseEntity<>("Prestamo rechazado. \nNúmero de prestamo: " + prestamo.getIdprestamo(), HttpStatus.CREATED);
        return new ResponseEntity<>(planpagos, HttpStatus.CREATED);
    }
    //estado de un prestamo.
    @GetMapping("/consultaestado")
    public ResponseEntity<String> estadoPrestamo(@Valid @RequestBody GestionarPrestamodto gestionDTO) {
        String estadoprestamo = prestamoService.estadoprestamo(gestionDTO);
        return new ResponseEntity<>("Estado Prestamo: " + estadoprestamo, HttpStatus.CREATED);
    }

    //datos de un prestamo.
    @GetMapping("/datosprestamo")
    public ResponseEntity<String> datosPrestamo(@Valid @RequestBody GestionarPrestamodto gestionDTO) {
        String  datosprestamo = prestamoService.datosprestamo(gestionDTO);

        return new ResponseEntity<>(datosprestamo, HttpStatus.CREATED);
    }
    //prestamos cliente y sus transacciones
    @GetMapping("/prestamoclienteytrans")
    public ResponseEntity<List<Prestamoscliente>> clientePrestamo(@Valid @RequestBody GestionClientedto gestionDTO) {

        List<Prestamoscliente> prestamosclienteslist = prestamoService.prestamosclientes(gestionDTO);
        return new ResponseEntity<>(prestamosclienteslist, HttpStatus.CREATED);
    }
    //prestamos del cliente
        @GetMapping("/prestamocliente")
    public ResponseEntity<List<Datosprestamo>> listclientePrestamo(@Valid @RequestBody GestionClientedto gestionDTO) {

        List<Datosprestamo> prestamosclienteslist = prestamoService.lisprestamosclientes(gestionDTO);
        return new ResponseEntity<>(prestamosclienteslist, HttpStatus.CREATED);
    }


}
