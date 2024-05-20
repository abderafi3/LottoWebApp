package com.lotto.app.Lotto_Web_App.controller;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import com.lotto.app.Lotto_Web_App.scheduler.DrawScheduler;
import com.lotto.app.Lotto_Web_App.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;

@Controller
public class MainController {

    @Autowired
    private DrawService drawService;

    @Autowired
    private DrawScheduler drawScheduler;

    @GetMapping("/")
    public String index(Model model) {
        Draw lastDraw = drawService.findLastDraw();
        if (lastDraw != null) {
            model.addAttribute("lastGeneratedNumbers", lastDraw.getWinningNumbers());
            model.addAttribute("lastDrawDate", lastDraw.getDrawDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' HH:mm")));
            model.addAttribute("nextDrawDate", drawScheduler.getNextDrawDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' HH:mm")));
        } else {
            model.addAttribute("lastGeneratedNumbers", null);
            model.addAttribute("lastDrawDate", "No draw has occurred yet.");
            model.addAttribute("nextDrawDate", "No draw has occurred yet.");
        }
        return "index";
    }
}
