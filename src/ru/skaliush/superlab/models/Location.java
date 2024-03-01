package ru.skaliush.superlab.models;

public class Location {
    private Float x;
    private Float y;
    private Float z;
    private String name;

    public Location(Float x, Float y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(Float x, Float y, Float z, String name) {
        this(x, y, z);
        this.name = name;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setZ(Float z) {
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
