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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practica01.practica01.domain.Suculenta;
import practica01.practica01.repository.SuculentaRepository;

@Service
public class SuculentaServiceImpl implements SuculentaService {

    private final SuculentaRepository suculentaRepository;

    public SuculentaServiceImpl(SuculentaRepository suculentaRepository) {
        this.suculentaRepository = suculentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Suculenta> obtenerTodas() {
        return suculentaRepository.findAllByOrderByNombreComunAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Suculenta> buscarPorId(Integer id) {
        return suculentaRepository.findById(id);
    }

    @Override
    @Transactional
    public void registrar(Suculenta suculenta) {
        suculentaRepository.save(suculenta);
    }

    @Override
    @Transactional
    public void borrar(Integer id) {
        if (!suculentaRepository.existsById(id)) {
            throw new IllegalArgumentException("La suculenta con id " + id + " no existe.");
        }
        suculentaRepository.deleteById(id);
    }
}
