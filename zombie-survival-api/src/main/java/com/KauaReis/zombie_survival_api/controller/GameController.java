package com.KauaReis.zombie_survival_api.controller;

import com.KauaReis.zombie_survival_api.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> iniciarJogo() {
        gameService.iniciarJogo();
        return ResponseEntity.ok("🎮 Jogo iniciado!");
    }

    @GetMapping("/status")
    public ResponseEntity<String> statusJogo() {
        String status = gameService.obterStatusFormatado(); // vamos criar esse método no GameService
        return ResponseEntity.ok(status);
    }

    @PostMapping("/next-round")
    public ResponseEntity<String> proximaRodada() {
        boolean rodadaExecutada = gameService.executarRodadaManual(); // vamos adicionar isso também
        if (!rodadaExecutada) {
            return ResponseEntity.ok("⛔ O jogo não está ativo ou já terminou.");
        }
        return ResponseEntity.ok("✅ Rodada executada!");
    }

    @PostMapping("/restart")
    public ResponseEntity<String> reiniciarJogo() {
        gameService.reiniciarJogo();
        return ResponseEntity.ok("🔄 Jogo reiniciado com novos personagens.");
    }
}
