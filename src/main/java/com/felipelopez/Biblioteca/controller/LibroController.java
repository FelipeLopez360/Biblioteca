package com.felipelopez.Biblioteca.controller;

import com.felipelopez.Biblioteca.exception.ResourceNotFoundException;
import com.felipelopez.Biblioteca.model.dto.LibroRequestDTO;
import com.felipelopez.Biblioteca.model.dto.LibroResponseDTO;
import com.felipelopez.Biblioteca.service.ILibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {

    private final ILibroService iLibroService;

    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodosLosLibros() {
        List<LibroResponseDTO> libros = iLibroService.obtenerTodosLosLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseDTO> obtenerLibroPorId(@PathVariable Long id) {
        try {
            LibroResponseDTO libroResponseDTO = iLibroService.obtenerLibroPorId(id);
            return new ResponseEntity<>(libroResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<LibroResponseDTO> crearLibro(@RequestBody LibroRequestDTO libroRequestDTO) {
        LibroResponseDTO crearLibro = iLibroService.crearLibro(libroRequestDTO);
        return new ResponseEntity<>(crearLibro, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LibroResponseDTO> actualizarLibro(@PathVariable Long id, @RequestBody LibroRequestDTO libroRequestDTO) {
        LibroResponseDTO actualizarLibro = iLibroService.actualizarLibro(id, libroRequestDTO);
        return new ResponseEntity<>(actualizarLibro, HttpStatus.OK);
    }

}
