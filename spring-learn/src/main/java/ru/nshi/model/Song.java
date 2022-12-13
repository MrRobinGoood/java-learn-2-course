package ru.nshi.model;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
public class Song {
    @ToString.Include
    private String artistName;

    @ToString.Include
    private String name;
    private Integer auditions = 0;


    public Song(String artistName, String name, Integer auditions) {
        this.name = name;
        this.artistName = artistName;
        this.auditions = auditions;
    }

    public Song(String artistName, String name) {
        this.name = name;
        this.artistName = artistName;
    }
}
