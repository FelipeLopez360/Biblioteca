package com.felipelopez.Biblioteca.repository;

import com.felipelopez.Biblioteca.model.entity.FechaPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IFechaPublicacionRepository extends JpaRepository<FechaPublicacion, Long> {
}
