package ru.nshi.error;

public class SongNotFoundException extends SongException {
    public SongNotFoundException(String message) {
        super(message);
    }
}
