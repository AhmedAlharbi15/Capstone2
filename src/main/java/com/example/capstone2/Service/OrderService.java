package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Order;
import com.example.capstone2.Model.Ticket;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.OrderRepository;
import com.example.capstone2.Repository.TicketRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    List<Ticket> ticket;
    List<Order> soldTickets;
    public String OrderTicket (Integer userID,  Integer numberOfTickets,Order order,String clubName1, String clubName2){
        ticket  = getAvailableTicket(clubName1, clubName2);
        User user = userRepository.findUserById(userID);
        if(ticket.isEmpty()){
            return "There is no tickets available";
        }else if(ticket.size() < numberOfTickets){
            return "There is no enough tickets";
        }else{
            for (int i =0 ; i<numberOfTickets ; i++){
                order.setTicketID(ticket.get(i).getTicketID());
                order.setUserID(user.getUserID());
                order.setSeatNumber(ticket.get(i).getSeatNumber());

            }
            return "Done";
        }
    }
    public List<Ticket> getAvailableTicket(String clubName1, String clubName2)
    {
        List<Ticket> ticket;
        List<Order> soldTickets;
        ticket = ticketRepository.findTicketByClubsName(clubName1, clubName2);
        soldTickets = getSoldTicket();
        for (int i =0; i <= ticket.size() ; i++){
            for (int j = 0; j <= soldTickets.size(); j++){
                if(ticket.get(i).getTicketID() == soldTickets.get(j).getTicketID()){
                    ticket.remove(ticket.get(i));
                    break;
                }
            }
        }
        return ticket;
    }
    public List<Order> getSoldTicket()

    {
        return orderRepository.findAll();
    }
    public void deleteOrder(Integer id){
        Order order=orderRepository.findOrderById(id);
        if(order == null){
            throw new ApiException("id not found");
        }
        orderRepository.delete(order);
    }
}