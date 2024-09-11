package com.felipelopez.Biblioteca.service.impl;

import com.felipelopez.Biblioteca.exception.BadRequestException;
import com.felipelopez.Biblioteca.exception.ResourceNotFoundException;
import com.felipelopez.Biblioteca.model.entity.Autor;
import com.felipelopez.Biblioteca.repository.IAutorRepository;
import com.felipelopez.Biblioteca.service.IAutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements IAutorService {

    private static final String AUTOR_NO_ENCONTRADO_MSG = "Autor no encontrado con el id: ";

    private final IAutorRepository iAutorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Autor> obtenerTodosLosAutores() {
        if(iAutorRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No hay autores en la base de datos");
        }
        return iAutorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Autor obtenerAutorPorId(Long idAutor) {
        return iAutorRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException(AUTOR_NO_ENCONTRADO_MSG));
    }
    @Transactional
    @Override
    public Autor crearAutor(Autor autor) {
        if(autor.getNombreAutor().equals("") || autor.getNombreAutor() == null) {
            throw new BadRequestException("El nombre del autor no puede estar vacio");
        }
        return iAutorRepository.save(autor);
    }

    @Transactional
    @Override
    public Autor actualizarAutor(Long idAutor, Autor autorActualizado) {
        if(autorActualizado.getNombreAutor().equals("") || autorActualizado.getNombreAutor() == null) {
            throw new BadRequestException("El nombre del autor no puede estar vacio");
        }
        Autor autorExistente = iAutorRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException(AUTOR_NO_ENCONTRADO_MSG));
        autorExistente.setNombreAutor(autorActualizado.getNombreAutor());
        return iAutorRepository.save(autorExistente);
    }

    @Transactional
    @Override
    public void eliminarAutor(Long idAutor) {
        Autor autor = iAutorRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException(AUTOR_NO_ENCONTRADO_MSG));
        iAutorRepository.delete(autor);
    }
}
