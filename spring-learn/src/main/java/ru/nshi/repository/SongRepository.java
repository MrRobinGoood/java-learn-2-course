package ru.nshi.repository;

import ru.nshi.model.Song;
import ru.nshi.model.SongWithId;


import java.util.Collections;
import java.util.List;

public interface SongRepository {
    List<SongWithId> findAll();

    SongWithId getById(Integer id);

    SongWithId save(Song message);

    SongWithId updateById(Integer id, Song message);

    SongWithId deleteById(Integer id);
}
