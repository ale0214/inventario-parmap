package com.parmap.inventario;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    private List<Producto> productos = new ArrayList<>();

    @GetMapping("/hola")
    public String saludar() {
        return "¡Hola desde la API de inventario!";
    }

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productos;
    }

    @PostMapping("/agregar")
    public String agregar(@RequestBody Producto p) {
        productos.add(p);
        return "Producto agregado correctamente: " + p.getNombre();
    }

    // Actualizar producto
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @RequestBody Producto actualizado) {

        for (Producto p : productos) {
            if (p.getId() == id) {
                p.setNombre(actualizado.getNombre());
                p.setCantidad(actualizado.getCantidad());
                p.setPrecio(actualizado.getPrecio());

                return "Producto actualizado correctamente: " + p.getNombre();
            }
        }

        return "Producto con ID " + id + " no encontrado.";
    }


    // Eliminar producto
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                productos.remove(p);
                return "Producto eliminado correctamente: " + p.getNombre();
            }
        }
        return "Producto no encontrado";
    }
}

