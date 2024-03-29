package com.example.helpdesk.controllers;


import com.example.helpdesk.controllers.beans.TicketBean;
import com.example.helpdesk.models.Equipo;
import com.example.helpdesk.models.Ticket;
import com.example.helpdesk.models.Usuario;
import com.example.helpdesk.services.EquipoService;
import com.example.helpdesk.services.TicketService;
import com.example.helpdesk.services.UsuarioService;
import com.example.helpdesk.services.impl.ExportPDFService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.helpdesk.services.impl.ExportPDFService.fechaFormatotabla;

@RestController
@CrossOrigin
@RequestMapping("/tickets")
public class TicketController {
  @Autowired
    private TicketService ticketService;
  @Autowired
    private EquipoService equipoService;
  @Autowired
   private UsuarioService usuarioService;
  @Autowired
  private ExportPDFService exportPDFService;
 /* @GetMapping
  public ResponseEntity<?> listar(){
    return ResponseEntity.ok().body(ticketService.findAll());
  }*/
  @GetMapping("/{id}")
  public ResponseEntity<?> obtTicket(@PathVariable Long id){
    Optional<Ticket> o = ticketService.findById(id);
    if (o.isEmpty()){
      return  ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(o.get());
  }
  @GetMapping()
  public List<TicketBean> listar(){
    List <TicketBean> vResponse = new ArrayList<>();
    TicketBean vTicketAux =   new TicketBean();
    List<Ticket> vTickeReg =  ticketService.findAll();
    int i=0;
    for (Ticket tickets:vTickeReg){
      vTicketAux.setId(tickets.getId());
      vTicketAux.setDescripcion(tickets.getDescripcion());

      if (tickets.getDispositivo()!= null){
        Optional<Equipo> vEquipo = equipoService.findById(tickets.getDispositivo());
        vTicketAux.setDispositivo(vEquipo.get().getTipoEquipo()+" ("+vEquipo.get().getSerieActivoFijo()+")");
      }
      Optional<Usuario> vUsuario = usuarioService.findById(tickets.getAsignadoa());
      vTicketAux.setCreadoPor(vUsuario.get().getNombres()+" "+vUsuario.get().getPaterno());
      if (tickets.getAsignadoa()!= null){
        vTicketAux.setAsignadoa(vUsuario.get().getNombres()+" "+vUsuario.get().getPaterno());
      }
      vTicketAux.setPrioridad(tickets.getPrioridad());
      vTicketAux.setFechaRegistro(tickets.getFechaRegistro());
      vTicketAux.setFechaInicio(tickets.getFechaInicio());
      vTicketAux.setFechaFin(tickets.getFechaFin());
      vTicketAux.setEstado(tickets.getEstado());

      vResponse.add(vTicketAux);
    }
    return vResponse;
  }
  @PostMapping
  public ResponseEntity<?> crear(@RequestBody Ticket ticket){
    return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.save(ticket));

  }
  @PutMapping("/{id}")
  public ResponseEntity<?> actualizar(@RequestBody Ticket ticket, @PathVariable Long id){
    Optional<Ticket> ticketMod= ticketService.findById(id);
    if (ticketMod.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    Ticket TicketDB = ticketMod.get();
    ticket.setDescripcion (TicketDB.getDescripcion());
    ticket.setDispositivo(TicketDB.getDispositivo());
    ticket.setAsignadoa(TicketDB.getAsignadoa());
    ticket.setEstado(TicketDB.getEstado());
    ticket.setPrioridad(TicketDB.getPrioridad());
    ticket.setCreadoPor(TicketDB.getCreadoPor());
    ticket.setFechaRegistro(TicketDB.getFechaRegistro());
    ticket.setFechaInicio(TicketDB.getFechaInicio());
    ticket.setFechaFin(TicketDB.getFechaFin());
    ticket.setHistorial(TicketDB.getHistorial());


     return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.save(TicketDB));

  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> eliminar(@PathVariable Long id){
    ticketService.deleteById(id);
    return ResponseEntity.noContent().build();

  }

    @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> ticketReports(HttpServletResponse response) throws IOException {

        List<TicketBean> tickets = listar();


        ByteArrayInputStream bis =  exportPDFService.ticketReport(tickets);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment;filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }
}
