package ru.skaliush.superlab.common.models.dto;

import ru.skaliush.superlab.common.models.Color;
import ru.skaliush.superlab.common.models.Country;
import ru.skaliush.superlab.common.models.Location;

import java.io.Serializable;

public class PersonDTO implements Serializable {
    private Long id;

    private String name;
    private Integer height;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;
    private Location location;

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "PersonForm{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
