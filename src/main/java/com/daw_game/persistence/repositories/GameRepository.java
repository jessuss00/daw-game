package com.daw_game.persistence.repositories;

import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import com.daw_game.persistence.entities.Game;
import com.daw_game.persistence.entities.enumerados.Tipo;

public interface GameRepository extends ListCrudRepository<Game, Integer> {

    // Busca juegos por género 
    List<Game> findByGeneroIgnoreCase(String genero);

    // Busca juegos cuyo nombre contenga el texto proporcionado 
    List<Game> findByNombreContainingIgnoreCase(String nombre);

    // Busca juegos que incluyan la plataforma especificada
    List<Game> findByPlataformasContainingIgnoreCase(String plataforma);

    // Filtra juegos por tipo (rol, pvp, estrategia) usando el enum
    List<Game> findByTipo(Tipo tipo);

    // Busca juegos con precio entre min y max 
    List<Game> findByPrecioBetween(double min, double max);

    // Obtiene juegos con más de X descargas
    List<Game> findByDescargasGreaterThan(long descargas);
}
