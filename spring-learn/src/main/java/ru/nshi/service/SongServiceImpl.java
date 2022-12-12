package ru.nshi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nshi.model.Song;
import ru.nshi.model.SongWithId;
import ru.nshi.repository.SongRepository;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository repository;

    @Override
    public List<SongWithId> getSongs() {
        return repository.findAll();
    }

    @Override
    public SongWithId getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public SongWithId save(Song song) {
        return repository.save(song);
    }

    @Override
    public SongWithId updateById(Integer id, Song song) {
        return repository.updateById(id, song);
    }

    @Override
    public SongWithId deleteById(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public SongWithId doHandleSong(Integer id, Song song) {
        return new SongWithId(id, song.getArtistName(), song.getName());
    }
}
