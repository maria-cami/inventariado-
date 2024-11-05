package com.tu_paquete.controlador;

import com.tu_paquete.modelo.Producto;
import com.tu_paquete.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> obtenerProductoPorCodigo(@PathVariable String codigo) {
        return productoService.obtenerProductoPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String codigo, @RequestBody Producto producto) {
        if (!productoService.obtenerProductoPorCodigo(codigo).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        producto.setCodigo(codigo);
        return ResponseEntity.ok(productoService.actualizarProducto(producto));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String codigo) {
        if (!productoService.obtenerProductoPorCodigo(codigo).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminarProducto(codigo);
        return ResponseEntity.noContent().build();
    }
}
