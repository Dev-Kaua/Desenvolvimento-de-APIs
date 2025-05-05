package com.KauaReis.zombie_survival_api.util;

import com.KauaReis.zombie_survival_api.personagens.Sobrevivente;
import com.KauaReis.zombie_survival_api.personagens.Zumbi;
import com.KauaReis.zombie_survival_api.service.ZumbiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JogoInitializer {

    @Bean
    public CommandLineRunner start(ZumbiService service) {
        return args -> {
            Sobrevivente s1 = new Sobrevivente("João");
            Sobrevivente s2 = new Sobrevivente("Maria");
            Zumbi z1 = new Zumbi("Zé Morto");

            service.adicionarSobrevivente(s1);
            service.adicionarSobrevivente(s2);
            service.adicionarZumbi(z1);

            System.out.println("🌍 Cenário inicial carregado com sobreviventes e zumbis!");
        };
    }
}
