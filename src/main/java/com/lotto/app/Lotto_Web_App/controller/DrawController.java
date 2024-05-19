package com.lotto.app.Lotto_Web_App.controller;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.entity.Ticket;
import com.lotto.app.Lotto_Web_App.scheduler.DrawScheduler;
import com.lotto.app.Lotto_Web_App.service.DrawService;
import com.lotto.app.Lotto_Web_App.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/draw")
public class DrawController {

    @Autowired
    private DrawService drawService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private DrawScheduler drawScheduler;

    @GetMapping("/nextDrawDate")
    public String getNextDrawDate() {
        LocalDateTime nextDrawDate = drawScheduler.getNextDrawDate();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return nextDrawDate.format(formatter);
    }

    @GetMapping("/lastDraw")
    public Draw getLastDraw() {
        return drawService.findLastDraw();
    }

    @GetMapping("/ticketMatchCounts")
    public Map<Integer, Long> getTicketMatchCounts() {
        Draw lastDraw = drawService.findLastDraw();
        Map<Integer, Long> matchCounts = new HashMap<>();
        for (int i = 3; i <= 6; i++) {
            matchCounts.put(i, 0L);
        }

        if (lastDraw != null) {
            List<Integer> winningNumbers = lastDraw.getWinningNumbers();
            List<Ticket> tickets = ticketService.findAllTickets();

            for (Ticket ticket : tickets) {
                long matchCount = ticket.getNumberSet().stream().filter(winningNumbers::contains).count();
                if (matchCount >= 3 && matchCount <= 6) {
                    matchCounts.put((int) matchCount, matchCounts.getOrDefault((int) matchCount, 0L) + 1);
                }
            }
        }
        return matchCounts;
    }
}
