package com.tu_paquete.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Producto {
    @Id
    private String codigo; // Clave primaria
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;

    @OneToMany(mappedBy = "producto")
    private List<Venta> ventas;

    @OneToMany(mappedBy = "producto")
    private List<OrdenDeCompra> ordenesDeCompra;

    // Constructor vacío para JPA
    public Producto() {}

    public Producto(String codigo, String nombre, String descripcion, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad; // Método getter para obtener la cantidad
    }

    public void actualizarCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public List<OrdenDeCompra> getOrdenesDeCompra() {
        return ordenesDeCompra;
    }
}
