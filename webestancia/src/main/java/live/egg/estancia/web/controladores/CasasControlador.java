/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.servicios.CasasServicio;
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
@RequestMapping("/casas")
public class CasasControlador {

    @Autowired
    CasasServicio casasServicio;

    @RequestMapping("/lista")
    public String listarCasas(Model model) {
        List<Casas> casas = casasServicio.listarCasas();
        model.addAttribute("casas", casas);
        return "casas_lista";
    }

    @GetMapping("/registrar/{id}")
    public String registraCasa(@PathVariable(required = false) Long id, Model model) {

        if (id != null) {
            Casas casa = casasServicio.getOne(id);
            String calle = casa.getCalle();
            Integer numero = casa.getNumero();
            String codigoPostal = casa.getCodigoPostal();
            String ciudad = casa.getCiudad();
            String pais = casa.getPais();
            Date fechaDesde = casa.getFechaDesde();
            Date fechaHasta = casa.getFechaHasta();
            Integer tiempoMinimo = casa.getTiempoMinimo();
            Integer tiempoMaximo = casa.getTiempoMaximo();
            BigDecimal precioHabitacion = casa.getPrecioHabitacion();
            String tipoVivienda = casa.getTipoVivienda();

            model.addAttribute("casa", casa);
            model.addAttribute("calle", calle);
            model.addAttribute("numero", numero);
            model.addAttribute("codigoPostal", codigoPostal);
            model.addAttribute("ciudad", ciudad);
            model.addAttribute("pais", pais);
            model.addAttribute("fechaDesde", fechaDesde);
            model.addAttribute("fechaHasta", fechaHasta);
            model.addAttribute("tiempoMinimo", tiempoMinimo);
            model.addAttribute("tiempoMaximo", tiempoMaximo);
            model.addAttribute("precioHabitacion", precioHabitacion);
            model.addAttribute("tipoVivienda", tipoVivienda);

        }
        return "casas_form";
    }

    @PostMapping("/registro")
    public String registroCasa(@RequestParam(required = false) Long id,
            @RequestParam String calle,
            @RequestParam Integer numero,
            @RequestParam String ciudad,
            @RequestParam String codigoPostal,
            @RequestParam String pais,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaDesde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaHasta,
            @RequestParam Integer tiempoMinimo,
            @RequestParam Integer tiempoMaximo,
            @RequestParam BigDecimal precioHabitacion,
            @RequestParam String tipoVivienda,
            Model model) {

        try {
            if (id != null) {
                casasServicio.modificarCasa(id, calle, numero, ciudad, codigoPostal, pais, fechaDesde, fechaHasta, tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda);
            } else {
                casasServicio.crearCasa(calle, numero, ciudad, codigoPostal, pais, fechaDesde, fechaHasta, tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda);
            }

            model.addAttribute("exito", "Se registró la propiedad en alquiler");
            return "redirect:/usuario/";
        } catch (MiException ex) {

            if (id != null) {
                model.addAttribute("casa", casasServicio.getOne(id));
            }
            model.addAttribute("calle", calle);
            model.addAttribute("numero", numero);
            model.addAttribute("ciudad", ciudad);
            model.addAttribute("codigoPostal", codigoPostal);
            model.addAttribute("pais", pais);
            model.addAttribute("fechaDesde", fechaDesde);
            model.addAttribute("fechaHasta", fechaHasta);
            model.addAttribute("tiempoMinimo", tiempoMinimo);
            model.addAttribute("tiempoMaximo", tiempoMaximo);
            model.addAttribute("precioHabitacion", precioHabitacion);
            model.addAttribute("tipoVivienda", tipoVivienda);
            model.addAttribute("error", ex.getMessage());
            return "casas_form";

        }
    }

}
