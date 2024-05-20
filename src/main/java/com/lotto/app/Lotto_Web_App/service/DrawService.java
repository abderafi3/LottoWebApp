package com.lotto.app.Lotto_Web_App.service;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.repository.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
