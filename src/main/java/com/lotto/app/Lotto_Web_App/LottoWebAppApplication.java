package com.lotto.app.Lotto_Web_App;

import com.lotto.app.Lotto_Web_App.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LottoWebAppApplication {
	@Autowired
	private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(LottoWebAppApplication.class, args);
	}
}
