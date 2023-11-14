/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.noticia.eggnews.controlador;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import live.egg.noticia.eggnews.entidades.Noticias;
import live.egg.noticia.eggnews.entidades.Usuario;
import live.egg.noticia.eggnews.excepciones.MiException;
import live.egg.noticia.eggnews.servicios.NoticiasServicio;
import live.egg.noticia.eggnews.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index(HttpSession session, ModelMap modelo) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", logueado);
        if (logueado != null) {
            modelo.put("exito", "Existe el usuario");
        }
        return "index.html";

    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            String password2, ModelMap modelo) {

        try {
            usuarioServicio.registrar(nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "index.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("error", "Usuario o Contraseña invalidos!");
        }

        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_PERIODISTA', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session,ModelMap modelo) throws MiException {
        
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", logueado);
        
        List<Noticias> noticias = noticiasServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);

        return "noticias_inicio";
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO','ROLE_PERIODISTA', 'ROLE_ADMIN')")
    @GetMapping("/noticia/{id}")
    public String noticia(@PathVariable("id") String id, ModelMap modelo) {
            Noticias noticia = noticiasServicio.getOne(id);

            modelo.put("noticia", noticia);

        return "noticia.html";
    }

}
