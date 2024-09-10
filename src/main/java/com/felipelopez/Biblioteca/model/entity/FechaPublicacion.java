package com.felipelopez.Biblioteca.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fecha_publicacion")
public class FechaPublicacion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long idFechaPublicacion;

        @Column(name = "fecha_publicacion", nullable = false)
        public LocalDate fechaPublicacion;
}
