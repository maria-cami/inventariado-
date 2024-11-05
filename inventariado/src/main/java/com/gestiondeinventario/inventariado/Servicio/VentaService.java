package com.tu_paquete.servicio;

import com.tu_paquete.modelo.Producto;
import com.tu_paquete.modelo.Venta;
import com.tu_paquete.repositorio.ProductoRepository;
import com.tu_paquete.repositorio.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Venta realizarVenta(Venta venta) {
        Producto producto = productoRepository.findById(venta.getProducto().getCodigo()).orElse(null);
        if (producto != null && producto.getCantidad() >= venta.getCantidadVendida()) {
            producto.actualizarCantidad(producto.getCantidad() - venta.getCantidadVendida());
            productoRepository.save(producto);
            return ventaRepository.save(venta);
        }
        throw new RuntimeException("No hay suficiente cantidad en inventario para realizar la venta.");
    }

    public Venta anularVenta(Venta venta) {
        if (!venta.isEstaAnulada()) {
            venta.setEstaAnulada(true);
            Producto producto = productoRepository.findById(venta.getProducto().getCodigo()).orElse(null);
            if (producto != null) {
                producto.actualizarCantidad(producto.getCantidad() + venta.getCantidadVendida());
                productoRepository.save(producto);
            }
            return ventaRepository.save(venta);
        }
        throw new RuntimeException("La venta ya ha sido anulada.");
    }
}
