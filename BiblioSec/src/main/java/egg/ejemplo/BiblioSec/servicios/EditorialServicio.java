/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.ejemplo.BiblioSec.servicios;

import egg.ejemplo.BiblioSec.controladores.repositorios.EditorialRepositorio;
import egg.ejemplo.BiblioSec.entidades.Editorial;
import egg.ejemplo.BiblioSec.excepciones.MiException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */
@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        validar(nombre);

        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    @Transactional
    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }

    @Transactional
    public void modificarEditorial(String id, String nombre) throws MiException {
        validar(nombre);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }
    }

    @Transactional
    public Editorial getOne(String id) {
        return editorialRepositorio.getReferenceById(id);
    }
    
    @Transactional
    public void eliminar(String id) throws MiException {
        Editorial editorial = editorialRepositorio.getReferenceById(id);
        editorialRepositorio.delete(editorial);
    }

    private void validar(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre de la editorial no puede ser nulo o estar vacio");
        }
    }
}
