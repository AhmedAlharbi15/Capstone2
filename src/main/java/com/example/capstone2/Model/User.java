package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @NotEmpty(message = "Name should be not Empty")
    @Column(columnDefinition = "varchar(8) not null")
    private String name;
    @NotEmpty(message = "Email should be not Empty")
    @Column(columnDefinition = "varchar(15)")
    @Email
    private String email;
    @NotNull(message = "Password should be not null")
    private Integer password;
}