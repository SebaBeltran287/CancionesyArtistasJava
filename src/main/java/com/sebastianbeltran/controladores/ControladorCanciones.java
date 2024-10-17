package com.sebastianbeltran.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sebastianbeltran.modelos.Cancion;
import com.sebastianbeltran.modelos.Artista;
import com.sebastianbeltran.servicios.ServicioCanciones;
import com.sebastianbeltran.servicios.ServicioArtistas;

import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {

    @Autowired
    private ServicioCanciones servicioCanciones;

    @Autowired
    private ServicioArtistas servicioArtistas;

    @GetMapping("/canciones")
    public String desplegarCanciones(Model modelo) {
        modelo.addAttribute("canciones", servicioCanciones.obtenerTodasLasCanciones());
        return "canciones.jsp";
    }

    @GetMapping("/canciones/detalle/{idCancion}")
    public String desplegarDetalleCancion(@PathVariable Long idCancion, Model modelo) {
        modelo.addAttribute("cancion", servicioCanciones.obtenerCancionPorId(idCancion));
        return "detalleCancion.jsp";
    }

    @GetMapping("/canciones/formulario/agregar")
    public String formularioAgregarCancion(Model modelo) {
        modelo.addAttribute("cancion", new Cancion());
        modelo.addAttribute("artistas", servicioArtistas.obtenerTodosLosArtistas());
        return "agregarCancion.jsp";
    }

    @PostMapping("/canciones/procesa/agregar")
    public String procesarAgregarCancion(@Valid @ModelAttribute Cancion cancion,
                                         BindingResult validaciones,
                                         @RequestParam Long artistaId,
                                         Model modelo) {
        if (validaciones.hasErrors()) {
            modelo.addAttribute("artistas", servicioArtistas.obtenerTodosLosArtistas());
            return "agregarCancion.jsp";
        }

        Artista artista = servicioArtistas.obtenerArtistaPorId(artistaId);
        cancion.setArtista(artista);

        this.servicioCanciones.agregarCancion(cancion);
        return "redirect:/canciones";
    }

    @GetMapping("/canciones/formulario/editar/{idCancion}")
    public String muestraFormularioEditarCancion(@PathVariable Long idCancion, Model modelo) {
        Cancion cancionActual = this.servicioCanciones.obtenerCancionPorId(idCancion);
        modelo.addAttribute("cancion", cancionActual);
        modelo.addAttribute("artistas", servicioArtistas.obtenerTodosLosArtistas());
        return "editarCancion.jsp";
    }

    @PutMapping("/canciones/procesa/editar/{idCancion}")
    public String procesarEditarCancion(@PathVariable Long idCancion,
                                        @Valid @ModelAttribute Cancion cancion,
                                        BindingResult validaciones,
                                        @RequestParam Long artistaId,
                                        Model modelo) {
        if (validaciones.hasErrors()) {
            modelo.addAttribute("artistas", servicioArtistas.obtenerTodosLosArtistas());
            return "editarCancion.jsp";
        }

        Artista artista = servicioArtistas.obtenerArtistaPorId(artistaId);
        cancion.setArtista(artista);
        cancion.setId(idCancion);

        servicioCanciones.actualizaCancion(cancion);
        return "redirect:/canciones";
    }

    @DeleteMapping("/canciones/eliminar/{idCancion}")
    public String procesarEliminarCancion(@PathVariable Long idCancion) {
        servicioCanciones.eliminaCancion(idCancion);
        return "redirect:/canciones";
    }
}
