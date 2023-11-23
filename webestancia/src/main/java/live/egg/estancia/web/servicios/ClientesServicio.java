/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Clientes;
import live.egg.estancia.web.excepciones.MiException;
import live.egg.estancia.web.repositorios.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class ClientesServicio {

    @Autowired
    ClientesRepository clienteRepositorio;

    @Transactional
    public void crearCliente(String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, String email) throws MiException {

        valida(nombre, calle, numero, codigoPostal, ciudad, pais, email);
        
        Clientes cliente = new Clientes();

        cliente.setNombre(nombre);
        cliente.setCalle(calle);
        cliente.setNumero(numero);
        cliente.setCodigoPostal(codigoPostal);
        cliente.setCiudad(ciudad);
        cliente.setPais(pais);
        cliente.setEmail(email);
        cliente.setActive(Boolean.TRUE);

        clienteRepositorio.save(cliente);
    }

    @Transactional
    public List<Clientes> listarClientes() {
        return clienteRepositorio.findAll();
    }

    @Transactional
    public void modificarClientes(Long idCliente, String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, String email) throws MiException {

        valida(nombre, calle, numero, codigoPostal, ciudad, pais, email);
        
        Optional<Clientes> clienteAcambiar = clienteRepositorio.findById(idCliente);

        Clientes cliente = new Clientes();

        if (clienteAcambiar.isPresent()) {
            cliente.setNombre(nombre);
            cliente.setCalle(calle);
            cliente.setNumero(numero);
            cliente.setCodigoPostal(codigoPostal);
            cliente.setCiudad(ciudad);
            cliente.setPais(pais);
            cliente.setEmail(email);

            if (clienteAcambiar.get().getActive()) {
                clienteRepositorio.save(cliente);
            }
        }

    }

    @Transactional
    public Clientes getOne(Long idCliente) {
        return clienteRepositorio.getReferenceById(idCliente);
    }

    private void valida(String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, String email) throws MiException {
        if (calle.isEmpty() || calle == null) {
            throw new MiException("la calle no puede ser nula o estar vacía");
        }
        if (numero == 0) {
            throw new MiException("el nuemro no puede ser nulo o estar vacio");
        }
        if (ciudad.isEmpty() || ciudad == null) {
            throw new MiException("La ciudad no puede estar vacía");
        }
        if (pais.isEmpty() || ciudad == null) {
            throw new MiException("El país no puede estar vacío");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacio");
        }
        if (codigoPostal.isEmpty()||codigoPostal == null) {
            throw new MiException("el código postal no puede ser nulo");
        }
        if (email.isEmpty() || email == null || !email.contains("@")) {
            throw new MiException("el correo no puede ser nulo");
        }

    }

}
