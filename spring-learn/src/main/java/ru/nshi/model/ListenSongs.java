package ru.nshi.model;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
public class ListenSongs {
    private Integer auditions;
    private Integer[] songs;
}
