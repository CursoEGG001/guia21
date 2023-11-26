/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.List;
import live.egg.estancia.web.entidades.Reserva;
import live.egg.estancia.web.servicios.EstanciasServicio;
import live.egg.estancia.web.servicios.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/registrar/")
    public String hacerReservas(Model model) {
        model.addAttribute("attribute", "value");
        return "reserva_lista";
    }

    @GetMapping("/registrar/{id}")
    public String hacerReservas(@PathVariable Long id, Model model) {
        Reserva reserva = reservaServicio.GetOne(id);
        model.addAttribute("reserva", reserva);
        return "reserva_lista";
    }

    @GetMapping("/registro")
    public String registroReservas(Model model) {
        model.addAttribute("attribute", "value");
        return "reserva_lista";
    }

}
