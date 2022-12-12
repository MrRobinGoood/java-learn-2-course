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
    private String author;

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

    public SongWithId(String name, String author, Integer auditions) {
        this.name = name;
        this.author = author;
        this.auditions = auditions;
    }

    public SongWithId(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
