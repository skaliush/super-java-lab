package ru.skaliush.superlab.models;

import ru.skaliush.superlab.dto.PersonDTO;

import java.time.ZonedDateTime;

public class Person implements Comparable<Person> {
    private final Long id; // Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name;
    private final ZonedDateTime creationDate; // Значение этого поля должно генерироваться автоматически
    private final Integer height;
    private final Color eyeColor;
    private final Color hairColor;
    private final Country nationality;
    private final Location location;

    public Person(Long id, PersonDTO form) {
        this.id = id;
        this.creationDate = ZonedDateTime.now();
        this.name = form.getName();
        this.height = form.getHeight();
        this.eyeColor = form.getEyeColor();
        this.hairColor = form.getHairColor();
        this.nationality = form.getNationality();
        this.location = form.getLocation();
    }

    public int compareTo(Person o) {
        return height.compareTo(o.height);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getHeight() {
        return height;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", height=" + height +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }
}

