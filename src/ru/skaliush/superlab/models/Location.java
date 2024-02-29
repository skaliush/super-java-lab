package ru.skaliush.superlab.models;

public class Location {
    private float x; //Поле не может быть null
    private float y; //Поле не может быть null
    private float z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле может быть null

    public Location(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(float x, float y, float z, String name) {
        this(x, y, z);
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name=" + (name == null ? "null" : "'" + name + "'") +
                '}';
    }
}
