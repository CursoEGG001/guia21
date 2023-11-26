/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.Date;
import java.util.List;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.entidades.Clientes;
import live.egg.estancia.web.entidades.Estancias;
import live.egg.estancia.web.servicios.CasasServicio;
import live.egg.estancia.web.servicios.ClientesServicio;
import live.egg.estancia.web.servicios.EstanciasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/estancias")
public class EstanciasControlador {

    @Autowired
    EstanciasServicio estanciasServicios;
    @Autowired
    CasasServicio casasServicio;
    @Autowired
    ClientesServicio clientesServicio;

    @GetMapping("/lista")
    public String verEstancias(ModelMap model) {
        List<Estancias> estancias = estanciasServicios.listarEstancias();

        model.addAttribute("estancias", estancias);
        return "estancias_lista";
    }

    @GetMapping("/registrar")
    public String asentarEstancias(ModelMap model) {

        List<Casas> casas = casasServicio.listarCasas();
        List<Clientes> clientes = clientesServicio.listarClientes();
        model.addAttribute("casas", casas);
        model.addAttribute("clientes", clientes);

        return "estancias_form";
    }

    @GetMapping("/registrar/{idEstancia}")
    public String asentarEstancias(@PathVariable Long idEstancia, ModelMap model) {

        Estancias estancia = estanciasServicios.getOne(idEstancia);
        List<Casas> casas = casasServicio.listarCasas();
        List<Clientes> clientes = clientesServicio.listarClientes();

        String nombreHuesped = estancia.getNombreHuesped();
        Date fechaDesde = estancia.getFechaDesde();
        Date fechaHasta = estancia.getFechaHasta();

        model.addAttribute("idEstancia", idEstancia);
        model.addAttribute("nombreHuesped", nombreHuesped);
        model.addAttribute("fechaDesde", fechaDesde);
        model.addAttribute("fechaHasta", fechaHasta);
        model.addAttribute("casas", casas);
        model.addAttribute("clientes", clientes);
        model.addAttribute("casas", casas);
        model.addAttribute("clientes", clientes);

        return "estancias_form";
    }

    @PostMapping("/registro")
    public String registroEstancia(@RequestParam(required = false) Long idEstancia, String nombreHuesped, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDesde, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaHasta, Long idCasa, Long idCliente, ModelMap model) {
        try {
            if (idEstancia == null) {
                estanciasServicios.crearEstancia(nombreHuesped, fechaDesde, fechaHasta, casasServicio.getOne(idCasa), clientesServicio.getOne(idCliente));
            } else {
                estanciasServicios.modificarEstancias(idEstancia, nombreHuesped, fechaDesde, fechaHasta, casasServicio.getOne(idCasa), clientesServicio.getOne(idCliente));
            }
        } catch (Exception ex) {
            List<Casas> casas = casasServicio.listarCasas();
            List<Clientes> clientes = clientesServicio.listarClientes();
            model.addAttribute("idEstancia", idEstancia);
            model.addAttribute("nombreHuesped", nombreHuesped);
            model.addAttribute("fechaDesde", fechaDesde);
            model.addAttribute("fechaHasta", fechaHasta);
            model.addAttribute("casas", casas);
            model.addAttribute("clientes", clientes);
            model.put("error", ex.getMessage());

            return "estancias_form";
        }

        return "redirect:/";
    }

}
