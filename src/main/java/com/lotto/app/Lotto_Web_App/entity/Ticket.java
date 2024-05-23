package com.lotto.app.Lotto_Web_App.entity;

import com.lotto.app.Lotto_Web_App.config.ListToStringConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String ticketNumber;

    @Convert(converter = ListToStringConverter.class)
    private List<Integer> numberSet;

    private LocalDateTime submitDate;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public List<Integer> getNumberSet() {
        return numberSet;
    }

    public void setNumberSet(List<Integer> numberSet) {
        this.numberSet = numberSet;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }
}
