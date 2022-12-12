package ru.nshi.repository;

import ru.nshi.model.Song;


import java.util.List;

public interface SongRepository {
    List<Song> findAll();

    Song getById(Integer id);

    Song save(Song message);

    Song updateById(Integer id, Song message);

    Song deleteById(Integer id);
}
