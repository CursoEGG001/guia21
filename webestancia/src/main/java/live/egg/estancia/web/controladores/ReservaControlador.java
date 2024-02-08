/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import live.egg.estancia.web.entidades.Estancias;
import live.egg.estancia.web.entidades.Reserva;
import live.egg.estancia.web.excepciones.MiException;
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

    @GetMapping("/lista")
    public String listarReservas(Model model) {
        List<Reserva> reserva = reservaServicio.listarReserva();

        model.addAttribute("reserva", reserva);
        return "reserva_lista";
    }

    @GetMapping("/registrar")
    public String hacerReservas(Model model) {
        List<Estancias> alquiler = estanciasServicio.listarEstancias();
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
            @RequestParam Long idEstancia,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaLlegada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaSalida,
            Model model
    ) throws MiException {

        Estancias lugarElegido = estanciasServicio.getOne(idEstancia);
        List<Estancias> alquileres = estanciasServicio.listarEstancias();
        List<Estancias> nueva = new ArrayList<>();
        try {
            if (id == null) {
                nueva.add(lugarElegido);
                reservaServicio.crearReserva(nueva, fechaLlegada, fechaSalida);
            } else {
                Reserva reserva = reservaServicio.GetOne(id);
                nueva = reserva.getAlquiler();
                nueva.add(lugarElegido);
                reservaServicio.modificarReserva(id, nueva, fechaLlegada, fechaSalida);
            }
            model.addAttribute("exito", "Se agregó una reserva.");
        } catch (MiException e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("alquileres", alquileres);
            model.addAttribute("idEstancia", idEstancia);
            return "reserva_form";
        }
        return "redirect:/";
    }
}
