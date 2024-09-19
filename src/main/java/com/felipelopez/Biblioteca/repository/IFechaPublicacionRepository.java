package com.felipelopez.Biblioteca.repository;

import com.felipelopez.Biblioteca.model.entity.FechaPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface IFechaPublicacionRepository extends JpaRepository<FechaPublicacion, Long> {
    Optional<FechaPublicacion> findByFecha(LocalDate fecha);
}
