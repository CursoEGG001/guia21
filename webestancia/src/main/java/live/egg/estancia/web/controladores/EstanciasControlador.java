/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.List;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.entidades.Clientes;
import live.egg.estancia.web.entidades.Estancias;
import live.egg.estancia.web.repositorios.CasasRepository;
import live.egg.estancia.web.repositorios.ClientesRepository;
import live.egg.estancia.web.repositorios.EstanciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/estancias")
public class EstanciasControlador {

    @Autowired
    EstanciasRepository estanciasRepositorio;
    @Autowired
    CasasRepository casasRepositorio;
    @Autowired
    ClientesRepository clientesRepositorio;

    @GetMapping("/lista")
    public String verEstancias(ModelMap model) {
        List<Estancias> estancias = estanciasRepositorio.findAll();

        model.addAttribute("estancias", estancias);
        return "estancias_lista";
    }

    @GetMapping("/registrar")
    public String asentarEstancias(ModelMap model) {

        List<Estancias> estancias = estanciasRepositorio.findAll();

        model.addAttribute("estancias", estancias);

        return "estancias_form";
    }

}
