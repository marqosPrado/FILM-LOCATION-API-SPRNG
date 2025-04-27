package br.com.marcosprado.filmlocationapi.domain;

import java.io.Serializable;

public class Location implements Serializable {
    private double[] coordinates;

    public Location(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getLongitude() {
        return String.valueOf(coordinates[0]); // Retorna a longitude
    }

    public String getLatitude() {
        return String.valueOf(coordinates[1]); // Retorna a latitude
    }
}
