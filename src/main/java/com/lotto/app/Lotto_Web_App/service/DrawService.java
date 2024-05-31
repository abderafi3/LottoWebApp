package com.lotto.app.Lotto_Web_App.service;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.entity.Ticket;
import com.lotto.app.Lotto_Web_App.repository.DrawRepository;
import com.lotto.app.Lotto_Web_App.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DrawService {

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Draw findLastDraw() {
        Optional<Draw> lastDraw = drawRepository.findTopByOrderByDrawDateDesc();
        return lastDraw.orElse(null);
    }


    public void saveDraw(Draw draw) {
        drawRepository.save(draw);
    }

    public Map<Integer, Long> getMatchCounts() {
        List<Ticket> allTickets = ticketRepository.findAll();
        Draw lastDraw = findLastDraw();

        List<Ticket> recentTickets = allTickets.stream()
                .filter(ticket -> ticket.getSubmitDate().isAfter(lastDraw.getDrawDate()))
                .toList();

        Map<Integer, Long> matchCounts = new HashMap<>();
        for (Ticket ticket : recentTickets) {
            long matchCount = ticket.getNumberSet().stream().filter(lastDraw.getWinningNumbers()::contains).count();
            matchCounts.put((int) matchCount, matchCounts.getOrDefault((int) matchCount, 0L) + 1);
        }

        return matchCounts;
    }

    public Map<Integer, Double> calculatePrizes(Map<Integer, Long> matchCounts) {
        Map<Integer, Double> prizes = new HashMap<>();
        prizes.put(3, 100.0);
        prizes.put(4, 1000.0);
        prizes.put(5, 10000.0);
        prizes.put(6, 1000000.0);

        Map<Integer, Double> prizeDistribution = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : prizes.entrySet()) {
            int matches = entry.getKey();
            double prize = entry.getValue();
            long winners = matchCounts.getOrDefault(matches, 0L);
            if (winners > 0) {
                prizeDistribution.put(matches, prize / winners);
            } else {
                prizeDistribution.put(matches, 0.0);
            }
        }

        return prizeDistribution;
    }
}
