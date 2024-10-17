package com.sebastianbeltran.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sebastianbeltran.modelos.Artista;
import com.sebastianbeltran.servicios.ServicioArtistas;

import jakarta.validation.Valid;

@Controller
public class ControladorArtistas {

    @Autowired
    private final ServicioArtistas servicioArtistas;
    
    public ControladorArtistas(ServicioArtistas servicioArtistas) {
        this.servicioArtistas = servicioArtistas;
    }

    @GetMapping("/artistas")
    public String desplegarArtistas(Model modelo) {
        modelo.addAttribute("artistas", servicioArtistas.obtenerTodosLosArtistas());
        return "artistas.jsp";
    }

    @GetMapping("/artistas/detalle/{idArtista}")
    public String desplegarDetalleArtista(@PathVariable Long idArtista, Model modelo) {
        modelo.addAttribute("artista", servicioArtistas.obtenerArtistaPorId(idArtista));
        return "detalleArtista.jsp";
    }

    @GetMapping("/artistas/formulario/agregar")
    public String formularioAgregarArtista(Model modelo) {
        modelo.addAttribute("artista", new Artista());
        return "agregarArtista.jsp";
    }

    @PostMapping("/artistas/procesa/agregar")
    public String procesarAgregarArtista(@Valid @ModelAttribute Artista artista, 
    									BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            return "agregarArtista.jsp";
        }
        servicioArtistas.agregarArtista(artista);
        return "redirect:/artistas";
    }
}
