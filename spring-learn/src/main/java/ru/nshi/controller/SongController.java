package ru.nshi.controller;

import org.springframework.web.bind.annotation.*;
import ru.nshi.model.Listen;
import ru.nshi.model.Song;
import ru.nshi.model.SongWithId;

import java.util.List;

@RequestMapping(SongController.MAPPING)
public interface SongController {
    String MAPPING = "/song";

    @GetMapping
    List<SongWithId> getSongs();

    @GetMapping("/{id}")
    SongWithId getSongById(@PathVariable Integer id);

    @PostMapping
    SongWithId createSong(@RequestBody Song song);

    @GetMapping("/listen/{id}")
    SongWithId listen(@PathVariable Integer id);

    @PostMapping("/listen/{id}")
    SongWithId listen(@PathVariable Integer id, @RequestBody Listen auditions);

    @PutMapping("/{id}")
    SongWithId updateSong(@PathVariable Integer id, @RequestBody Song song);

    @DeleteMapping("/{id}")
    SongWithId deleteById(@PathVariable Integer id);
}
