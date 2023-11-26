/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.Date;
import java.util.List;
import live.egg.estancia.web.entidades.Clientes;
import live.egg.estancia.web.entidades.Estancias;
import live.egg.estancia.web.entidades.Reserva;
import live.egg.estancia.web.servicios.ClientesServicio;
import live.egg.estancia.web.servicios.EstanciasServicio;
import live.egg.estancia.web.servicios.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/reserva")
public class ReservaControlador {

    @Autowired
    ReservaServicio reservaServicio;
    @Autowired
    EstanciasServicio estanciasServicio;
    @Autowired
    ClientesServicio clientesServicio;

    @GetMapping("/lista")
    public String listarReservas(Model model) {
        List<Reserva> reserva = reservaServicio.listarReserva();

        model.addAttribute("reserva", reserva);
        return "reserva_lista";
    }

    @GetMapping("/registrar")
    public String hacerReservas(Model model) {
        List<Estancias> alquiler = estanciasServicio.listarEstancias();
        model.addAttribute("alquiler", alquiler);
        return "reserva_form";
    }

    @GetMapping("/registrar/{id}")
    public String hacerReserva(@PathVariable Long id, Model model) {
        Reserva reserva = reservaServicio.GetOne(id);
        model.addAttribute("reserva", reserva);
        return "reserva_form";
    }

    @PostMapping("/registro")
    public String guardarReserva(@RequestParam(required = false) Long id, @RequestParam Long idCliente, @RequestParam Long idEstancia, @RequestParam Date fechaLlegada, @RequestParam Date fechaSalida) {

        List<Estancias> alquiler = estanciasServicio.listarEstancias();
        Clientes cliente = clientesServicio.getOne(idCliente);
        if (id == null) {
            alquiler.add(estanciasServicio.getOne(idEstancia));
            reservaServicio.crearReserva(cliente, alquiler, fechaLlegada, fechaSalida);
        } else {
            reservaServicio.modificarReserva(id, cliente, alquiler, fechaLlegada, fechaSalida);
        }
        return "redirect/";
    }
}
