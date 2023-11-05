/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egg.ejemplo.BiblioSec.servicios;

import egg.ejemplo.BiblioSec.controladores.repositorios.AutorRepositorio;
import egg.ejemplo.BiblioSec.entidades.Autor;
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
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException{
        
        validar(nombre);
        
        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }
    
    @Transactional
    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList();

        autores = autorRepositorio.findAll();

        return autores;
    }
    
    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException{
        
        validar(nombre);
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            
            autor.setNombre(nombre);

            autorRepositorio.save(autor);

        }
    }
    
    @Transactional
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }
    
   
    @Transactional
    public void eliminar(String id) throws MiException{
        
        Autor autor = autorRepositorio.getById(id);
        
        autorRepositorio.delete(autor);

    }
    
     private void validar(String nombre) throws MiException {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");
        }
    }
    
}
