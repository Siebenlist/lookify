package com.example.lookify.services;

import com.example.lookify.models.Song;
import com.example.lookify.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    //Devuelve todos los libros
    public List<Song> allSongs(){
        return songRepository.findAll();
    }

    //Crea una cancion
    public Song createSong(Song s){
        return songRepository.save(s);
    }

    //Busca una cancion por id
    public Song findSong(Long id){
        Optional<Song> optionalSong = songRepository.findById(id);
        return optionalSong.orElse(null);
    }

    public void deleteSong(Long id){
        if(songRepository.existsById(id)){
            songRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("No existe esta id");
        }
    }

    public Iterable<Song> findByArtista(String artista){
        Iterable<Song> listaCanciones = songRepository.findByArtistaContainingIgnoreCase(artista);
        return listaCanciones;
    }

    public List<Song> findByTop10(){
        List<Song> listaCanciones = songRepository.findTop10ByOrderByClasificacionDesc();
        return listaCanciones;
    }
}
