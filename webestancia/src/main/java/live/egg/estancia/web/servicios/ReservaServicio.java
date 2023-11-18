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
    public void crearReserva(Clientes cliente, List<Estancias> alquiler, Date fechaLlegada, Date fechaSalida) {

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
    public void modificarReserva(Long id, Clientes cliente, List<Estancias> alquiler, Date fechaLlegada, Date fechaSalida){
        
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
    
    
}
