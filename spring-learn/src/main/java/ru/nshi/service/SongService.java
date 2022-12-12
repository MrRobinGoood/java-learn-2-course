package ru.nshi.service;

import ru.nshi.model.Song;
import ru.nshi.model.SongWithId;

import java.util.List;

public interface SongService {
    List<SongWithId> getSongs();

    SongWithId getById(Integer id);

    SongWithId save(Song song);

    SongWithId updateById(Integer id, Song song);

    SongWithId deleteById(Integer id);

    SongWithId doHandleSong(Song song);
}
