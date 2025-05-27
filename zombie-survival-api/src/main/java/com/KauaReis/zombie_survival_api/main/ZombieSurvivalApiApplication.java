package com.KauaReis.zombie_survival_api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZombieSurvivalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZombieSurvivalApiApplication.class ,args);
	}

}
