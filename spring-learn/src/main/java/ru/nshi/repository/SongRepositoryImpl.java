package ru.nshi.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.nshi.error.SongNotFoundException;
import ru.nshi.model.Song;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Repository
public class SongRepositoryImpl implements SongRepository {
    private final Map<Integer, Song> data;
    private final AtomicInteger autoId;


    @Override
    public List<Song> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Song getById(Integer id) {
        Song result = data.get(id);
        if (result == null) {
            throw new SongNotFoundException("song not found");
        }
        return result;
    }

    @Override
    public Song save(@NonNull Song song) {
        int id = autoId.incrementAndGet();
        song.setId(id);
        data.put(id, song);
        return song;
    }

    @Override
    public Song updateById(Integer id, Song song) {
        Song oldValue = getById(id);
        song.setId(id);
        data.put(id, song);
        return song;
    }

    @Override
    public Song deleteById(Integer id) {
        Song result = data.remove(id);
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
