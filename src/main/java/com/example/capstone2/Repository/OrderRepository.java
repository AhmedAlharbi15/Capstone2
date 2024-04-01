package com.example.capstone2.Repository;

import com.example.capstone2.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findOrderById(Integer id);

    @Query("select '*' from Order o where o.ticketID = ?1")
    Order findOrderByTicketID(Integer id);


}
