package com.felipelopez.Biblioteca.repository;

import com.felipelopez.Biblioteca.model.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

}
