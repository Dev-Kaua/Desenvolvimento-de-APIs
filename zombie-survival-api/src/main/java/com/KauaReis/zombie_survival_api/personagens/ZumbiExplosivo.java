package com.KauaReis.zombie_survival_api.personagens;

public class ZumbiExplosivo extends Zumbi {

    public ZumbiExplosivo(String nome) {
        super(nome);
        this.setVida(80);
    }

    @Override
    public void morrer() {
        super.morrer();
        System.out.println("💥 " + getNome() + " explodiu ao morrer e causou dano em área!");
        // Você pode ter uma lista de sobreviventes próxima e causar dano neles
    }
}
