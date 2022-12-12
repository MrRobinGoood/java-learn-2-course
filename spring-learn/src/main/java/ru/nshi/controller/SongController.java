package ru.nshi.controller;

import org.springframework.web.bind.annotation.*;
import ru.nshi.model.Song;

import java.util.List;

@RequestMapping(SongController.MAPPING)
public interface SongController {
    String MAPPING = "/song";

    @GetMapping
    List<Song> getSongs();

    @GetMapping("/{id}")
    Song getSongById(@PathVariable Integer id);

    @PostMapping
    Song createSong(@RequestBody Song song);

    @PutMapping("/{id}")
    Song updateSong(@PathVariable Integer id, @RequestBody Song song);

    @DeleteMapping("/{id}")
    Song deleteById(@PathVariable Integer id);
}
