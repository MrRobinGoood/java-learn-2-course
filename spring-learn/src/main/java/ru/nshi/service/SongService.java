package ru.nshi.service;

import ru.nshi.model.Song;

import java.util.List;

public interface SongService {
    List<Song> getSongs();

    Song getById(Integer id);

    Song save(Song song);

    Song updateById(Integer id, Song song);

    Song deleteById(Integer id);

    Song doHandleSong(Song song);
}
