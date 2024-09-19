package com.felipelopez.Biblioteca.service;

import com.felipelopez.Biblioteca.model.entity.Autor;

import java.util.List;

public interface IAutorService {

    public List<Autor> obtenerTodosLosAutores();

    public Autor crearAutor(Autor autor);

    public Autor obtenerAutorPorId(Long idAutor);

    void eliminarAutor(Long idAutor);

    public Autor actualizarAutor(Long idAutor, Autor autorActualizado);

}
