package com.felipelopez.Biblioteca.service.impl;

import com.felipelopez.Biblioteca.model.entity.Libro;
import com.felipelopez.Biblioteca.repository.ILibroRepository;
import com.felipelopez.Biblioteca.service.ILibroService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements ILibroService {


    private final ILibroRepository iLibroRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Libro> obtenerTodosLosLibros() {
        return iLibroRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Libro obtenerLibroPorId(Long idLibro) {
        return iLibroRepository.findById(idLibro).orElse(null);
    }

    @Transactional
    @Override
    public Libro crearLibro(Libro libro) {
        return iLibroRepository.save(libro);
    }

    @Transactional
    @Override
    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        Libro libroExistente = iLibroRepository.findById(id).orElse(null);
        libroExistente.setTituloLibro(libroActualizado.getTituloLibro());
        return iLibroRepository.save(libroExistente);
    }

    @Transactional
    @Override
    public void eliminarLibro(Long idLibro) {
        Libro libro = obtenerLibroPorId(idLibro);
        iLibroRepository.delete(libro);
    }
}
