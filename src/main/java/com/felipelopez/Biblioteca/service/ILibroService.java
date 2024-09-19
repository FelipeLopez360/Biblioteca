package com.felipelopez.Biblioteca.service;

import com.felipelopez.Biblioteca.model.dto.LibroResponseDTO;
import com.felipelopez.Biblioteca.model.entity.Libro;

import java.util.List;

public interface ILibroService {

    public List<LibroResponseDTO> obtenerTodosLosLibros();

    public Libro crearLibro(Libro libro);

    public LibroResponseDTO obtenerLibroPorId(Long idLibro);

    void eliminarLibro(Long idLibro);

    public Libro actualizarLibro(Long idLibro, Libro libroActualizado);

}