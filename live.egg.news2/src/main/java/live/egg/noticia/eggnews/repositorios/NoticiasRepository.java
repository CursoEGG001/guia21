/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package live.egg.noticia.eggnews.repositorios;

import live.egg.noticia.eggnews.entidades.Noticias;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pc
 */
public interface NoticiasRepository extends JpaRepository<Noticias, String> {
    
}
