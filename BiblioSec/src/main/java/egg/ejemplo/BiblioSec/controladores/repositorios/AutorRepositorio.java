/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package egg.ejemplo.BiblioSec.controladores.repositorios;

import egg.ejemplo.BiblioSec.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
}
