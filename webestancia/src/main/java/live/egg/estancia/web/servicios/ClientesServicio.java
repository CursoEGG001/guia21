/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package live.egg.estancia.web.servicios;

import java.util.List;
import java.util.Optional;
import live.egg.estancia.web.entidades.Clientes;
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
    public void crearCliente(String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, String email) {

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
    public void modificarClientes(Long idCliente, String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, String email) {

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
    public Clientes getOne(Long idCliente){
        return clienteRepositorio.getReferenceById(idCliente);
    }
    
    
    
}
