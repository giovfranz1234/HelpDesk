package com.example.helpdesk.controllers;


import com.example.helpdesk.models.*;
import com.example.helpdesk.repositories.rolRepository;
import com.example.helpdesk.services.LogTransService;
import com.example.helpdesk.services.UsuarioService;
import com.example.helpdesk.services.impl.ExportPDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.helpdesk.models.IUser;
import com.example.helpdesk.models.Rol;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {
     @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogTransService logTransService;
    @Autowired
    private rolRepository rolRepository;
    @Autowired
     private ExportPDFService exportPDFService;
    @Autowired
     private PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> actualizar(@RequestBody UserRequest usuario, @PathVariable Long id){
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
         usuarioDB.setLogin(usuario.getLogin());
         usuarioDB.setPassword(usuario.getPassword());


         return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDB));

     }
     @DeleteMapping("/{id}")
     public ResponseEntity<?> eliminar(@PathVariable Long id){
         Log log = new Log();

         Optional<Usuario> usuario = usuarioService.findById(id);
         log.setIdUsuario(1L);
         log.setTransaccion("Eliminar Usuario");
         log.setCampo(usuario.get().getNombres()+" "+usuario.get().getPaterno()+" "+usuario.get().getMaterno());
         log.setValorNuevo("Eliminado");
         log.setFecha(new Date());
         logTransService.save(log);
           usuarioService.deleteById(id);


         return ResponseEntity.noContent().build();

     }
    private List<Rol> getRoles(IUser user) {
        List<Rol> roles = new ArrayList<>();
        Optional<Rol> optionalRoleUser = rolRepository.findByDescripcion ("ROLE_USER");
        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Rol> optionalRoleAdmin = rolRepository.findByDescripcion("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        return roles;
    }
     /*@GetMapping("/export/pdf")
    public ResponseEntity<InputStreamResource> exportPdf()     {
         List<Usuario> usuarios = usuarioService.findAll();
         ByteArrayInputStream bytes= exportPDFService.usuariosPDFReport(usuarios);
         re
     }*/
}
