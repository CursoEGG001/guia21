/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package live.egg.noticia.eggnews.repositorios;

import live.egg.noticia.eggnews.entidades.Periodista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public interface PeriodistaRepositorio extends JpaRepository<Periodista, String> {
    
}
