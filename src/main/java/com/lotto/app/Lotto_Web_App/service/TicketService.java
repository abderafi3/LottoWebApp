package com.lotto.app.Lotto_Web_App.service;

import com.lotto.app.Lotto_Web_App.entity.Ticket;
import com.lotto.app.Lotto_Web_App.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EmailService emailService;


    public String saveTicket(String email, List<Integer> numberSet) {
        String ticketNumber = UUID.randomUUID().toString();
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setEmail(email);
        ticket.setNumberSet(numberSet);
        ticket.setSubmitDate(LocalDateTime.now());
        ticketRepository.save(ticket);

         // Send confirmation email
        if (email != null && !email.isEmpty()) {
            String subject = "Lotto Ticket Confirmation";
            String text = "Your ticket has been successfully submitted with the following numbers: " + numberSet.toString()
                    + " \n Your Ticket Number is: " + ticketNumber;
            emailService.sendEmail(email, subject, text);
        }
        return ticketNumber;
    }

    public Optional<Ticket> findByTicketNumber(String ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber);
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }
}
