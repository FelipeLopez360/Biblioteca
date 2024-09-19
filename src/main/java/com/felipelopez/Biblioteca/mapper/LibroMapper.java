package com.felipelopez.Biblioteca.mapper;

import com.felipelopez.Biblioteca.model.dto.LibroRequestDTO;
import com.felipelopez.Biblioteca.model.dto.LibroResponseDTO;
import com.felipelopez.Biblioteca.model.entity.Libro;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LibroMapper {

    private final ModelMapper modelMapper;

    public Libro convertToEntity(LibroRequestDTO libroRequestDTO) {
        return modelMapper.map(libroRequestDTO, Libro.class);
    }

    public LibroResponseDTO convertToDTO(Libro libro) {
        return modelMapper.map(libro, LibroResponseDTO.class);
    }

    public List<LibroResponseDTO> convertToListDTO(List<Libro> libros) {
        return libros.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
