/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.noticia.eggnews.controlador;

import java.util.Date;
import java.util.List;
import live.egg.noticia.eggnews.entidades.Noticias;
import live.egg.noticia.eggnews.excepciones.MiException;
import live.egg.noticia.eggnews.servicios.NoticiasServicio;
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
@RequestMapping("/")
public class NoticiasControlador {

    @Autowired
    private NoticiasServicio noticiasServicio;

    @GetMapping("/")
    public String index() {

        return "index.html";

    }

    @GetMapping("/inicio")
    public String inicio(ModelMap modelo) throws MiException {
        List<Noticias> noticias = noticiasServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);


        return "noticias_inicio";
    }

    @GetMapping("/noticia/{id}")
    public String noticia(@PathVariable("id") String id, ModelMap modelo) {

        modelo.put("noticia", noticiasServicio.getOne(id));


        return "noticia.html";
    }

    @GetMapping("/panelAdmin")
    public String panelAdmin(ModelMap modelo) {
        

        
        return ("/panelAdmin.html");
    }

    @PostMapping("/noticia/crear")
    public String guardarNoticia(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) {

        Date fecha = new Date();

        try {
            noticiasServicio.crearNoticia(titulo, cuerpo, fecha);

            modelo.put("exito", "La noticia fue cargada correctamente!");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return ("/panelAdmin");
        }

        return ("redirect:/inicio");
    }

}
