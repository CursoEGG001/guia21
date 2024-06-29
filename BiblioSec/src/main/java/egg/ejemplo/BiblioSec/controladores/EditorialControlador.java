/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package egg.ejemplo.BiblioSec.controladores;

import egg.ejemplo.BiblioSec.entidades.Editorial;
import egg.ejemplo.BiblioSec.excepciones.MiException;
import egg.ejemplo.BiblioSec.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }

    @GetMapping("/lista") //localhost:8080/editorial/lista
    public String listarEditoriales(ModelMap modelo) {

        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.put("editoriales", editoriales);

        return "editorial_lista.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificaEditorial(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", editorialServicio.getOne(id));

        return ("editorial_modificar.html");
    }

    @PostMapping("/modificar/{id}")
    public String modificarEditorial(@PathVariable String id, String nombre, ModelMap modelo) {
        try {
            editorialServicio.modificarEditorial(id, nombre);

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "editorial_modificar.html";
        }
        return "redirect:/editorial/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminaEditorial(@PathVariable String id, ModelMap modelo) throws MiException {
        try {
            editorialServicio.eliminar(id);
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "redirect:/editorial/lista";

        }
        return "redirect:/editorial/lista";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) {

        try {
            editorialServicio.crearEditorial(nombre);

            modelo.put("exito", "La Editorial fue registrada correctamente!");
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }

        return "index.html";
    }
}
