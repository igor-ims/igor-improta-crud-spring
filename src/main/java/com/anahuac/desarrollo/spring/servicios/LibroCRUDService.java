package com.anahuac.desarrollo.spring.servicios;

import java.util.List;

import com.anahuac.desarrollo.spring.entidades.Libro;

public interface LibroCRUDService {
    Libro obtenerLibroId(int id);
    List<Libro> obtenerTodosLibros();
    void borrarLibroId(int id);
    void modificarLibro(Libro libro);
    void insertarLibro(Libro libro);
    int obtenerMaxId();
    int obtenerIdPorIsbn(String isbn);
}
