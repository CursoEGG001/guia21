/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.entidades.Comentarios;
import live.egg.estancia.web.repositorios.CasasRepository;
import live.egg.estancia.web.repositorios.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class ComentariosServicio {

    @Autowired
    ComentariosRepository comentarioRepositorio;

    @Autowired
    CasasRepository casaRepositorio;

    @Transactional
    public void crearComentario(String comenta, Casas idCasa) {

        Comentarios comentario = new Comentarios();

        comentario.setComentario(comenta);
        comentario.setIdCasa(idCasa);
        comentario.setActive(Boolean.TRUE);

        comentarioRepositorio.save(comentario);

    }

    @Transactional
    public List<Comentarios> listarComentarios() {
        return comentarioRepositorio.findAll();
    }

    @Transactional
    public void modificarComentario(Long idComentario, String comenta, Casas idCasa) {
        Optional<Comentarios> cmAcambiar = comentarioRepositorio.findById(idComentario);

        Comentarios cm = new Comentarios();

        cm.setComentario(comenta);
        cm.setIdCasa(idCasa);

        if (cmAcambiar.get().getActive()) {

            comentarioRepositorio.save(cm);

        }

    }

    @Transactional
    public Comentarios getOne(Long idComentario) {
        return comentarioRepositorio.getReferenceById(idComentario);
    }

}
