package com.tu_paquete.controlador;

import com.tu_paquete.modelo.OrdenDeCompra;
import com.tu_paquete.servicio.OrdenDeCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraService ordenDeCompraService;

    @PostMapping
    public ResponseEntity<OrdenDeCompra> crearOrdenDeCompra(@RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            return ResponseEntity.ok(ordenDeCompraService.crearOrdenDeCompra(ordenDeCompra));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{numeroOrden}/cancelar")
    public ResponseEntity<OrdenDeCompra> cancelarOrdenDeCompra(@PathVariable String numeroOrden, @RequestBody OrdenDeCompra ordenDeCompra) {
        try {
            return ResponseEntity.ok(ordenDeCompraService.cancelarOrdenDeCompra(ordenDeCompra));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
