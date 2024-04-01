package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Repository.TicketRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;


    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    public void addTicket(Ticket ticket)
    {
        ticketRepository.save(ticket);

    }

    public void updateTicket(Integer id,Ticket ticket){
        Ticket ticket1=ticketRepository.findTicketByById(id);

        if(ticket1 == null){
            throw new ApiException("id not found");
        }
        ticket1.setPrice(ticket.getPrice());
        ticket1.setRow(ticket.getRow());
        ticket1.setBlock(ticket.getBlock());
        ticket1.setSeatNumber(ticket.getSeatNumber());
        ticket1.setGateNumber(ticket.getPrice());
        ticket1.setClub1(ticket.getClub1());
        ticket1.setClub2(ticket.getClub2());
        ticket1.setMatchDate(ticket.getMatchDate());
        ticket1.setMatchTime(ticket.getMatchTime());
        ticketRepository.save(ticket1);

    }
    public void deleteTicket(Integer id){
        Ticket ticket=ticketRepository.findTicketByById(id);
        if(ticket == null){
            throw new ApiException("id not found");
        }
        ticketRepository.delete(ticket);
    }
    public List<Ticket> findAllByPrice(Integer price){
        List<Ticket> tickets = ticketRepository.findTicketByPrice(price);
        if(tickets.isEmpty()){
            throw new ApiException("no Ticket in this price");
        }
        return tickets;
    }
    public Ticket changePrice(Integer id, Integer price){
        Ticket ticket = ticketRepository.findTicketByById(id);
        if(ticket==null){
            throw new ApiException("no Ticket in this name");
        }
        ticket.setPrice(price);
        ticketRepository.save(ticket);
        return ticket;
    }
}