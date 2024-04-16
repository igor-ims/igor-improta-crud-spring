package com.anahuac.desarrollo.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anahuac.desarrollo.spring.entidades.Libro;
import com.anahuac.desarrollo.spring.persistencia.LibroRepository;

@Service
public class LibroCRUDServiceImpl implements LibroCRUDService{
    @Autowired
    private LibroRepository LibroRepository;

    public Libro obtenerLibroId(int id){
        Libro libro = this.LibroRepository.findById(id);
        System.out.println(libro);
        return libro;
    }
    
    @Override
    public List<Libro> obtenerTodosLibros(){
        return LibroRepository.findAll();
    }

    @Override
    public void borrarLibroId(int id) {
        Libro libro = LibroRepository.findById(id);
        if (libro == null) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }

        LibroRepository.deleteById(id);
    }

    @Override
    public void modificarLibro(Libro libro){
        Libro nlibro = LibroRepository.findById(libro.getId());
        if (nlibro == null) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + libro.getId());
        }

        nlibro.setTitulo(libro.getTitulo());
        nlibro.setAutor(libro.getAutor());
        nlibro.setIsbn(libro.getIsbn());

        LibroRepository.save(nlibro);
    }
    
    public void insertarLibro(Libro libro){
        LibroRepository.save(libro);
    }

    public int obtenerMaxId() {
        return LibroRepository.findMaxId();
    }
}
