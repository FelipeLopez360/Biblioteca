package com.felipelopez.Biblioteca.repository;

import com.felipelopez.Biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
}
