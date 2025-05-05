package com.KauaReis.zombie_survival_api.util;
import com.KauaReis.zombie_survival_api.personagens.Sobrevivente;
import com.KauaReis.zombie_survival_api.personagens.Zumbi;

import java.util.Scanner;

public class Batalha {
    public void iniciar(Sobrevivente sobrevivente, Zumbi zumbi) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🔥 A batalha começa!");

        while (sobrevivente.estaVivo() && zumbi.estaVivo()) {
            System.out.println("\n===== TURNO DO SOBREVIVENTE =====");
            System.out.println("1. Atacar");
            System.out.println("2. Usar item (futuramente)");
            System.out.println("Escolha sua ação: ");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                sobrevivente.atacar(zumbi);
            } else {
                System.out.println("Ação inválida.");
            }

            if (!zumbi.estaVivo()) break;

            System.out.println("\n===== TURNO DO ZUMBI =====");
            zumbi.decidirAcao(sobrevivente);  // comportamento autônomo
        }

        System.out.println("\n🎉 A batalha terminou!");
        if (sobrevivente.estaVivo()) {
            System.out.println("✅ Sobrevivente venceu!");
        } else {
            System.out.println("☠️ O zumbi venceu...");
        }

        scanner.close();
    }
}
