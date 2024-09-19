package com.felipelopez.Biblioteca.service.impl;

import com.felipelopez.Biblioteca.model.entity.FechaPublicacion;
import com.felipelopez.Biblioteca.exception.ResourceNotFoundException;
import com.felipelopez.Biblioteca.repository.IFechaPublicacionRepository;
import com.felipelopez.Biblioteca.service.IFechaPublicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FechaPublicacionServiceImpl implements IFechaPublicacionService {

    private static final String FECHA_PUBLICACION_NO_ENCONTRADA_MSG = "Fecha de publicacion no encontrada.";

    private final IFechaPublicacionRepository iFechaPublicacionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<FechaPublicacion> obtenerTodasLasFechasPublicacion() {
        if(iFechaPublicacionRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No hay fechas de publicacion en la base de datos");
        }
        return iFechaPublicacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public FechaPublicacion obtenerFechaPublicacionPorId(Long idFechaPublicacion) {
        return iFechaPublicacionRepository.findById(idFechaPublicacion)
                .orElseThrow(() -> new ResourceNotFoundException(FECHA_PUBLICACION_NO_ENCONTRADA_MSG));
    }

    @Transactional
    @Override
    public FechaPublicacion crearFechaPublicacion(FechaPublicacion fechaPublicacion) {
        return iFechaPublicacionRepository.save(fechaPublicacion);
    }

    @Transactional
    @Override
    public FechaPublicacion actualizarFechaPublicacion(Long idFechaPublicacion, FechaPublicacion fechaPublicacionActualizada) {
        FechaPublicacion fechaPublicacion = iFechaPublicacionRepository.findById(idFechaPublicacion)
                .orElseThrow(()-> new ResourceNotFoundException(FECHA_PUBLICACION_NO_ENCONTRADA_MSG));
        fechaPublicacionActualizada.setFecha(fechaPublicacionActualizada.getFecha());
        return iFechaPublicacionRepository.save(fechaPublicacion);
    }

    @Transactional
    @Override
    public void eliminarFechaPublicacion(Long idFechaPublicacion) {
        FechaPublicacion fechaPublicacion = iFechaPublicacionRepository.findById(idFechaPublicacion)
                .orElseThrow(()-> new ResourceNotFoundException(FECHA_PUBLICACION_NO_ENCONTRADA_MSG));
        iFechaPublicacionRepository.delete(fechaPublicacion);
    }
}