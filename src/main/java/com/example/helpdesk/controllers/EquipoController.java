package com.example.helpdesk.controllers;

import com.example.helpdesk.models.Equipo;
import com.example.helpdesk.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


    @RestController
    @CrossOrigin
    @RequestMapping("/equipos")
    public class EquipoController {
        @Autowired
        private EquipoService equipoService;

        @GetMapping
        public ResponseEntity<?> listar(){
            return ResponseEntity.ok().body(equipoService.findAll());
        }
        @GetMapping("/{id}")
        public ResponseEntity<?> obtEquipo(@PathVariable Long id){
            Optional<Equipo> o = equipoService.findById(id);
            if (o.isEmpty()){
                return  ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(o.get());
        }
        @PostMapping
        public ResponseEntity<?> crear(@RequestBody Equipo equipo){
            return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.save(equipo));

        }
        @PutMapping("/{id}")
        public ResponseEntity<?> actualizar(@RequestBody Equipo equipo, @PathVariable Long id){
            Optional<Equipo> equipoMod= equipoService.findById(id);
            if (equipoMod.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            Equipo equipoDB = equipoMod.get();
            equipoDB.setSerieActivoFijo(equipo.getSerieActivoFijo());
            equipoDB.setMarca(equipo.getMarca());
            equipoDB.setPropietario(equipo.getPropietario());
            equipoDB.setUnidadOrg(equipo.getUnidadOrg());
            equipoDB.setTipoEquipo(equipo.getTipoEquipo());
            equipoDB.setObservaciones(equipo.getObservaciones());

            return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.save(equipoDB));

        }
        @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminar(@PathVariable Long id){
            equipoService.deleteById(id);
            return ResponseEntity.noContent().build();

        }
        @GetMapping("actFijo/{activoFijo}")
        public ResponseEntity<?> ObtEquipoByActFijo(@PathVariable String activoFijo){
            Optional<Equipo> o = equipoService.findBYActivoFijo(activoFijo);
            if (o.isEmpty()){
                return  ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(o.get());
        }


    }

