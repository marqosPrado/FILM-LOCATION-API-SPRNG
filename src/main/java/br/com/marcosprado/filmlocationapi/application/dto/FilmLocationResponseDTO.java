package br.com.marcosprado.filmlocationapi.application.dto;

import br.com.marcosprado.filmlocationapi.domain.Film;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class FilmLocationResponseDTO implements Serializable {
    @JsonProperty("title")
    private String title;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("production_company")
    private String productionCompany;

    @JsonProperty("director")
    private String director;

    @JsonProperty("distributor")
    private String distributor;

    @JsonProperty("location")
    private String location;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("latitude")
    private String latitude;

    public FilmLocationResponseDTO(
            String title,
            Integer year,
            String productionCompany,
            String director,
            String distributor,
            String location,
            String longitude,
            String latitude
    ) {
        this.title = title;
        this.year = year;
        this.productionCompany = productionCompany;
        this.director = director;
        this.distributor = distributor;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static List<FilmLocationResponseDTO> fromDomain(List<Film> films) {
        return films.stream()
                .map(FilmLocationResponseDTO::fromDomain)
                .collect(Collectors.toList());
    }

    private static FilmLocationResponseDTO fromDomain(Film film) {
        return new FilmLocationResponseDTO(
                film.title(),
                film.year().getValue(),
                film.productionCompany(),
                film.director(),
                film.distributor(),
                film.locations(),
                film.location().getLongitude(),
                film.location().getLatitude()
        );
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer year() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String productionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String director() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String distributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String location() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String longitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String latitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
