package br.com.marcosprado.filmlocationapi.service;

import br.com.marcosprado.filmlocationapi.application.dto.FilmLocationResponseDTO;
import br.com.marcosprado.filmlocationapi.domain.Film;
import br.com.marcosprado.filmlocationapi.domain.Location;
import br.com.marcosprado.filmlocationapi.domain.exception.NoDataFoundException;
import br.com.marcosprado.filmlocationapi.port.http.HttpService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class FilmLocationService {
    private final HttpService httpService;
    private final ObjectMapper objectMapper;
    private final String BASE_URL = "https://data.sfgov.org/resource/yitu-d5am.json?locations=";

    public FilmLocationService(HttpService httpService, ObjectMapper objectMapper) {
        this.httpService = httpService;
        this.objectMapper = objectMapper;
    }

    public List<FilmLocationResponseDTO> getFilmLocationByUserLocation(String input) {
        String location = input.trim();
        if (location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        try {
            URI uri = buildUriFromLocation(location);
            String response = httpService.get(uri);
            JsonNode jsonNode = parseJson(response);

            if (jsonNode == null || jsonNode.isEmpty()) {
                throw new NoDataFoundException("No data found for the given location");
            }

            List<Film> films = parseApiResponse(jsonNode);
            return FilmLocationResponseDTO.fromDomain(films);
        } catch (URISyntaxException | UnsupportedEncodingException ex) {
            throw new RuntimeException("Erro ao construir a URI para a API");
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao processar resposta da API");
        } catch (Exception ex) {
            throw new RuntimeException("Erro inesperado ao buscar dados da API");
        }
    }

    private URI buildUriFromLocation(String location) throws URISyntaxException, UnsupportedEncodingException {
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
        return new URI(BASE_URL + encodedLocation);
    }

    private JsonNode parseJson(String response) throws IOException {
        return objectMapper.readTree(response);
    }

    private List<Film> parseApiResponse(JsonNode jsonNode) {
        List<Film> films = new ArrayList<>();

        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                JsonNode coordinates = node.path("point").path("coordinates");

                if (coordinates.isArray() && coordinates.size() == 2) {
                    double longitude = coordinates.get(0).asDouble();
                    double latitude = coordinates.get(1).asDouble();

                    films.add(new Film(
                            getJsonTextValue(node, "title"),
                            Year.parse(Objects.requireNonNull(getJsonTextValue(node, "release_year"))),
                            getJsonTextValue(node, "production_company"),
                            getJsonTextValue(node, "director"),
                            getJsonTextValue(node, "distributor"),
                            getJsonTextValue(node, "locations"),
                            new Location(new double[]{longitude, latitude})
                    ));
                }
            }
        }
        return films;
    }


    private String getJsonTextValue(JsonNode node, String... path) {
        JsonNode current = node;
        for (String key : path) {
            if (current == null || !current.has(key)) {
                return null;
            }
            current = current.get(key);
        }
        return current != null ? current.asText() : null;
    }

}
