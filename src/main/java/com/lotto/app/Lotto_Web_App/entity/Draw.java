package com.lotto.app.Lotto_Web_App.entity;

import com.lotto.app.Lotto_Web_App.config.ListToStringConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Draw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ListToStringConverter.class)
    private List<Integer> winningNumbers;

    private LocalDateTime drawDate;

    //constructor


    public Draw( LocalDateTime drawDate, List<Integer> winningNumbers) {
        this.drawDate = drawDate;
        this.winningNumbers = winningNumbers;
    }

    public Draw() {
    }


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public LocalDateTime getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(LocalDateTime drawDate) {
        this.drawDate = drawDate;
    }
}
