/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.entidades.Reserva;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.servicios.CasasServicio;
import live.egg.estancia.web.servicios.EstanciasServicio;
import live.egg.estancia.web.servicios.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    CasasServicio casasServicio;

    @GetMapping("/lista")
    public String listarReservas(Model model) {
        Collection<Reserva> reservas =  reservaServicio.listarReserva();

        model.addAttribute("reservas", reservas);
        return "reserva_lista";
    }

    @GetMapping("/registrar")
    public String hacerReservacion(Model model) {
        List<Casas> alquiler = casasServicio.listarCasas();
        model.addAttribute("alquileres", alquiler);
        return "reserva_form";
    }

    @GetMapping("/registrar/{id}")
    public String hacerReserva(@PathVariable Long id, Model model) {
        Reserva reserva = reservaServicio.GetOne(id);
        model.addAttribute("reserva", reserva);
        return "reserva_form";
    }

    @PostMapping("/registro")
    public String guardarReserva(
            @RequestParam(required = false) Long id,
            @RequestParam Long idCasa,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaLlegada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaSalida,
            Model model
    ) throws MiException {

        Casas lugarElegido = casasServicio.getOne(idCasa);
        List<Casas> alquileres = casasServicio.listarCasas();

        try {
            if (id == null) {
                reservaServicio.crearReserva(lugarElegido, fechaLlegada, fechaSalida);
            } else {
                reservaServicio.modificarReserva(id, lugarElegido, fechaLlegada, fechaSalida);
            }
            model.addAttribute("exito", "Se agregó una reserva.");
        } catch (MiException e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("alquileres", alquileres);
            model.addAttribute("lugarElegido", lugarElegido);
            return "reserva_form";
        }
        return "redirect:/";
    }
}
