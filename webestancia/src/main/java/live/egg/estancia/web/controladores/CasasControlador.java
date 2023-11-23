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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/registrar")
    public String registraCasa(Model model) {
        model.addAttribute("attribute", "value");
        return "casas_form";
    }

    @PostMapping("/registro")
    public String registroCasa(@RequestParam String calle,
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
            ModelMap model) {

        try {
            casasServicio.crearCasa(calle, numero, ciudad, codigoPostal, pais, fechaDesde, fechaHasta, tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda);
            model.addAttribute("exito", "Se registró la propiedad en alquiler");
            return "redirect:/usuario/";
        } catch (MiException ex) {

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
            model.put("error", ex.getMessage());
            return "casas_form";

        }
    }

}
