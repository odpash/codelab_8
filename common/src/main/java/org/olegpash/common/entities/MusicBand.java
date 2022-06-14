package org.olegpash.common.entities;

import org.olegpash.common.entities.enums.MusicGenre;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class MusicBand implements Serializable, Comparable<MusicBand> {
    private static final long serialVersionUID = 0xDEAD;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private java.time.LocalDateTime establishmentDate; //Поле может быть null
    private MusicGenre genre; //Поле не может быть null
    private Album bestAlbum; //Поле может быть null

    /**
     * Конструктор, автоматически выставляющий ID и дату инициализации
     */
    public MusicBand() {
        creationDate = ZonedDateTime.now();
    }

    public MusicBand(Long id) {
        this.id = id;
        creationDate = ZonedDateTime.now();
    }

    public MusicBand(LocalDateTime establishmentDate, Long id, String name, Coordinates coordinates, long numberOfParticipants, String description, MusicGenre genre, Album album) {
        this.creationDate = ZonedDateTime.now();
        this.establishmentDate = establishmentDate;
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.numberOfParticipants = numberOfParticipants;
        this.description = description;
        this.genre = genre;
        this.bestAlbum = album;
    }

    /**
     * Метод, возвращающий ID
     *
     * @return ID объекта
     */
    public Long getId() {
        return id;
    }

    /**
     * Метод, устанавливающий ID по данному
     *
     * @param id Новый ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    /**
     * Метод, устанавливающий имя объекта
     *
     * @param name Новое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод, возвращающий соординаты объекта
     *
     * @return Координаты объекта
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, устанавливающий координаты объекта
     *
     * @param coordinates Новые координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Метод, возвращающий число участников
     *
     * @return Число участников
     */
    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /**
     * Метод, устанавливающий число участников
     *
     * @param numberOfParticipants Новое число участников
     */
    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Метод, устанавливающий описание
     *
     * @param description Новое описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    /**
     * Метод, устанавливающий жанр
     *
     * @param genre Новый жанр
     */
    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    /**
     * Метод, возвращающий студию
     *
     * @return Студия
     */
    public Album getAlbum() {
        return bestAlbum;
    }

    /**
     * Метод, устанавливающий альбом
     *
     * @param album Новый альбом
     */
    public void setAlbum(Album album) {
        this.bestAlbum = album;
    }

    /**
     * Метод сравнения
     *
     * @param anotherBand Группа для сравнения
     * @return Целочисленное значение
     */
    @Override
    public int compareTo(MusicBand anotherBand) {
        if (this.bestAlbum == null) {
            return -1;
        } else if (anotherBand.getAlbum() == null) {
            return 1;
        }
        return this.bestAlbum.compareTo(anotherBand.getAlbum());
    }

    /**
     * Переопределение метода, возвращающего строковое представление класса
     *
     * @return Строковое представление класса
     */
    @Override
    public String toString() {
        return "ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Coordinates: " + coordinates + "\n"
                + "Creation date: " + creationDate + "\n"
                + "Number of participants: " + numberOfParticipants + "\n"
                + "Description: " + ((description == null) ? "missing" : description) + "\n"
                + "Genre: " + ((genre == null) ? "not defined" : genre) + "\n"
                + ((bestAlbum == null) ? "The album is missing" : bestAlbum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return numberOfParticipants == musicBand.numberOfParticipants && Objects.equals(creationDate, musicBand.creationDate) && Objects.equals(id, musicBand.id) && Objects.equals(name, musicBand.name) && Objects.equals(coordinates, musicBand.coordinates) && Objects.equals(description, musicBand.description) && genre == musicBand.genre && Objects.equals(bestAlbum, musicBand.bestAlbum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, id, name, coordinates, numberOfParticipants, description, genre, bestAlbum);
    }
}
