/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Clientes;
import live.egg.estancia.web.entidades.Estancias;
import live.egg.estancia.web.entidades.Reserva;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.repositorios.ClientesRepository;
import live.egg.estancia.web.repositorios.EstanciasRepository;
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

    @Autowired
    ClientesRepository clientesRepositorio;

    @Autowired
    EstanciasRepository estanciasRepositorio;

    @Transactional
    public void crearReserva(Clientes cliente, List<Estancias> alquiler, Date fechaLlegada, Date fechaSalida) throws MiException {

        valida(cliente, alquiler, fechaLlegada, fechaSalida);

        Reserva reserva = new Reserva();

        reserva.setCliente(cliente);
        reserva.setAlquiler(alquiler);
        reserva.setFechaLlegada(fechaLlegada);
        reserva.setFechaSalida(fechaSalida);
        reserva.setActive(Boolean.TRUE);

        reservaRepositorio.save(reserva);

    }

    @Transactional
    public List<Reserva> listarReserva() {
        return reservaRepositorio.findAll();
    }

    @Transactional
    public Reserva GetOne(Long id) {
        return reservaRepositorio.getReferenceById(id);
    }

    @Transactional
    public void modificarReserva(Long id, Clientes cliente, List<Estancias> alquiler, Date fechaLlegada, Date fechaSalida) throws MiException {

        valida(cliente, alquiler, fechaLlegada, fechaSalida);

        Optional<Reserva> rsvAcambiar = reservaRepositorio.findById(id);

        Reserva reserva = new Reserva();

        if (rsvAcambiar.isPresent()) {

            reserva.setCliente(cliente);
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

    private void valida(Clientes cliente, List<Estancias> alquiler, Date fechaLlegada, Date fechaSalida) throws MiException {

        if (cliente == null) {
            throw new MiException("Casa inválida");
        }

        if (alquiler != null) {
            int cnt = 0;
            for (Estancias estancias : alquiler) {
                for (Estancias estancias1 : alquiler) {
                    if (estancias.equals(estancias1) && cnt == 2) {
                        throw new MiException("Ya hay una estancia igual");
                    } else {
                        cnt++;
                    }
                }
            }
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
