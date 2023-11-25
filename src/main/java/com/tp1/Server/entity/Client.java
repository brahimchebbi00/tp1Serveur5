package com.tp1.Server.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    private String ipAdress;
    private boolean winner;
    private int iteration;

    public Client(String ipAdress, int iteration) {
        this.ipAdress = ipAdress;
        this.iteration = iteration;
    }
}
