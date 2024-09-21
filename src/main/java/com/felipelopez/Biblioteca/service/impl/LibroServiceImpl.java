package com.felipelopez.Biblioteca.service.impl;

import com.felipelopez.Biblioteca.mapper.LibroMapper;
import com.felipelopez.Biblioteca.model.dto.LibroRequestDTO;
import com.felipelopez.Biblioteca.model.dto.LibroResponseDTO;
import com.felipelopez.Biblioteca.model.entity.Autor;
import com.felipelopez.Biblioteca.model.entity.FechaPublicacion;
import com.felipelopez.Biblioteca.model.entity.Libro;
import com.felipelopez.Biblioteca.exception.BadRequestException;
import com.felipelopez.Biblioteca.exception.ResourceNotFoundException;
import com.felipelopez.Biblioteca.repository.IAutorRepository;
import com.felipelopez.Biblioteca.repository.IFechaPublicacionRepository;
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
    private final IAutorRepository iAutorRepository;
    private final IFechaPublicacionRepository iFechaPublicacionRepository;
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
    public LibroResponseDTO obtenerLibroPorId(Long idLibro) {
        Libro libro = iLibroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException(LIBRO_NO_ENCONTRADO_MSG + idLibro));
        return libroMapper.convertToDTO(libro);
    }

    @Transactional
    @Override
    public LibroResponseDTO crearLibro(LibroRequestDTO libroRequestDTO) {
        if (libroRequestDTO.getTituloLibro() == null || libroRequestDTO.getTituloLibro().isEmpty()) {
            throw new BadRequestException("El titulo del libro no puede estar vacio");
        }
        if (libroRequestDTO.getNombreAutor() == null || libroRequestDTO.getNombreAutor().isEmpty()) {
            throw new BadRequestException("El nombre del autor no puede estar vacio");
        }
        if (libroRequestDTO.getFechaPublicacion() == null) {
            throw new BadRequestException("La fecha de publicacion no puede estar vacia");
        }

        Autor autor = new Autor();
        autor.setNombreAutor(libroRequestDTO.getNombreAutor());
        autor = iAutorRepository.save(autor);

        FechaPublicacion fechaPublicacion = new FechaPublicacion();
        fechaPublicacion.setFecha(libroRequestDTO.getFechaPublicacion());
        fechaPublicacion = iFechaPublicacionRepository.save(fechaPublicacion);

        Libro libro = new Libro();
        libro.setTituloLibro(libroRequestDTO.getTituloLibro());
        libro.setAutor(autor);
        libro.setFechaPublicacion(fechaPublicacion);

        libro = iLibroRepository.save(libro);
        return libroMapper.convertToDTO(libro);
    }

    @Transactional
    @Override
    public LibroResponseDTO actualizarLibro(Long idLibro, LibroRequestDTO libroRequestDTO) {
        if (libroRequestDTO.getTituloLibro() == null  || libroRequestDTO.getTituloLibro().isEmpty()) {
            throw new BadRequestException("El titulo del libro no puede estar vacio");
        }
        if (libroRequestDTO.getNombreAutor() == null || libroRequestDTO.getNombreAutor().isEmpty()) {
            throw new BadRequestException("El nombre del autor no puede estar vacio");
        }
        if (libroRequestDTO.getFechaPublicacion() == null) {
            throw new BadRequestException("La fecha de publicacion no puede estar vacia");
        }

        Libro libroExistente = iLibroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException(LIBRO_NO_ENCONTRADO_MSG + idLibro));

        Autor autor = new Autor();
        autor.setNombreAutor(libroRequestDTO.getNombreAutor());
        autor = iAutorRepository.save(autor);

        FechaPublicacion fechaPublicacion = new FechaPublicacion();
        fechaPublicacion.setFecha(libroRequestDTO.getFechaPublicacion());
        fechaPublicacion = iFechaPublicacionRepository.save(fechaPublicacion);

        libroExistente.setTituloLibro(libroRequestDTO.getTituloLibro());
        libroExistente.setFechaPublicacion(fechaPublicacion);
        libroExistente.setAutor(autor);

        Libro libroActualizado = iLibroRepository.save(libroExistente);
        return libroMapper.convertToDTO(libroActualizado);
    }

    @Transactional
    @Override
    public void eliminarLibro(Long idLibro) {
        Libro libro = iLibroRepository.findById(idLibro)
                .orElseThrow(() -> new ResourceNotFoundException(LIBRO_NO_ENCONTRADO_MSG + idLibro));
        iLibroRepository.delete(libro);
    }
}