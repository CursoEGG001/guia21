/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package egg.ejemplo.BiblioSec.controladores;

import egg.ejemplo.BiblioSec.entidades.Usuario;
import egg.ejemplo.BiblioSec.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo(HttpSession httpSession) {
        Usuario usuario = (Usuario) httpSession.getAttribute("usuariosession");
        
        return "panel.html";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(ModelMap modelo) {

        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);

        return "usuario_list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/usuario/modificar/{id}")
    public String modificarUsuarios(@PathVariable String id, ModelMap modelo) {

        Usuario usuario = usuarioServicio.getOne(id);
        modelo.put("usuario", usuario);
        return "usuario_modificar.html";

    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) {
        usuarioServicio.cambiarRol(id);

        return "redirect:/admin/usuarios";
    }

}
