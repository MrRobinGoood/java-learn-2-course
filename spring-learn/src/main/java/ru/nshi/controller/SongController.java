package ru.nshi.controller;

import org.springframework.web.bind.annotation.*;
import ru.nshi.model.ListenSong;
import ru.nshi.model.ListenSongs;
import ru.nshi.model.Song;
import ru.nshi.model.SongWithId;

import java.util.Comparator;
import java.util.List;

@RequestMapping(SongController.MAPPING)
public interface SongController {
    String MAPPING = "/songs";

    @GetMapping
    List<SongWithId> getSongs();

    @GetMapping("/{id}")
    SongWithId getSongById(@PathVariable Integer id);

    @GetMapping("/listen")
    List<SongWithId> getSortedSongsByAuditions(@RequestParam(defaultValue = "5") Integer limit);

    @PostMapping
    SongWithId createSong(@RequestBody Song song);

    @PostMapping("/{id}/listen")
    SongWithId listenSongById(@PathVariable Integer id, @RequestBody ListenSong song);

    @PutMapping("/{id}")
    SongWithId updateSong(@PathVariable Integer id, @RequestBody Song song);

    @PostMapping("/listen")
    public SongWithId[] listenSongByIds(@RequestBody ListenSongs auditions);

    @DeleteMapping("/{id}")
    SongWithId deleteById(@PathVariable Integer id);
}
