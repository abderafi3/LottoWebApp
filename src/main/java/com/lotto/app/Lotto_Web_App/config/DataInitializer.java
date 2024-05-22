package com.lotto.app.Lotto_Web_App.config;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.entity.Ticket;
import com.lotto.app.Lotto_Web_App.repository.DrawRepository;
import com.lotto.app.Lotto_Web_App.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void run(String... args) throws Exception {
        // draw table
        drawRepository.save(new Draw(LocalDateTime.of(2024, 5, 18, 18, 0), Arrays.asList(19, 22, 23, 47, 5, 16)));
        drawRepository.save(new Draw(LocalDateTime.of(2024, 5, 22, 18, 0), Arrays.asList(18, 12, 38, 44, 16, 22)));
        //drawRepository.save(new Draw(LocalDateTime.of(2024, 5, 25, 18, 0), Arrays.asList(22, 36, 5, 31, 13, 22)));

        //  ticket table
        ticketRepository.save(new Ticket("user1@example.com", "ticket1", Arrays.asList(16, 2, 33, 27, 18, 49), LocalDateTime.of(2024, 5, 14, 10, 18)));
        ticketRepository.save(new Ticket("user2@example.com", "ticket2", Arrays.asList(10, 28, 30, 31, 39, 43), LocalDateTime.of(2024, 5, 19, 15, 32)));
        ticketRepository.save(new Ticket("user3@example.com", "ticket3", Arrays.asList(18, 18, 32, 31, 44, 45), LocalDateTime.of(2024, 5, 22, 9, 32)));
    }
}
