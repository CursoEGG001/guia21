/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.noticia.eggnews.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import live.egg.noticia.eggnews.entidades.Noticias;
import live.egg.noticia.eggnews.excepciones.MiException;
import live.egg.noticia.eggnews.repositorios.NoticiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class NoticiasServicio {

    @Autowired
    NoticiasRepository noticiasRepository;

    @Transactional
    public void crearNoticia(String titulo, String cuerpo, Date fecha) throws MiException {
        validaDatos(titulo, cuerpo);

        Noticias noticia = new Noticias();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setFecha(fecha);
        noticia.setActivo(Boolean.TRUE);

        noticiasRepository.save(noticia);
    }

    @Transactional
    public List<Noticias> listarNoticias() throws MiException {

        List<Noticias> noticias = new ArrayList<>(noticiasRepository.findAll());

        return noticias;
    }

    @Transactional
    public void modificarNoticia(String Id, String titulo, String cuerpo,Date fecha) throws MiException {
        validaDatos(titulo, cuerpo);

        Optional<Noticias> respuesta = noticiasRepository.findById(Id);

        if (respuesta.isPresent()) {
            Noticias noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setFecha(fecha);
            noticia.setActivo(Boolean.TRUE);

            noticiasRepository.save(noticia);
        }
    }

    public void eliminarNoticia(String Id) throws MiException {
        Optional<Noticias> respuesta = noticiasRepository.findById(Id);

        if (respuesta.isPresent()) {
            Noticias noticia = respuesta.get();

            noticia.setActivo(Boolean.FALSE);

            noticiasRepository.save(noticia);
        }
    }
    
    public Noticias getOne(String id) {
        return noticiasRepository.getReferenceById(id);
    }

    private void validaDatos(String titulo, String noticia) throws MiException {

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo o estar vacío");
        }
        if (noticia.isEmpty() || noticia == null) {
            throw new MiException("la noticia no puede ser nula o estar vacía");
        }
    }
}
