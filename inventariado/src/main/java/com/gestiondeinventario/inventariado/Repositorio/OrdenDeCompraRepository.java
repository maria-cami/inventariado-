package com.tu_paquete.repositorio;

import com.tu_paquete.modelo.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDeCompraRepository extends JpaRepository<OrdenDeCompra, String> {

    // Obtener todas las órdenes de compra que no están canceladas
    List<OrdenDeCompra> findByEstaCanceladaFalse();
    
    // Buscar órdenes de compra por fecha
    List<OrdenDeCompra> findByFechaOrden(String fechaOrden);
    
    // Obtener órdenes de compra de un producto específico
    List<OrdenDeCompra> findByProductoCodigo(String codigoProducto);
}
