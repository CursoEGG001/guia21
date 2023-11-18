/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package live.egg.estancia.web.repositorios;

import live.egg.estancia.web.entidades.Estancias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository

public interface EstanciasRepository extends JpaRepository<Estancias, Long> {
    
}
