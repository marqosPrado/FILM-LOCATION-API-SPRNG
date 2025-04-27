package br.com.marcosprado.filmlocationapi.application.controller;

import br.com.marcosprado.filmlocationapi.application.dto.FilmLocationResponseDTO;
import br.com.marcosprado.filmlocationapi.service.FilmLocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/film")
public class FilmLocationController {
    private final FilmLocationService filmLocationService;

    public FilmLocationController(FilmLocationService filmLocationService) {
        this.filmLocationService = filmLocationService;
    }

    @GetMapping()
    public ResponseEntity<List<FilmLocationResponseDTO>> getFilmByUserLocation(
            @RequestParam("location") String location
    ) {
        List<FilmLocationResponseDTO> response = filmLocationService.getFilmLocationByUserLocation(location);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
