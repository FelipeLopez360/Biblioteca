package com.felipelopez.Biblioteca.repository;

import com.felipelopez.Biblioteca.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
