/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.List;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.entidades.Familias;
import live.egg.estancia.web.servicios.CasasServicio;
import live.egg.estancia.web.servicios.FamiliasServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/familias")
public class FamiliasControlador {

    @Autowired
    CasasServicio casasServicio;
    @Autowired
    FamiliasServicio familiasServicio;

    @GetMapping("/lista")
    public String verFamilias(ModelMap model) {

        List<Familias> familias = familiasServicio.listarFamilias();
        model.addAttribute("familias", familias);
        return "familias_lista";
    }

    @GetMapping("/registrar")
    public String asentarFamilia(ModelMap model) {

        Familias familia = new Familias();

        List<Casas> casas = casasServicio.listarCasas();
        model.addAttribute("familia", familia);
        model.addAttribute("idFamilia", familia.getIdFamilia());
        model.addAttribute("nombre", familia.getNombre());
        model.addAttribute("edadMinima", familia.getEdadMinima());
        model.addAttribute("edadMaxima", familia.getEdadMaxima());
        model.addAttribute("numHijos", familia.getNumHijos());
        model.addAttribute("email", familia.getEmail());
        model.addAttribute("casas", casas);

        return "familias_form";
    }

    @GetMapping("/registrar/{idFamilia}")
    public String asentarFamilia(@PathVariable Long idFamilia, ModelMap model) {

        Familias familia = familiasServicio.getOne(idFamilia);
        String nombre = familia.getNombre();
        int edadMinima = familia.getEdadMinima();
        int edadMaxima = familia.getEdadMaxima();
        int numHijos = familia.getNumHijos();
        String email = familia.getEmail();

        List<Casas> casas = casasServicio.listarCasas();

        model.addAttribute("familia", familia);
        model.addAttribute("idFamilia", idFamilia);
        model.addAttribute("nombre", nombre);
        model.addAttribute("edadMinima", edadMinima);
        model.addAttribute("edadMaxima", edadMaxima);
        model.addAttribute("numHijos", numHijos);
        model.addAttribute("email", email);
        model.addAttribute("casas", casas);

        return "familias_form";
    }

    @PostMapping("/registro")
    public String registroFamilia(@RequestParam(required = false) Long idFamilia, @RequestParam String nombre, @RequestParam int edadMinima, @RequestParam int edadMaxima, @RequestParam int numHijos, @RequestParam String email, @RequestParam Long idCasa, ModelMap modelo) {
        try {

            if (idFamilia == null) {
                familiasServicio.crearFamilia(nombre, edadMinima, edadMaxima, numHijos, email);
            } else {
                familiasServicio.modificarFamilia(idFamilia, nombre, edadMinima, edadMaxima, numHijos, email);
            }

        } catch (Exception e) {
            Familias familia = familiasServicio.getOne(idFamilia);
            List<Casas> casas = casasServicio.listarCasas();

            modelo.addAttribute("familia", familia);
            modelo.addAttribute("idFamilia", idFamilia);
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("edadMinima", edadMinima);
            modelo.addAttribute("edadMaxima", edadMaxima);
            modelo.addAttribute("numHijos", numHijos);
            modelo.addAttribute("email", email);
            modelo.addAttribute("casas", casas);

            return "familias_form";
        }
        return "redirect:/";
    }

}
