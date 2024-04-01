package com.example.capstone2.Controller;

import com.example.capstone2.Api.Api;
import com.example.capstone2.Model.Order;
import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Service.OrderService;
import com.example.capstone2.Service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/get")
    public ResponseEntity getOrder()
    {
        return ResponseEntity.status(200).body(orderService.getSoldTicket());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body(new Api("Order deleted"));
    }
    @PostMapping("/addOrder/{clubName1}/{clubName2}/{userId}/{noTickets]")
    public ResponseEntity addOrder(@PathVariable String clubName1,@PathVariable String clubName2, @PathVariable Integer userId, @PathVariable Integer noTickets,@RequestBody @Valid Order order, Errors errors) {

        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }

        orderService.OrderTicket(userId,noTickets, order, clubName1,clubName2);
        return ResponseEntity.ok().body(new Api("Order Made  "));
    }

}