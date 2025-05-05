package com.daw_game.servicies;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw_game.persistence.entities.Game;
import com.daw_game.persistence.entities.enumerados.Tipo;
import com.daw_game.persistence.repositories.GameRepository;

@Service
public class GameServicies {

    @Autowired
    private GameRepository gameRepository;

    // Devuelve la lista de todos los juegos
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    // Busca un juego por su ID
    public Game findById(int id) {
        Optional<Game> gameOpt = gameRepository.findById(id);
        return gameOpt.orElse(null);
    }

    // Crea un nuevo juego
    public Game create(Game game) {
        if (game.getFechaLanzamiento() == null) {
            game.setFechaLanzamiento(LocalDateTime.now());
        }
        return gameRepository.save(game);
    }

    // Modifica un juego existente
    public Game update(int id, Game game) {
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (gameOpt.isPresent() && id == game.getId()) {
            Game gameBD = gameOpt.get();
            game.setCompletado(gameBD.isCompletado());
            return gameRepository.save(game);
        }
        return null;
    }

    // Elimina un juego por su ID
    public boolean delete(int id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Marca un juego como completado
    public Game marcarCompletado(int id) {
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            game.setCompletado(true);
            return gameRepository.save(game);
        }
        return null;
    }

    // Desmarca un juego como completado
    public Game desmarcarCompletado(int id) {
        Optional<Game> gameOpt = gameRepository.findById(id);
        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            game.setCompletado(false);
            return gameRepository.save(game);
        }
        return null;
    }

    // Busca juegos por género
    public List<Game> buscarPorGenero(String genero) {
        return gameRepository.findByGeneroIgnoreCase(genero);
    }

    // Busca juegos cuyo nombre contiene el texto dado
    public List<Game> buscarPorNombre(String nombre) {
        return gameRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Busca juegos que incluyen la plataforma indicada
    public List<Game> buscarPorPlataforma(String plataforma) {
        return gameRepository.findByPlataformasContainingIgnoreCase(plataforma);
    }

    // Busca juegos por tipo (rol, pvp, estrategia)
    public List<Game> buscarPorTipo(Tipo tipo) {
        return gameRepository.findByTipo(tipo);
    }

    // Busca juegos cuyo precio está entre min y max
    public List<Game> buscarPorPrecio(double min, double max) {
        return gameRepository.findByPrecioBetween(min, max);
    }

    // Devuelve los juegos que tienen más de 1000 descargas
    public List<Game> juegosMasDeMilDescargas() {
        return gameRepository.findByDescargasGreaterThan(1000L);
    }
}
