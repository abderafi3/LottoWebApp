package com.lotto.app.Lotto_Web_App.controller;

import com.lotto.app.Lotto_Web_App.entity.Draw;
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
    public Map<String, Map<Integer, ? extends Number>> getTicketMatchCounts() {
        Map<Integer, Long> matchCounts = drawService.getMatchCounts();
        Map<Integer, Double> prizeDistribution = drawService.calculatePrizes(matchCounts);
        Map<String, Map<Integer, ? extends Number>> response = new HashMap<>();
        response.put("matchCounts", matchCounts);
        response.put("prizes", prizeDistribution);
        return response;
    }

}
