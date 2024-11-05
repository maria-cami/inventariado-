package com.tu_paquete.repositorio;

import com.tu_paquete.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {

    // Buscar productos por nombre (parcial o completo)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
