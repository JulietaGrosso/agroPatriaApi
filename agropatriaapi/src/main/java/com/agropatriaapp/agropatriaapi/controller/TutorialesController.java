package com.agropatriaapp.agropatriaapi.controller;

import com.agropatriaapp.agropatriaapi.dto.TutorialDto;
import com.agropatriaapp.agropatriaapi.model.Tutoriales;
import com.agropatriaapp.agropatriaapi.services.TutorialesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tutoriales")
public class TutorialesController {

    @Autowired
    private TutorialesService tutorialesService;

    // Obtener todos los tutoriales
    @GetMapping
    public ResponseEntity<List<Tutoriales>> obtenerTodos() {
        return ResponseEntity.ok(tutorialesService.obtenerTodos());
    }

    // Obtener tutorial por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tutoriales> obtenerPorId(@PathVariable int id) {
        return tutorialesService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo tutorial
    @PostMapping
    public ResponseEntity<Tutoriales> crear(@RequestBody TutorialDto tutorial) {
        Tutoriales nuevo = tutorialesService.guardar(tutorial);
        return ResponseEntity.ok(nuevo);
    }

    // Modificar tutorial existente
    @PutMapping("/{id}")
    public ResponseEntity<Tutoriales> modificar(@PathVariable int id, @RequestBody TutorialDto datos) {
        return tutorialesService.modificar(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar tutorial
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        tutorialesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
