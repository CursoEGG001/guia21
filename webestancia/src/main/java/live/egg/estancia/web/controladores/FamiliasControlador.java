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
import org.springframework.web.bind.annotation.RequestMapping;

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

        List<Casas> casas = casasServicio.listarCasas();
        model.addAttribute("casas", casas);

        return "familias_form";
    }

    @GetMapping("/registrar/{idFamilia}")
    public String asentarEstancias(@PathVariable Long idFamilia, ModelMap model) {

        Familias familia = familiasServicio.getOne(idFamilia);
        String nombre = familia.getNombre();
        int edadMinima = familia.getEdadMinima();
        int edadMaxima = familia.getEdadMaxima();
        int numHijos = familia.getNumHijos();
        String email = familia.getEmail();

        List<Casas> casas = casasServicio.listarCasas();

        model.addAttribute("idFamilia", idFamilia);
        model.addAttribute("nombre", nombre);
        model.addAttribute("edadMinima", edadMinima);
        model.addAttribute("edadMaxima", edadMaxima);
        model.addAttribute("numHijos", numHijos);
        model.addAttribute("email", email);
        model.addAttribute("casas", casas);

        return "familias_form";
    }

}
