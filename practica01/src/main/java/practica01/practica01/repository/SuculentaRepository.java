/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica01.repository;

/**
 *
 * @author home
 */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import practica01.practica01.domain.Suculenta;

public interface SuculentaRepository extends JpaRepository<Suculenta, Integer> {

    List<Suculenta> findAllByOrderByNombreComunAsc();
}
