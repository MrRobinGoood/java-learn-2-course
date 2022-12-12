package ru.nshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.error.*;
import ru.nshi.model.Error;
import ru.nshi.model.Song;
import ru.nshi.service.SongService;
import ru.nshi.service.SongServiceImpl;

import java.util.List;

@RestController
@RequestMapping(SongController.MAPPING)
public class SongControllerImpl implements SongController {
    private SongService service = new SongServiceImpl();

    @Autowired
    public void setService(SongService service) {
        this.service = service;
    }

    @Override
    public List<Song> getSongs() {
        return service.getSongs();
    }

    @Override
    public Song getSongById(Integer id) {
        checkId(id);
        return service.getById(id);
    }

    @Override
    public Song createSong(Song song) {
        checkSong(song);
        return service.save(song);
    }

    @Override
    public Song updateSong(Integer id, Song song) {
        checkId(id);
        checkSong(song);
        return service.updateById(id, song);
    }

    void checkId(Integer id) {
        if (id == null) {
            throw new SongValidationException("song id cannot by null");
        }
        if (id < 1) {
            throw new SongValidationException("song id cannot be less than 1");
        }
    }

    @ExceptionHandler(SongValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleValidationException(SongValidationException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(SongNotFoundException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(SongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleNotFoundException(SongException ex) {
        return new Error(ex.getMessage());
    }

    void checkSong(Song song) {
        if (song == null || song.getAuthor() == null || song.getName() == null) {
            throw new SongValidationException("song, song author or song name cannot be null");
        }
        if (song.getAuditions() < 0) {
            throw new SongValidationException("song auditions cannot be less than 0");
        }
        String stripAuthor = song.getAuthor().strip();
        String stripName = song.getName().strip();
        if (stripAuthor.isEmpty() || stripName.isEmpty()) {
            throw new SongValidationException("song author or song name cannot be empty");
        }
        song.setAuthor(stripAuthor);
        song.setName(stripName);
    }

    @Override
    public Song deleteById(Integer id) {
        checkId(id);
        return service.deleteById(id);
    }
}
