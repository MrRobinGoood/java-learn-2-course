package ru.nshi.model;

import lombok.*;
import ru.nshi.error.SongValidationException;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
public class SongWithId {
    @EqualsAndHashCode.Include
    private Integer id;
    @ToString.Include
    private String artistName;

    @ToString.Include
    private String name;
    private Integer auditions = 0;

    public void listen() {
        auditions++;
    }

    public void listen(Integer auditions) {
        if (auditions <= 0) throw new SongValidationException("auditions must be greater than zero");
        this.auditions += auditions;
    }

    public SongWithId(String artistName, String name, Integer auditions) {
        this.name = name;
        this.artistName = artistName;
        this.auditions = auditions;
    }

    public SongWithId(Integer id, String artistName, String name) {
        this.name = name;
        this.artistName = artistName;
        this.id = id;
    }

    public SongWithId(String artistName, String name) {
        this.name = name;
        this.artistName = artistName;
    }
}
