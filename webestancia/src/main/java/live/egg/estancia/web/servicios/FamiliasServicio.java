/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Familias;
import live.egg.estancia.web.repositorios.FamiliasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class FamiliasServicio {

    @Autowired
    FamiliasRepository familiasRepositorio;

    @Transactional
    public void crearFamilia(Integer idFamilia, String nombre, int edadMinima, int edadMaxima, int numHijos, String email) {

        Familias familia = new Familias();

        familia.setNombre(nombre);
        familia.setEdadMinima(edadMinima);
        familia.setEdadMaxima(edadMaxima);
        familia.setNumHijos(numHijos);
        familia.setEmail(email);
        familia.setActive(Boolean.TRUE);

        familiasRepositorio.save(familia);
    }

    @Transactional
    public List<Familias> listarFamilias() {
        return familiasRepositorio.findAll();
    }

    @Transactional
    public void modificarFamilia(Long idFamilia, String nombre, int edadMinima, int edadMaxima, int numHijos, String email) {

        Optional<Familias> fmAcambiar = familiasRepositorio.findById(idFamilia);

        Familias familia = new Familias();

        if (fmAcambiar.isPresent()) {

            familia.setIdFamilia(fmAcambiar.get().getIdFamilia());
            familia.setNombre(nombre);
            familia.setEmail(email);
            familia.setEdadMinima(edadMinima);
            familia.setEdadMaxima(edadMaxima);
            familia.setNumHijos(numHijos);

            if (fmAcambiar.get().getActive()) {

                familiasRepositorio.save(familia);
            }

        }

    }

    @Transactional
    public Familias getOne(Long idFamilia) {
        return familiasRepositorio.getReferenceById(idFamilia);

    }
}
