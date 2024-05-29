package com.lotto.app.Lotto_Web_App.scheduler;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.entity.Ticket;
import com.lotto.app.Lotto_Web_App.service.DrawService;
import com.lotto.app.Lotto_Web_App.service.EmailService;
import com.lotto.app.Lotto_Web_App.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DrawScheduler {

    @Autowired
    private DrawService drawService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 18 * * WED,SAT")
    public void scheduleDraw() {
        Draw draw = new Draw();
        draw.setWinningNumbers(generateWinningNumbers());
        draw.setDrawDate(LocalDateTime.now());
        drawService.saveDraw(draw);

        List<Ticket> allTickets = ticketService.findAllTickets();

        for (Ticket ticket : allTickets) {
            long matchCount = ticket.getNumberSet().stream().filter(draw.getWinningNumbers()::contains).count();
            String message;
            if (matchCount >= 3) {
                message = "Congratulations! You have " + matchCount + " matching numbers!";
            } else {
                message = "Better luck next time!";
            }
            emailService.sendEmail(ticket.getEmail(), "Lotto Draw Results", message);
        }
    }
    

    private List<Integer> generateWinningNumbers() {
        Random random = new Random();
        List<Integer> winningNumbers = new ArrayList<>();
        while (winningNumbers.size() < 6) {
            int nextNumber = random.nextInt(49) + 1;
            if (!winningNumbers.contains(nextNumber)) {
                winningNumbers.add(nextNumber);
            }
        }
        return winningNumbers;
    }


    public LocalDateTime getNextDrawDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextDrawDate = now;

        if (now.toLocalTime().isAfter(LocalTime.of(18, 0))) {
            nextDrawDate = nextDrawDate.plusDays(1);
        }

        while (true) {
            if (nextDrawDate.getDayOfWeek() == DayOfWeek.WEDNESDAY || nextDrawDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                break;
            }
            nextDrawDate = nextDrawDate.plusDays(1);
        }

        return nextDrawDate.withHour(18).withMinute(0).withSecond(0).withNano(0);
    }

}