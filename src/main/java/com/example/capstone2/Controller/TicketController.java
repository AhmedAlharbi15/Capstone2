package com.example.capstone2.Controller;

import com.example.capstone2.Api.Api;
import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;
    @GetMapping("/get")
    public ResponseEntity getTicket()
    {
        return ResponseEntity.status(200).body(ticketService.getAllTicket());

    }

    @PostMapping("/add")
    public ResponseEntity addTicket(@RequestBody @Valid Ticket ticket, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }

        ticketService.addTicket(ticket);
        return ResponseEntity.ok().body(new Api("Ticket Added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateTicket(@PathVariable Integer id, @RequestBody @Valid Ticket ticket, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        ticketService.updateTicket(id,ticket);
        return ResponseEntity.ok().body(new Api("Ticket updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTicket(@PathVariable Integer id){
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().body(new Api("Ticket deleted"));
    }
    @GetMapping("/get-by-price/{price}")
    public ResponseEntity getAllTicketPrice(@PathVariable Integer price){
        List<Ticket> tickets = ticketService.findAllByPrice(price);
        return ResponseEntity.ok().body(tickets);
    }
    @PutMapping ("/changPrice/{id}/{price}")
    private ResponseEntity changePrice(@PathVariable Integer id, @PathVariable Integer price){
        Ticket ticket = ticketService.changePrice(id,price);
        return ResponseEntity.ok().body(ticket);
    }
}