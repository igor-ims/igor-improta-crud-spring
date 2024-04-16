package com.anahuac.desarrollo.spring.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import com.anahuac.desarrollo.spring.entidades.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{
    Libro findById(int id);

    @NonNull
    List<Libro> findAll();

    void deleteById(int id);
    
    @Transactional
    @Modifying
    @Query("UPDATE Libro lib SET lib.titulo = :titulo, lib.autor = :autor, lib.isbn = :isbn WHERE lib.id = :id")
    void updateById(int id, String titulo, String autor, String isbn);

    @Query("SELECT COALESCE(MAX(lib.id), 0) FROM Libro lib")
    int findMaxId();

    @Query("SELECT l.id FROM Libro l WHERE l.isbn = :isbn")
    Integer findIdByIsbn(String isbn);
}
