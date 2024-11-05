package com.tu_paquete.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Inventario {
    @Id
    private Long id; // Clave primaria
    private List<Producto> listaProductos;

    // Constructor vacío para JPA
    public Inventario() {
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void eliminarProducto(String codigo) {
        listaProductos.removeIf(p -> p.getCodigo().equals(codigo));
    }

    public Producto buscarProducto(String codigo) {
        for (Producto producto : listaProductos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null; // Producto no encontrado
    }

    public void actualizarInventario(String codigo, int nuevaCantidad) {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            producto.actualizarCantidad(nuevaCantidad);
        }
    }

     // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
}
