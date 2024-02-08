/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.List;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.entidades.Comentarios;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.servicios.CasasServicio;
import live.egg.estancia.web.servicios.ComentariosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/comentarios")
public class ComentariosControlador {

    @Autowired
    ComentariosServicio comentarioServicio;

    @Autowired
    CasasServicio casasServicio;

    @GetMapping("/lista")
    public String cargarComentarios(Model model) {

        List<Comentarios> comentarios = comentarioServicio.listarComentarios();

        model.addAttribute("comentarios", comentarios);
        return "comentario_lista";
    }

    @GetMapping("/registrar")
    public String crearComentario(ModelMap modelo) {
        List<Casas> casas = casasServicio.listarCasas();

        modelo.addAttribute("casas", casas);

        return "comentario_form";
    }

    @GetMapping("/registrar/{idComentario}")
    public String cambiarComentario(@PathVariable Long idComentario, ModelMap modelo) {
        Comentarios comentario = comentarioServicio.getOne(idComentario);
        List<Casas> casas = casasServicio.listarCasas();

        String comenta = comentario.getComentario();
        Long idCasa = comentario.getIdCasa().getIdCasa(); //Nop, no es error

        modelo.addAttribute("comentario", comentario);
        modelo.addAttribute("casas", casas);
        modelo.addAttribute("comenta", comenta);
        modelo.addAttribute("idCasa", idCasa);

        modelo.addAttribute("casas", casas);

        return "comentario_form.html";
    }

    @PostMapping("/registro")
    public String registraComentario(@RequestParam(required = false) Long idComentario, @RequestParam String comenta, @RequestParam Long idCasa, ModelMap modelo) throws MiException {
        try {

            if (idComentario == null) {

                comentarioServicio.crearComentario(comenta, idCasa);
            } else {
                comentarioServicio.modificarComentario(idComentario, comenta, idCasa);
            }

            modelo.put("exito", "Usuario registrado correctamente!");

        } catch (MiException ex) {
            List<Casas> casas = casasServicio.listarCasas();

            modelo.addAttribute("casas", casas);
            modelo.addAttribute("comenta", comenta);
            modelo.addAttribute("idCasa", idCasa);
            modelo.put("error", ex.getMessage());

            return "comentario_form";
        }
        return "redirect:/";
    }

}
