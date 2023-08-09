package com.example.lookify.controllers;

import com.example.lookify.models.Song;
import com.example.lookify.repositories.SongRepository;
import com.example.lookify.services.SongService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SongsController {
    private final SongService songService;


    public SongsController(SongService songService){
        this.songService = songService;
    }

    @RequestMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        List<Song> listSongs = songService.allSongs();
        model.addAttribute("listSongs", listSongs);
        return "dashboard";
    }

    //Pagina para añadir una nueva cancion
    @GetMapping("/new")
    public String newSong(Model model){
        model.addAttribute("song", new Song());
        return "new";
    }

    //Metodo POST para obtener la nueva cancion
    @PostMapping("/new")
    public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result){
        if(result.hasErrors()){
            return "new";
        }

        songService.createSong(song);
        return "redirect:/dashboard";
    }

    @PostMapping("/songs/{id}")
    public String deleteSong(@PathVariable("id") Long id){
        songService.deleteSong(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/songs/{id}")
    public String viewSong(@PathVariable("id") Long id, Model model){
        model.addAttribute("song", songService.findSong(id));
        return "view";
    }

    @GetMapping("/search")
    public String buscarPorArtista(@RequestParam("artista") String artista, Model model) {
        Iterable<Song> listaCanciones = songService.findByArtista(artista);
        model.addAttribute("listaCanciones", listaCanciones);
        model.addAttribute("artista", artista);
        return "search";
    }

    @GetMapping("/search/topTen")
    public String mostrarTopTen(Model model) {
        List<Song> topTen = songService.findByTop10();
        model.addAttribute("topTen", topTen);
        return "topTen";
    }
}
