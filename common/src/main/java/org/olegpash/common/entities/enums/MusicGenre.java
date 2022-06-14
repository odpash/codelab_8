package org.olegpash.common.entities.enums;

/**
 * ENUM, хранящий возможные жанры музыки
 */
public enum MusicGenre {
    PSYCHEDELIC_CLOUD_RAP,
    POP,
    POST_PUNK;

    /**
     * Метод, возвращающий строковое предстваление класса
     *
     * @return Строковое представление класса
     */
    public static String show() {
        StringBuilder sb = new StringBuilder();
        for (MusicGenre j : values()) {
            sb.append(j);
            sb.append("\n");
        }
        return sb.toString();
    }
}
