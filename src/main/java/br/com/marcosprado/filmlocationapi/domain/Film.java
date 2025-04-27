package br.com.marcosprado.filmlocationapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film implements Serializable {
    private String title;
    private Year releaseYear;
    private String productionCompany;
    private String director;
    private String distributor;
    private String locations;
    private Location location;

    public Film(String title, Year releaseYear, String productionCompany, String director, String distributor, String locations, Location location) {
        this.setTitle(title);
        this.setReleaseYear(releaseYear);
        this.setProductionCompany(productionCompany);
        this.setDirector(director);
        this.setDistributor(distributor);
        this.setLocations(locations);
        this.setLocation(location);
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        Objects.requireNonNull(title, "Title cannot be null");
        this.title = title;
    }

    public Year year() {
        return this.releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        Objects.requireNonNull(releaseYear, "Year cannot be null");
        this.releaseYear = releaseYear;
    }

    public String productionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        Objects.requireNonNull(productionCompany, "Production Company cannot be null");
        this.productionCompany = productionCompany;
    }

    public String director() {
        return director;
    }

    public void setDirector(String director) {
        Objects.requireNonNull(director, "Director cannot be null");
        this.director = director;
    }

    public String distributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        Objects.requireNonNull(distributor, "Distributor cannot be null");
        this.distributor = distributor;
    }

    public Year releaseYear() {
        return releaseYear;
    }

    public String locations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Location location() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
