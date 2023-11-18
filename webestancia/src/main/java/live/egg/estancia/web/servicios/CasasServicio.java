/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Casas;
import live.egg.estancia.web.repositorios.CasasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class CasasServicio {

    @Autowired
    CasasRepository casaRepositorio;

    @Transactional
    public void crearCasa(String calle, Integer numero, String ciudad, String codigoPostal, String pais, Date fechaDesde, Date fechaHasta, Integer tiempoMinimo, Integer tiempoMaximo, BigDecimal precioHabitacion, String tipoVivienda) {

        Casas casa = new Casas();

        casa.setCalle(calle);
        casa.setNumero(numero);
        casa.setCodigoPostal(codigoPostal);
        casa.setCiudad(ciudad);
        casa.setCodigoPostal(codigoPostal);
        casa.setFechaDesde(fechaDesde);
        casa.setFechaHasta(fechaHasta);
        casa.setPais(pais);
        casa.setTiempoMaximo(tiempoMaximo);
        casa.setTiempoMinimo(tiempoMinimo);
        casa.setPrecioHabitacion(precioHabitacion);
        casa.setTipoVivienda(tipoVivienda);
        casa.setActive(Boolean.TRUE);

        casaRepositorio.save(casa);
    }

    @Transactional
    public List<Casas> listarCasas() {
        return casaRepositorio.findAll();
    }

    @Transactional
    public void modificarCasa(Long idCasa, String calle, Integer numero, String ciudad, String codigoPostal, String pais, Date fechaDesde, Date fechaHasta, Integer tiempoMinimo, Integer tiempoMaximo, BigDecimal precioHabitacion, String tipoVivienda) {

        Optional<Casas> casaAcambiar = casaRepositorio.findById(idCasa);

        Casas casa = new Casas();

        if (casaAcambiar.isPresent()) {
            casa.setCalle(calle);
            casa.setCalle(calle);
            casa.setNumero(numero);
            casa.setCiudad(ciudad);
            casa.setCodigoPostal(codigoPostal);
            casa.setPais(pais);
            casa.setFechaDesde(fechaDesde);
            casa.setFechaHasta(fechaHasta);
            casa.setPrecioHabitacion(precioHabitacion);
            casa.setTiempoMaximo(tiempoMaximo);
            casa.setTiempoMinimo(tiempoMinimo);
            casa.setTipoVivienda(tipoVivienda);

            if (casaAcambiar.get().getActive()) {
                casaRepositorio.save(casa);
            }

        }

    }
    
    @Transactional
    public Casas getOne(Long idCasa){
        return casaRepositorio.getReferenceById(idCasa);
    }
    
}
