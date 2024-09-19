package com.felipelopez.Biblioteca.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LibroResponseDTO {

    private String tituloLibro;
    private String nombreAutor;
    private LocalDate fechaPublicacion;
}
