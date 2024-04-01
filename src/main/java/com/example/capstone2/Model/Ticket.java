package com.example.capstone2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketID;
    @NotNull(message = "Price should be not null")
    private Integer price;
    @NotNull(message = "seatNumber should be not null")
    private Integer seatNumber;
    @NotNull(message = "Row should be not null")
    private Integer row;
    private Integer gateNumber;
    private Date matchDate;
    private Time matchTime;
    private Integer block;
    private String club1;
    private String club2;

    private Integer userID;
}