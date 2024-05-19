package com.lotto.app.Lotto_Web_App.service;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.repository.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrawService {

    @Autowired
    private DrawRepository drawRepository;

    public Draw findLastDraw() {
        List<Draw> draws = drawRepository.findAll();
        return draws.isEmpty() ? null : draws.get(draws.size() - 1);
    }

    public void saveDraw(Draw draw) {
        drawRepository.save(draw);
    }

    public LocalDateTime calculateNextDrawDate(LocalDateTime lastDrawDate) {
        LocalDateTime nextDrawDate = lastDrawDate;
        while (nextDrawDate.isBefore(LocalDateTime.now()) || nextDrawDate.isEqual(LocalDateTime.now())) {
            nextDrawDate = nextDrawDate.plusDays(1);
            if (nextDrawDate.getDayOfWeek() == DayOfWeek.WEDNESDAY || nextDrawDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                break;
            }
        }
        return nextDrawDate.withHour(18).withMinute(0).withSecond(0).withNano(0);
    }
}
