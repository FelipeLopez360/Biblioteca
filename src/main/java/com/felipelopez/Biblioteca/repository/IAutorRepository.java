package com.felipelopez.Biblioteca.repository;

import com.felipelopez.Biblioteca.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreAutor(String nombreAutor);
}
