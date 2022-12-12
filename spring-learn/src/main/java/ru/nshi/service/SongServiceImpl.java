package ru.nshi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nshi.model.Song;
import ru.nshi.repository.SongRepository;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    public SongServiceImpl() {
    }

    @Autowired
    private SongRepository repository;

    @Override
    public List<Song> getSongs() {
        return repository.findAll();
    }

    @Override
    public Song getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Song save(Song song) {
        return repository.save(song);
    }

    @Override
    public Song updateById(Integer id, Song song) {
        return repository.updateById(id, song);
    }

    @Override
    public Song deleteById(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public Song doHandleSong(Song song) {
        song.setAuthor(song.getAuthor());
        song.setName(song.getName());
        return song;
    }
}
