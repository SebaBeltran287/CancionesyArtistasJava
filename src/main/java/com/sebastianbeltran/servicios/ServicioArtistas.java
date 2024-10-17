package com.sebastianbeltran.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastianbeltran.modelos.Artista;
import com.sebastianbeltran.repositorios.RepositorioArtistas;

@Service
public class ServicioArtistas {
	@Autowired
	private final RepositorioArtistas repositorioArtistas;

    
    public ServicioArtistas(RepositorioArtistas repositorioArtistas) {
        this.repositorioArtistas = repositorioArtistas;
    }

    public List<Artista> obtenerTodosLosArtistas() {
        return repositorioArtistas.findAll();
    }

    public Artista obtenerArtistaPorId(Long id) {
        return repositorioArtistas.findById(id).orElse(null);
    }

    public Artista agregarArtista(Artista artista) {
        return repositorioArtistas.save(artista);
    }
}
