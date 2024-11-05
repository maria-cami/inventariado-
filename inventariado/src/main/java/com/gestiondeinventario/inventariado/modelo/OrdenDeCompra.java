package com.tu_paquete.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrdenDeCompra {
    @Id
    private String numeroOrden; // Clave primaria
    @ManyToOne
    private Producto producto;
    private int cantidadOrdenada;
    private String fechaOrden;
    private boolean estaCancelada;

    // Constructor vacío para JPA
    public OrdenDeCompra() {}

    public OrdenDeCompra(String numeroOrden, Producto producto, int cantidadOrdenada, String fechaOrden) {
        this.numeroOrden = numeroOrden;
        this.producto = producto;
        this.cantidadOrdenada = cantidadOrdenada;
        this.fechaOrden = fechaOrden;
        this.estaCancelada = false; // Inicialmente la orden no está cancelada
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidadOrdenada() {
        return cantidadOrdenada;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public boolean isEstaCancelada() {
        return estaCancelada;
    }

    public void crearOrden(Inventario inventario) {
        // Verificar si hay suficiente cantidad en el inventario
        Producto productoEnInventario = inventario.buscarProducto(producto.getCodigo());
        if (productoEnInventario != null && productoEnInventario.getCantidad() >= cantidadOrdenada) {
            // Si hay suficiente cantidad, reducirla del inventario
            inventario.actualizarInventario(producto.getCodigo(), productoEnInventario.getCantidad() - cantidadOrdenada);
            System.out.println("Orden de compra creada: " + numeroOrden);
        } else {
            System.out.println("No hay suficiente cantidad en inventario para crear la orden.");
        }
    }

    public void cancelarOrden(Inventario inventario) {
        if (!estaCancelada) {
            // Si la orden no está cancelada, se puede cancelar
            this.estaCancelada = true;
            // Devuelve la cantidad al inventario
            inventario.actualizarInventario(producto.getCodigo(), inventario.buscarProducto(producto.getCodigo()).getCantidad() + cantidadOrdenada);
            System.out.println("Orden de compra cancelada: " + numeroOrden);
        } else {
            System.out.println("La orden ya ha sido cancelada.");
        }
    }
}
