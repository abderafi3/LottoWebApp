package com.lotto.app.Lotto_Web_App.scheduler;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DrawScheduler {

    @Autowired
    private DrawService drawService;

    @Scheduled(cron = "0 0 3 * * WED,SUN")
    public void scheduleDraw() {
        Draw draw = new Draw();
        draw.setWinningNumbers(generateWinningNumbers());
        draw.setDrawDate(LocalDateTime.now());
        drawService.saveDraw(draw);
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

        while (true) {
            nextDrawDate = nextDrawDate.plusDays(1);
            if (nextDrawDate.getDayOfWeek() == DayOfWeek.WEDNESDAY || nextDrawDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                break;
            }
        }

        return nextDrawDate.withHour(18).withMinute(0).withSecond(0).withNano(0);
    }
}
