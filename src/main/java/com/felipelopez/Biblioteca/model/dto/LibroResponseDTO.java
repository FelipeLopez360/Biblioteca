package com.felipelopez.Biblioteca.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LibroResponseDTO {

    private String libro;
    private String autor;
    private LocalDate fechaPublicacion;
}
