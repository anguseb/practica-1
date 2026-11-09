/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica01.controllers;

/**
 *
 * @author home
 */

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practica01.practica01.domain.Suculenta;
import practica01.practica01.service.SuculentaService;

@Controller
@RequestMapping("/suculentas")
public class SuculentaController {

    private final SuculentaService suculentaService;
    private final MessageSource messageSource;

    public SuculentaController(SuculentaService suculentaService, MessageSource messageSource) {
        this.suculentaService = suculentaService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String mostrarLista(Model model) {
        var suculentas = suculentaService.obtenerTodas();
        model.addAttribute("suculentas", suculentas);
        model.addAttribute("totalSuculentas", suculentas.size());
        model.addAttribute("suculenta", new Suculenta());
        model.addAttribute("paginaActiva", "suculentas");
        return "suculentas/lista";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEdicion(@PathVariable("id") Integer id, Model model, RedirectAttributes atributos) {
        Optional<Suculenta> suculentaEncontrada = suculentaService.buscarPorId(id);
        if (suculentaEncontrada.isEmpty()) {
            atributos.addFlashAttribute("mensajeError", obtenerMensaje("suculenta.error.noexiste"));
            return "redirect:/suculentas";
        }
        model.addAttribute("suculenta", suculentaEncontrada.get());
        model.addAttribute("paginaActiva", "suculentas");
        return "suculentas/editar";
    }

    @PostMapping("/registrar")
    public String registrarSuculenta(@Valid Suculenta suculenta, BindingResult resultado,
            RedirectAttributes atributos) {
        if (resultado.hasErrors()) {
            atributos.addFlashAttribute("mensajeError", obtenerMensaje("suculenta.error.datos"));
            return suculenta.getIdSuculenta() == null
                    ? "redirect:/suculentas"
                    : "redirect:/suculentas/editar/" + suculenta.getIdSuculenta();
        }
        boolean esNueva = suculenta.getIdSuculenta() == null;
        suculentaService.registrar(suculenta);
        atributos.addFlashAttribute("mensajeExito",
                obtenerMensaje(esNueva ? "suculenta.agregada" : "suculenta.modificada"));
        return "redirect:/suculentas";
    }

    @PostMapping("/borrar")
    public String borrarSuculenta(@RequestParam("id") Integer id, RedirectAttributes atributos) {
        String claveAtributo = "mensajeExito";
        String claveMensaje = "suculenta.eliminada";
        try {
            suculentaService.borrar(id);
        } catch (IllegalArgumentException e) {
            claveAtributo = "mensajeError";
            claveMensaje = "suculenta.error.noexiste";
        } catch (Exception e) {
            claveAtributo = "mensajeError";
            claveMensaje = "suculenta.error.general";
        }
        atributos.addFlashAttribute(claveAtributo, obtenerMensaje(claveMensaje));
        return "redirect:/suculentas";
    }

    private String obtenerMensaje(String clave) {
        return messageSource.getMessage(clave, null, LocaleContextHolder.getLocale());
    }
}
