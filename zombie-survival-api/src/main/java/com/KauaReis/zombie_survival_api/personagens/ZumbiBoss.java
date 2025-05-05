package com.KauaReis.zombie_survival_api.personagens;

import java.util.List;

public class ZumbiBoss extends Zumbi {

    public ZumbiBoss(String nome) {
        super(nome);
        this.setVida(300);
    }

    public void atacarGrupo(List<Sobrevivente> sobreviventes) {
        System.out.println("👑 " + getNome() + " ataca todos os sobreviventes!");
        for (Sobrevivente s : sobreviventes) {
            s.receberDano(40);
        }
    }

    @Override
    public void morrer() {
        System.out.println("🔥 Zumbi Boss " + getNome() + " foi derrotado! A cidade vibra.");
    }
}
