/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package live.egg.estancia.web.controladores;

import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Clientes;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.servicios.ClientesServicio;
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
@RequestMapping("/clientes")
public class ClientesControlador {

    @Autowired
    ClientesServicio clienteServicio;

    @GetMapping("/lista")
    public String listaClientes(ModelMap model) {

        List<Clientes> listado = clienteServicio.listarClientes();
        model.addAttribute("clientes", listado);
        return "clientes_lista";
    }

    @GetMapping("/registrar")
    public String registraClientes(ModelMap modelo) {
        Clientes cliente = null;
        modelo.addAttribute("cliente", cliente);
        return "cliente_form";
    }

    @GetMapping("/modificar/{id}")
    public String modificaClientes(@PathVariable Long id, ModelMap modelo) {
        Clientes cliente = clienteServicio.getOne(id);

        modelo.addAttribute("cliente", cliente);

        String nombre = cliente.getNombre();
        String calle = cliente.getCalle();
        Integer numero = cliente.getNumero();
        String codigoPostal = cliente.getCodigoPostal();
        String ciudad = cliente.getCiudad();
        String pais = cliente.getPais();
        String email = cliente.getEmail();

        modelo.put("nombre", nombre);
        modelo.put("calle", calle);
        modelo.put("numero", numero);
        modelo.put("codigoPostal", codigoPostal);
        modelo.put("ciudad", ciudad);
        modelo.put("pais", pais);
        modelo.put("email", email);
        return "cliente_form";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long id, @RequestParam String nombre, @RequestParam String email, @RequestParam String pais, @RequestParam String ciudad, @RequestParam String calle, @RequestParam Integer numero, @RequestParam String codigoPostal, ModelMap modelo) {

        System.out.println("(registrando cliente) id :" + id);
        try {
            if (id != null) {

                clienteServicio.modificarClientes(id, nombre, calle, numero, codigoPostal, ciudad, pais, email);
            } else {
                clienteServicio.crearCliente(nombre, calle, numero, codigoPostal, ciudad, pais, email);
            }

            modelo.put("exito", "Usuario registrado correctamente!");

            return "redirect:/";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("calle", calle);
            modelo.put("numero", numero);
            modelo.put("codigoPostal", codigoPostal);
            modelo.put("ciudad", ciudad);
            modelo.put("pais", pais);
            modelo.put("email", email);

            return "cliente_form.html";
        }

    }
}
