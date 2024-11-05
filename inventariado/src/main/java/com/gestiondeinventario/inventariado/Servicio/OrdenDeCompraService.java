package com.tu_paquete.servicio;

import com.tu_paquete.modelo.OrdenDeCompra;
import com.tu_paquete.modelo.Producto;
import com.tu_paquete.repositorio.OrdenDeCompraRepository;
import com.tu_paquete.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenDeCompraService {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public OrdenDeCompra crearOrdenDeCompra(OrdenDeCompra ordenDeCompra) {
        Producto producto = productoRepository.findById(ordenDeCompra.getProducto().getCodigo()).orElse(null);
        if (producto != null && producto.getCantidad() >= ordenDeCompra.getCantidadOrdenada()) {
            producto.actualizarCantidad(producto.getCantidad() - ordenDeCompra.getCantidadOrdenada());
            productoRepository.save(producto);
            return ordenDeCompraRepository.save(ordenDeCompra);
        }
        throw new RuntimeException("No hay suficiente cantidad en inventario para crear la orden.");
    }

    public OrdenDeCompra cancelarOrdenDeCompra(OrdenDeCompra ordenDeCompra) {
        if (!ordenDeCompra.isEstaCancelada()) {
            ordenDeCompra.setEstaCancelada(true);
            Producto producto = productoRepository.findById(ordenDeCompra.getProducto().getCodigo()).orElse(null);
            if (producto != null) {
                producto.actualizarCantidad(producto.getCantidad() + ordenDeCompra.getCantidadOrdenada());
                productoRepository.save(producto);
            }
            return ordenDeCompraRepository.save(ordenDeCompra);
        }
        throw new RuntimeException("La orden ya ha sido cancelada.");
    }
}
