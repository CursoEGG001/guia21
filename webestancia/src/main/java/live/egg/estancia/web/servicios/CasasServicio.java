/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.repositorios.CasasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class CasasServicio {

    @Autowired
    CasasRepository casaRepositorio;

    @Transactional
    public void crearCasa(String calle, Integer numero, String ciudad, String codigoPostal, String pais, Date fechaDesde, Date fechaHasta, Integer tiempoMinimo, Integer tiempoMaximo, BigDecimal precioHabitacion, String tipoVivienda) throws MiException {

        valida(calle, numero, ciudad, codigoPostal, pais, fechaDesde, fechaHasta, tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda);
        Casas casa = new Casas();

        casa.setCalle(calle);
        casa.setNumero(numero);
        casa.setCodigoPostal(codigoPostal);
        casa.setCiudad(ciudad);
        casa.setCodigoPostal(codigoPostal);
        casa.setFechaDesde(fechaDesde);
        casa.setFechaHasta(fechaHasta);
        casa.setPais(pais);
        casa.setTiempoMaximo(tiempoMaximo);
        casa.setTiempoMinimo(tiempoMinimo);
        casa.setPrecioHabitacion(precioHabitacion);
        casa.setTipoVivienda(tipoVivienda);
        casa.setActive(Boolean.TRUE);

        casaRepositorio.save(casa);
    }

    @Transactional
    public List<Casas> listarCasas() {
        return casaRepositorio.findAll();
    }

    @Transactional
    public void modificarCasa(Long idCasa, String calle, Integer numero, String ciudad, String codigoPostal, String pais, Date fechaDesde, Date fechaHasta, Integer tiempoMinimo, Integer tiempoMaximo, BigDecimal precioHabitacion, String tipoVivienda) throws MiException {

        valida(calle, numero, ciudad, codigoPostal, pais, fechaDesde, fechaHasta, tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda);
        Optional<Casas> casaAcambiar = casaRepositorio.findById(idCasa);

        Casas casa = new Casas();

        if (casaAcambiar.isPresent()) {
            casa.setIdCasa(idCasa);
            casa.setCalle(calle);
            casa.setNumero(numero);
            casa.setCiudad(ciudad);
            casa.setCodigoPostal(codigoPostal);
            casa.setPais(pais);
            casa.setFechaDesde(fechaDesde);
            casa.setFechaHasta(fechaHasta);
            casa.setPrecioHabitacion(precioHabitacion);
            casa.setTiempoMaximo(tiempoMaximo);
            casa.setTiempoMinimo(tiempoMinimo);
            casa.setTipoVivienda(tipoVivienda);
            casa.setActive(Boolean.TRUE);

            if (casaAcambiar.get().getActive()) {
                casaRepositorio.save(casa);
            }

        }

    }

    @Transactional
    public Casas getOne(Long idCasa) {
        return casaRepositorio.getReferenceById(idCasa);
    }

    private void valida(String calle, Integer numero, String ciudad, String codigoPostal, String pais, Date fechaDesde, Date fechaHasta, Integer tiempoMinimo, Integer tiempoMaximo, BigDecimal precioHabitacion, String tipoVivienda) throws MiException {
        if (calle.isEmpty() || calle == null) {
            throw new MiException("la calle no puede ser nula o estar vacía");
        }
        if (numero == null) {
            throw new MiException("el nuemro no puede ser nulo o estar vacio");
        }
        if (ciudad.isEmpty() || ciudad == null) {
            throw new MiException("La ciudad no puede estar vacía");
        }
        if (ciudad.isEmpty() || ciudad == null) {
            throw new MiException("La ciudad no puede estar vacía");
        }
        if (fechaDesde == null || fechaHasta == null) {
            throw new MiException("Las fechas no pueden ser nulas");
        }
        if (fechaDesde.compareTo(Date.from(Instant.now())) < 0) {
            throw new MiException("La fecha de inicio es inválida");
        }
        if (fechaDesde.compareTo(fechaHasta) == 0) {
            throw new MiException("Las fechas ingresadas no deben ser iguales");
        }
        if (fechaDesde.compareTo(fechaHasta) > 0 || fechaHasta.compareTo(fechaDesde) < 0) {
            throw new MiException("Las fecha de inicio ingresada no debe ser posterior a la de finalización");
        }
        if (tiempoMaximo == 0 || (Date.from(Instant.now().plus(tiempoMaximo, ChronoUnit.DAYS)).compareTo(fechaHasta) > 0)) {
            throw new MiException("Tiempo Máximo es invalido");
        }
        if (tiempoMinimo == null) {
            throw new MiException("el tiempo mínimo no puede ser nulo o estar vacio");
        }
        if (precioHabitacion == null) {
            throw new MiException("el precio de alquiler  no puede ser nulo o estar vacio");
        }
        if (tipoVivienda.isEmpty() || tipoVivienda == null) {
            throw new MiException("Tipo de vivienda no puede estar vacía");
        }
    }
}
