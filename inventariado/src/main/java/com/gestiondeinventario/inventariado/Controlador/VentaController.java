package com.tu_paquete.controlador;

import com.tu_paquete.modelo.Venta;
import com.tu_paquete.servicio.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> realizarVenta(@RequestBody Venta venta) {
        try {
            return ResponseEntity.ok(ventaService.realizarVenta(venta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{numeroVenta}/anular")
    public ResponseEntity<Venta> anularVenta(@PathVariable String numeroVenta, @RequestBody Venta venta) {
        try {
            return ResponseEntity.ok(ventaService.anularVenta(venta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
