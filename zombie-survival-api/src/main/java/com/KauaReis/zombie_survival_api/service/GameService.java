package com.KauaReis.zombie_survival_api.service;

import com.KauaReis.zombie_survival_api.personagens.Sobrevivente;
import com.KauaReis.zombie_survival_api.personagens.Zumbi;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final SobreviventeService sobreviventeService;
    private final ZumbiService zumbiService;

    private boolean jogoAtivo = false;

    public GameService(SobreviventeService sobreviventeService, ZumbiService zumbiService) {
        this.sobreviventeService = sobreviventeService;
        this.zumbiService = zumbiService;
    }

    public void iniciarJogo() {
        if (jogoAtivo) {
            System.out.println("⚠️ O jogo já está em andamento!");
            return;
        }

        jogoAtivo = true;
        System.out.println("🎮 Iniciando o jogo!");

        // Criando sobreviventes
        sobreviventeService.adicionarSobrevivente(new Sobrevivente("Alice"));
        sobreviventeService.adicionarSobrevivente(new Sobrevivente("Bob"));
        sobreviventeService.adicionarSobrevivente(new Sobrevivente("Charlie"));

        // Criando zumbis
        zumbiService.adicionarZumbi(new Zumbi("Walker", Zumbi.TipoZumbi.NORMAL));
        zumbiService.adicionarZumbi(new Zumbi("Brute", Zumbi.TipoZumbi.AVANCADO));
        zumbiService.adicionarZumbi(new Zumbi("Mutante X", Zumbi.TipoZumbi.MUTANTE));
    }

    @Scheduled(fixedRate = 5000)
    public void verificarFimDeJogo() {
        if (!jogoAtivo) return;

        boolean todosZumbisMortos = zumbiService.listarZumbis().stream().noneMatch(Zumbi::estaVivo);
        boolean todosSobreviventesMortos = sobreviventeService.listarSobreviventes().stream().noneMatch(Sobrevivente::estaVivo);

        if (todosZumbisMortos) {
            jogoAtivo = false;
            System.out.println("\n✅ Todos os zumbis foram eliminados. Sobreviventes venceram!");
        } else if (todosSobreviventesMortos) {
            jogoAtivo = false;
            System.out.println("\n☠️ Todos os sobreviventes morreram. Os zumbis dominaram tudo.");
        }
    }

    public void reiniciarJogo() {
        jogoAtivo = false;
        sobreviventeService.zerar();
        zumbiService.zerar();
        iniciarJogo();
    }

    public boolean executarRodadaManual() {
        if (!jogoAtivo) return false;

        sobreviventeService.realizarTurno();
        zumbiService.realizarTurno();
        verificarFimDeJogo();
        return true;
    }

    public String obterStatusFormatado() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n📊 Status do Jogo:\n");

        sb.append("🧍 Sobreviventes:\n");
        for (Sobrevivente s : sobreviventeService.listarSobreviventes()) {
            sb.append(String.format("- %s: %d de vida (Vivo: %s)\n", s.getNome(), s.getVida(), s.estaVivo()));
        }

        sb.append("\n🧟 Zumbis:\n");
        for (Zumbi z : zumbiService.listarZumbis()) {
            sb.append(String.format("- %s: %d de vida (Vivo: %s)\n", z.getNome(), z.getVida(), z.estaVivo()));
        }

        return sb.toString();
    }

    public void mostrarStatus() {
        System.out.println("\n📊 Status do Jogo:");

        System.out.println("🧍 Sobreviventes:");
        for (Sobrevivente s : sobreviventeService.listarSobreviventes()) {
            System.out.printf("- %s: %d de vida (Vivo: %s)\n", s.getNome(), s.getVida(), s.estaVivo());
        }

        System.out.println("🧟 Zumbis:");
        for (Zumbi z : zumbiService.listarZumbis()) {
            System.out.printf("- %s: %d de vida (Vivo: %s)\n", z.getNome(), z.getVida(), z.estaVivo());
        }
    }
}
