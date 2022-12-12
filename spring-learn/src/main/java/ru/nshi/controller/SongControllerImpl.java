package ru.nshi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.nshi.error.*;
import ru.nshi.model.*;
import ru.nshi.model.Error;
import ru.nshi.service.SongService;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(SongController.MAPPING)
public class SongControllerImpl implements SongController {
    private SongService service;

    @Autowired
    public void setService(SongService service) {
        this.service = service;
    }

    @Override
    public List<SongWithId> getSongs() {
        return service.getSongs();
    }

    @Override
    public SongWithId getSongById(Integer id) {
        checkId(id);
        return service.getById(id);
    }

    @Override
    public SongWithId createSong(Song song) {
        checkSong(song);
        return service.save(song);
    }

    @Override
    public SongWithId listenSongById(Integer id, ListenSong auditions) {
        checkId(id);
        SongWithId songWithId = service.getById(id);
        songWithId.listen(auditions.getAuditions());
        return songWithId;
    }

    @Override
    public List<SongWithId> getSortedSongsByAuditions() {
        List<SongWithId> array = service.getSongs();
        array.sort(new Comparator<SongWithId>() {
            @Override
            public int compare(SongWithId p1, SongWithId p2) {
                return p2.getAuditions() - p1.getAuditions();
            }
        });
        return array;
    }

    @Override
    public SongWithId[] listenSongByIds(ListenSongs auditions) {
        SongWithId[] songWithIds = new SongWithId[auditions.getSongs().length];
        int i = 0;
        for (Integer id : auditions.getSongs()) {
            checkId(id);
            SongWithId songWithId = service.getById(id);
            songWithId.listen(auditions.getAuditions());
            songWithIds[i] = songWithId;
            i++;
        }
        return songWithIds;
    }

    @Override
    public SongWithId updateSong(Integer id, Song song) {
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
        if (song == null || song.getArtistName() == null || song.getName() == null) {
            throw new SongValidationException("song, song author or song name cannot be null");
        }
        if (song.getAuditions() < 0) {
            throw new SongValidationException("song auditions cannot be less than 0");
        }
        String stripAuthor = song.getArtistName().strip();
        String stripName = song.getName().strip();
        if (stripAuthor.isEmpty() || stripName.isEmpty()) {
            throw new SongValidationException("song author or song name cannot be empty");
        }
        song.setArtistName(stripAuthor);
        song.setName(stripName);
    }

    @Override
    public SongWithId deleteById(Integer id) {
        checkId(id);
        return service.deleteById(id);
    }
}
