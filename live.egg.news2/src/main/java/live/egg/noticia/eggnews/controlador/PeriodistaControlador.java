/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.noticia.eggnews.controlador;

import jakarta.servlet.http.HttpSession;
import java.util.Date;
import live.egg.noticia.eggnews.entidades.Usuario;
import live.egg.noticia.eggnews.excepciones.MiException;
import live.egg.noticia.eggnews.servicios.NoticiasServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/")
public class PeriodistaControlador {

    @Autowired
    private NoticiasServicio noticiasServicio;


    @GetMapping("/panelAdmin")
    public String panelAdmin(ModelMap modelo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return ("/panelAdmin.html");
    }

    @PreAuthorize("hasAnyRole('ROLE_PERIODISTA', 'ROLE_ADMIN')")
    @PostMapping("/noticia/crear")
    public String guardarNoticia(@RequestParam String titulo, @RequestParam String cuerpo, @RequestParam(required = false) String creador, ModelMap modelo) {

        Date fecha = new Date();

        try {

            if (creador == null) {
                noticiasServicio.crearNoticia(titulo, cuerpo, fecha);
            } else {
                noticiasServicio.crearNoticia(titulo, cuerpo, fecha, creador);
            }

            modelo.put("exito", "La noticia fue cargada correctamente!");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return ("/panelAdmin");
        }

        return ("redirect:/inicio");
    }

}
