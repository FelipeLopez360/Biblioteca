package com.felipelopez.Biblioteca.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LibroRequestDTO {

    public Long idLibro;
    @NotBlank(message = "El nombre del Libro no puede estar vacio")
    private String tituloLibro;
    @NotBlank(message = "El nombre del Autor no puede estar vacio")
    private String nombreAutor;
    @NotBlank(message = "La Fecha no puede estar vacia")
    private LocalDate fechaPublicacion;
}
