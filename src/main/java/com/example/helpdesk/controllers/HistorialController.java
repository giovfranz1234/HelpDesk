package com.example.helpdesk.controllers;


import com.example.helpdesk.models.Historial;
import com.example.helpdesk.services.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/historial")
public class HistorialController {
    @Autowired
    private HistorialService historialService;
    @GetMapping
    public ResponseEntity<?> obtHistorial(){
        return ResponseEntity.ok().body(historialService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtHist(@PathVariable Long id){
        Optional<Historial> o = historialService.findById(id);
        if (o.isEmpty()){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(o.get());
    }
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Historial historial){
        return ResponseEntity.status(HttpStatus.CREATED).body(historialService.save(historial));

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Historial historial, @PathVariable Long id){
        Optional<Historial> usuariomod= historialService.findById(id);
        if (usuariomod.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Historial historialDB = usuariomod.get();
        historialDB.setDescripcion(historial.getDescripcion());
        historialDB.setResueltoPor(historial.getResueltoPor());
        historialDB.setTicket(historial.getTicket());
        historialDB.setDocumento(historial.getDocumento());

        return ResponseEntity.status(HttpStatus.CREATED).body(historialService.save(historialDB));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        historialService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
