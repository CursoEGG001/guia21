/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import live.egg.estancia.web.entidades.Usuario;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String page(HttpSession session, Model model) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        Usuario muestra = usuarioServicio.listarUsuarios().get(0);
        model.addAttribute("usuario", muestra);
        model.addAttribute("usuarioActivo", logueado);
        return "index.html";
    }

    @GetMapping("/lista")
    public String listarUsuarios(HttpSession session, Model model) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        List<Usuario> muestra = usuarioServicio.listarUsuarios();
        model.addAttribute("usuarios", muestra);
        model.addAttribute("usuarioActivo", logueado);
        return "usuarios_list";
    }

    @PreAuthorize("hasAnyRole('ADMIN','ENCARGADO','TITULAR','USUARIO')")
    @GetMapping("/perfil/{id}")
    public String perfilUsuario(@PathVariable Long id, HttpSession session, Model model) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        Usuario muestra = usuarioServicio.getOne(id);
        model.addAttribute("usuario", muestra);
        model.addAttribute("usuarioActivo", logueado);
        return "usuario.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar/{id}")
    public String registrar(@PathVariable Long id, ModelMap modelo) {
        try {
            usuarioServicio.eliminar(id);
            modelo.addAttribute("exito", "Se eliminó con éxito el usuario");
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
            return "redirect:/usuario/lista";
        }
        return "redirect:/usuario/lista";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cambiar-rol/{id}")
    public String cambiarRol(@PathVariable Long id, ModelMap modelo) {
        try {
            usuarioServicio.cambiarRol(id);
            modelo.addAttribute("exito", "Se cambió con éxito el rol de usuario");
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
            return "redirect:/usuario/lista";
        }
        return "redirect:/usuario/lista";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, String password2, ModelMap modelo) {

        try {
            usuarioServicio.registrar(nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado correctamente!");

            return "redirect:/";
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

}
