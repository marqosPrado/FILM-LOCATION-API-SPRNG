package br.com.marcosprado.filmlocationapi.application.dto;

public record ErrorResponseDTO (
    String message,
    int httpStatus
) {
    public static ErrorResponseDTO of(String message, int httpStatus) {
        return new ErrorResponseDTO(message, httpStatus);
    }
}
