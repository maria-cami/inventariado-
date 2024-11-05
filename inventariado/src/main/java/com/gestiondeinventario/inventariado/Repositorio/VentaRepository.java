package com.tu_paquete.repositorio;

import com.tu_paquete.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {

    // Obtener todas las ventas que no han sido anuladas
    List<Venta> findByEstaAnuladaFalse();
    
    // Buscar ventas por fecha de venta
    List<Venta> findByFechaVenta(String fechaVenta);
    
    // Obtener ventas de un producto específico
    List<Venta> findByProductoCodigo(String codigoProducto);
}
