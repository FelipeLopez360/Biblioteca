package com.felipelopez.Biblioteca.service.impl;

import com.felipelopez.Biblioteca.mapper.LibroMapper;
import com.felipelopez.Biblioteca.model.dto.LibroResponseDTO;
import com.felipelopez.Biblioteca.model.entity.Libro;
import com.felipelopez.Biblioteca.exception.BadRequestException;
import com.felipelopez.Biblioteca.exception.ResourceNotFoundException;
import com.felipelopez.Biblioteca.repository.ILibroRepository;
import com.felipelopez.Biblioteca.service.ILibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements ILibroService {

    private static final String LIBRO_NO_ENCONTRADO_MSG = "Libro no encontrado con el id: ";

    private final ILibroRepository iLibroRepository;
    private final LibroMapper libroMapper;


    @Transactional(readOnly = true)
    @Override
    public List<LibroResponseDTO> obtenerTodosLosLibros() {
        List<Libro> libros = iLibroRepository.findAll();
        if (libros.isEmpty()) {
            throw new ResourceNotFoundException("No hay libros en la base de datos");
        }
        return libroMapper.convertToListDTO(libros);
    }

    @Transactional(readOnly = true)
    @Override
    public Libro obtenerLibroPorId(Long idLibro) {
        return iLibroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException(LIBRO_NO_ENCONTRADO_MSG + idLibro));
    }

    @Transactional
    @Override
    public Libro crearLibro(Libro libro) {
        if(libro.getTituloLibro().equals("") || libro.getTituloLibro() == null) {
            throw new BadRequestException("El titulo del libro no puede estar vacio");
        }
        return iLibroRepository.save(libro);
    }

    @Transactional
    @Override
    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        if (libroActualizado.getTituloLibro().equals("") || libroActualizado.getTituloLibro() == null) {
            throw new BadRequestException("El titulo del libro no puede estar vacio");
        }
        Libro libroExistente = iLibroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(LIBRO_NO_ENCONTRADO_MSG  + id));
        libroExistente.setTituloLibro(libroActualizado.getTituloLibro());
        return iLibroRepository.save(libroExistente);
    }

    @Transactional
    @Override
    public void eliminarLibro(Long idLibro) {
        Libro libro = iLibroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException(LIBRO_NO_ENCONTRADO_MSG + idLibro));
        iLibroRepository.delete(libro);
    }
}