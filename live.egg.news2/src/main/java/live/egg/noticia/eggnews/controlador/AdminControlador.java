/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.noticia.eggnews.controlador;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import live.egg.noticia.eggnews.entidades.Rol;
import live.egg.noticia.eggnews.entidades.Usuario;
import live.egg.noticia.eggnews.excepciones.MiException;
import live.egg.noticia.eggnews.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pc
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_PERIODISTA', 'ROLE_ADMIN')")
@RequestMapping("/admin")

public class AdminControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @PreAuthorize("hasAnyRole('ROLE_PERIODISTA', 'ROLE_ADMIN')")
    @GetMapping("/dashboard")
    public String panelAdministrativo(ModelMap modelo, HttpSession session) {
        List<Usuario> periodistas = usuarioServicio.listarUsuarios()
                .stream().filter(usuario -> usuario.getRol()
                .equals(Rol.PERIODISTA))
                .collect(Collectors.toList());
        modelo.addAttribute("periodistas", periodistas);
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "panelAdmin.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PERIODISTA', 'ROLE_ADMIN')")
    @GetMapping("/usuarios")
    public String listarUsuarios(ModelMap modelo) {

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "usuario_list.html";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) throws MiException {
        usuarioServicio.cambiarRol(id);

        return "redirect:/admin/usuarios";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PERIODISTA')")
    @GetMapping("/modificar/{id}")
    public String modificarUsuario(@PathVariable String id, ModelMap modelo) {

        Usuario aModificar = usuarioServicio.getOne(id);

        modelo.put("usuario", aModificar);

        return "usuario_form";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PERIODISTA')")
    @PostMapping("/modificar/{id}")
    public String guardarModificarUsuario(@PathVariable String id, @RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {
        try {
            usuarioServicio.actualizar(id, nombre, email, password, password2);

        } catch (MiException ex) {
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("email", email);
            modelo.put("error", ex.getMessage());
            return "usuario_form";

        }
        return "redirect:/";
    }

}
