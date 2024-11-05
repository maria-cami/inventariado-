package com.tu_paquete.servicio;

import com.tu_paquete.modelo.Producto;
import com.tu_paquete.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorCodigo(String codigo) {
        return productoRepository.findById(codigo);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(String codigo) {
        productoRepository.deleteById(codigo);
    }

    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
}
