package com.felipelopez.Biblioteca.service;

import com.felipelopez.Biblioteca.entity.FechaPublicacion;

import java.util.List;

public interface IFechaPublicacionService {

    public List<FechaPublicacion> obtenerTodasLasFechasPublicacion();

    public FechaPublicacion obtenerFechaPublicacionPorId(Long idFechaPublicacion);

    public FechaPublicacion crearFechaPublicacion(FechaPublicacion fechaPublicacion);

    public FechaPublicacion actualizarFechaPublicacion(Long idFechaPublicacion, FechaPublicacion fechaPublicacionActualizada);

    void eliminarFechaPublicacion(Long idFechaPublicacion);

}
