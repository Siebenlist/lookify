package com.example.lookify.repositories;

import com.example.lookify.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findAll();
    Iterable<Song> findByArtistaContainingIgnoreCase(String artista);
    List<Song> findTop10ByOrderByClasificacionDesc();
}
