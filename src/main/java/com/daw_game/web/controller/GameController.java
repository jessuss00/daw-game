package com.daw_game.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daw_game.persistence.entities.Game;
import com.daw_game.persistence.entities.enumerados.Tipo;
import com.daw_game.servicies.GameServicies;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameServicies gameServicies;

    // Obtener todos los juegos
    @GetMapping
    public List<Game> findAll() {
        return gameServicies.findAll();
    }

    // Obtener un juego por ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> findById(@PathVariable int id) {
        Game game = gameServicies.findById(id);
        if (game != null) {
            return ResponseEntity.ok(game);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Crear un juego
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        Game created = gameServicies.create(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Modificar un juego
    @PutMapping("/{id}")
    public ResponseEntity<Game> update(@PathVariable int id, @RequestBody Game game) {
        Game updated = gameServicies.update(id, game);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Borrar un juego
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = gameServicies.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Marcar como completado
    @PutMapping("/{id}/completar")
    public ResponseEntity<Game> marcarCompletado(@PathVariable int id) {
        Game game = gameServicies.marcarCompletado(id);
        if (game != null) {
            return ResponseEntity.ok(game);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Desmarcar como completado
    @PutMapping("/{id}/descompletar")
    public ResponseEntity<Game> desmarcarCompletado(@PathVariable int id) {
        Game game = gameServicies.desmarcarCompletado(id);
        if (game != null) {
            return ResponseEntity.ok(game);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Buscar juegos por género
    @GetMapping("/genero/{genero}")
    public List<Game> buscarPorGenero(@PathVariable String genero) {
        return gameServicies.buscarPorGenero(genero);
    }

    // Buscar juegos por nombre
    @GetMapping("/buscarNombre/{nombre}")
    public List<Game> buscarPorNombre(@PathVariable String nombre) {
        return gameServicies.buscarPorNombre(nombre);
    }

    // Buscar juegos por plataforma
    @GetMapping("/buscarPlataforma/{plataforma}")
    public List<Game> buscarPorPlataforma(@PathVariable String plataforma) {
        return gameServicies.buscarPorPlataforma(plataforma);
    }

    // Obtener juegos por tipo (rol, pvp, estrategia)
    @GetMapping("/tipo/{tipo}")
    public List<Game> buscarPorTipo(@PathVariable Tipo tipo) {
        return gameServicies.buscarPorTipo(tipo);
    }

    // Buscar juegos en un rango de precios
    @GetMapping("/precio/{min}/{max}")
    public List<Game> buscarPorPrecio(@PathVariable double min, @PathVariable double max) {
        return gameServicies.buscarPorPrecio(min, max);
    }

    // Juegos con más de 1000 descargas
    @GetMapping("/populares")
    public List<Game> juegosMasDeMilDescargas() {
        return gameServicies.juegosMasDeMilDescargas();
    }
}
