/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica01.service;

/**
 *
 * @author home
 */

import java.util.List;
import java.util.Optional;
import practica01.practica01.domain.Suculenta;

public interface SuculentaService {

    List<Suculenta> obtenerTodas();

    Optional<Suculenta> buscarPorId(Integer id);

    void registrar(Suculenta suculenta);

    void borrar(Integer id);
}
