package ru.nshi.model;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
public class Song {
    @EqualsAndHashCode.Include
    private Integer id;
    @ToString.Include
    private String author;

    @ToString.Include
    private String name;
    private Integer auditions = 0;

    public Song(String name, String author, Integer auditions) {
        this.name = name;
        this.author = author;
        this.auditions = auditions;
    }

    public Song(String name, String author) {
        this.name = name;
        this.author = author;
    }
}
