package com.lotto.app.Lotto_Web_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LottoWebAppApplication /*implements  CommandLineRunner*/{


	public static void main(String[] args) {
		SpringApplication.run(LottoWebAppApplication.class, args);
	}


}
