package com.felipelopez.Biblioteca.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LibroRequestDTO {

    @NotBlank(message = "El nombre del Libro no puede estar vacio")
    private String libro;
    @NotBlank(message = "El nombre del Autor no puede estar vacio")
    private String autor;
    @NotBlank(message = "La Fecha no puede estar vacia")
    private LocalDate fechaPublicacion;
}
