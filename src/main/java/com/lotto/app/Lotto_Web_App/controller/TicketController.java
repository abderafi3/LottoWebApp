package com.lotto.app.Lotto_Web_App.controller;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.entity.Ticket;
import com.lotto.app.Lotto_Web_App.scheduler.DrawScheduler;
import com.lotto.app.Lotto_Web_App.service.DrawService;
import com.lotto.app.Lotto_Web_App.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private DrawService drawService;

    @Autowired
    private DrawScheduler drawScheduler;

    @PostMapping("/submit")
    public String submitTicket(@RequestBody TicketRequest ticketRequest) {
        try {
            String ticketNumber = ticketService.saveTicket(ticketRequest.getEmail(), ticketRequest.getNumberSet());
            return "Ticket submitted successfully. Your ticket number is:  \n" + ticketNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while submitting the ticket. Please try again.";
        }
    }

    @GetMapping("/check/{ticketNumber}")
    public Map<String, Object> checkTicket(@PathVariable String ticketNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Ticket> ticketOptional = ticketService.findByTicketNumber(ticketNumber);
            if (!ticketOptional.isPresent()) {
                response.put("error", true);
                response.put("message", "Invalid ticket number.");
                return response;
            }

            Ticket ticket = ticketOptional.get();
            Draw lastDraw = drawService.findLastDraw();
            if (lastDraw == null) {
                response.put("error", true);
                response.put("message", "No draw has occurred yet.");
                return response;
            }

            LocalDateTime nextDrawDate = drawScheduler.getNextDrawDate();
            if (ticket.getSubmitDate().isAfter(lastDraw.getDrawDate())) {
                response.put("error", true);
                response.put("message", "This ticket will be included in the next draw on " + nextDrawDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' HH:mm")) + ".");
                return response;
            }

            List<Integer> winningNumbers = lastDraw.getWinningNumbers();
            response.put("ticketNumbers", ticket.getNumberSet());
            response.put("winningNumbers", winningNumbers);
            response.put("error", false);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", true);
            response.put("message", "An error occurred. Please try again.");
            return response;
        }
    }

    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }
}

class TicketRequest {
    private String email;
    private List<Integer> numberSet;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getNumberSet() {
        return numberSet;
    }

    public void setNumberSet(List<Integer> numberSet) {
        this.numberSet = numberSet;
    }
}
