/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.entidades.Reserva;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.repositorios.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class ReservaServicio {

    @Autowired
    ReservaRepository reservaRepositorio;

    @Transactional
    public void crearReserva(Casas alquiler, Date fechaLlegada, Date fechaSalida) throws MiException {

        valida(alquiler, fechaLlegada, fechaSalida);

        Reserva reserva = new Reserva();

        reserva.setAlquiler(alquiler);
        reserva.setFechaLlegada(fechaLlegada);
        reserva.setFechaSalida(fechaSalida);
        reserva.setActive(Boolean.TRUE);

        reservaRepositorio.save(reserva);

    }

    @Transactional
    public Collection<Reserva> listarReserva() {
        return reservaRepositorio.findAll();
    }

    @Transactional
    public Reserva GetOne(Long id) {
        return reservaRepositorio.getReferenceById(id);
    }

    @Transactional
    public void modificarReserva(Long id, Casas alquiler, Date fechaLlegada, Date fechaSalida) throws MiException {

        valida(alquiler, fechaLlegada, fechaSalida);

        Optional<Reserva> rsvAcambiar = reservaRepositorio.findById(id);

        Reserva reserva = new Reserva();

        if (rsvAcambiar.isPresent()) {

            reserva.setFechaLlegada(fechaLlegada);
            reserva.setFechaSalida(fechaSalida);
            reserva.setId(id);
            reserva.setAlquiler(alquiler);
            reserva.setActive(Boolean.TRUE);

            if (rsvAcambiar.get().getActive()) {
                reservaRepositorio.save(reserva);
            }

        }
    }

    private void valida(Casas alquiler, Date fechaLlegada, Date fechaSalida) throws MiException {

        if (alquiler == null) {
            throw new MiException("No debe estar vacio");
        }
        if (fechaLlegada.equals(fechaSalida)) {
            throw new MiException("Fecha inválida");
        }
        if (fechaLlegada.after(fechaSalida) || fechaSalida.before(fechaLlegada)) {
            throw new MiException("Fechas inválidas de llegada y salida");
        }
        if (fechaLlegada == null || fechaSalida == null) {
            throw new MiException("Ingrese una fecha");
        }
    }

}
