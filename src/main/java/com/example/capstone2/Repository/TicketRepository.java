package com.example.capstone2.Repository;

import com.example.capstone2.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Ticket findTicketByById(Integer id);

    @Query("select t from Ticket t where t.price >= ?1")
    List<Ticket> findTicketByPrice(Integer price);

    @Query("select '*' from Ticket t where t.club1 = ?1 and t.club2 = ?2")
    List<Ticket> findTicketByClubsName(String clubName1, String clubName2);




}