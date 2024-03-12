package com.example.helpdesk.controllers;


import com.example.helpdesk.models.Usuario;
import com.example.helpdesk.services.UsuarioService;
import com.example.helpdesk.services.impl.ExportPDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {
     @Autowired
    private UsuarioService usuarioService;
    @Autowired
     private ExportPDFService exportPDFService;
     @GetMapping
      public ResponseEntity<?> obtUsuarios(){
         return ResponseEntity.ok().body(usuarioService.findAll());
     }
     @GetMapping("/{id}")
     public ResponseEntity<?> obtUsuario(@PathVariable Long id){
         Optional<Usuario> o = usuarioService.findById(id);
         if (o.isEmpty()){
          return  ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(o.get());
     }
     @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario){
         return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));

     }
     @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Usuario usuario, @PathVariable Long id){
         Optional<Usuario> usuariomod= usuarioService.findById(id);
         if (usuariomod.isEmpty()){
            return ResponseEntity.notFound().build();
         }
         Usuario usuarioDB = usuariomod.get();
         usuarioDB.setNombres(usuario.getNombres());
         usuarioDB.setPaterno(usuario.getPaterno());
         usuarioDB.setMaterno(usuario.getMaterno());
         usuarioDB.setCorreoElectronico(usuario.getCorreoElectronico());
         usuarioDB.setDocIdentidad(usuario.getDocIdentidad());
         usuarioDB.setEstado(usuario.getEstado());
         usuarioDB.setTelefono(usuario.getTelefono());
         usuarioDB.setRol(usuario.getRol());
         return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDB));

     }
     @DeleteMapping("/{id}")
     public ResponseEntity<?> eliminar(@PathVariable Long id){
         usuarioService.deleteById(id);
         return ResponseEntity.noContent().build();

     }

     /*@GetMapping("/export/pdf")
    public ResponseEntity<InputStreamResource> exportPdf()     {
         List<Usuario> usuarios = usuarioService.findAll();
         ByteArrayInputStream bytes= exportPDFService.usuariosPDFReport(usuarios);
         re
     }*/
}
