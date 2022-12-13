package ru.nshi.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.nshi.error.SongNotFoundException;
import ru.nshi.model.Song;
import ru.nshi.model.SongWithId;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Repository
public class SongRepositoryImpl implements SongRepository {
    private final Map<Integer, SongWithId> data;
    private final AtomicInteger autoId;

    @Override
    public List<SongWithId> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public SongWithId getById(Integer id) {
        SongWithId result = data.get(id);
        if (result == null) {
            throw new SongNotFoundException("song not found");
        }
        return result;
    }

    @Override
    public SongWithId save(@NonNull Song song) {
        int id = autoId.incrementAndGet();
        SongWithId songWithId = new SongWithId(song.getArtistName(), song.getName(), song.getAuditions());
        songWithId.setId(id);
        data.put(id, songWithId);
        return songWithId;
    }

    @Override
    public SongWithId updateById(Integer id, Song song) {
        getById(id);
        SongWithId songWithId = new SongWithId(song.getArtistName(), song.getName(), song.getAuditions());
        songWithId.setId(id);
        data.put(id, songWithId);
        return songWithId;
    }

    @Override
    public SongWithId deleteById(Integer id) {
        SongWithId result = data.remove(id);
        if (result == null) {
            throw new SongNotFoundException("song not found");
        }
        return result;
    }

    @PostConstruct
    public void setup() {
//        int id = autoId.incrementAndGet();
//        data.put(id, new Message(id, defaultMessage));
        System.out.println("Repository created");
    }

    @PreDestroy
    public void clean() {
        data.clear();
        System.out.println("Repository clean");
    }
}
