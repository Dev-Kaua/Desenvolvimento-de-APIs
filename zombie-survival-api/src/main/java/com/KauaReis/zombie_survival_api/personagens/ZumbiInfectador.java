package com.KauaReis.zombie_survival_api.personagens;


public class ZumbiInfectador extends Zumbi {

    public ZumbiInfectador(String nome) {
        super(nome);
        this.setVida(90);
    }

    @Override
    public void atacar(Sobrevivente s) {
        super.atacar(s);
        if (s.getVida() < 50) {
            System.out.println("☣️ " + getNome() + " infectou " + s.getNome() + "!");
            s.morrer(); // transforma em zumbi
        }
    }
}
