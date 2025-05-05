package com.KauaReis.zombie_survival_api.armas;

import com.KauaReis.zombie_survival_api.itens.Item;

public abstract class Arma extends Item {
    protected int danoBase;
    protected double chanceAcerto; // 0.0 a 1.0
    protected double chanceCritico; // 0.0 a 1.0
    protected double multiplicadorCritico; // Ex: 1.5, 2.0

    public Arma(String nome, int danoBase, double chanceAcerto, double chanceCritico, double multiplicadorCritico) {
        super(nome, "Arma");
        this.danoBase = danoBase;
        this.chanceAcerto = chanceAcerto;
        this.chanceCritico = chanceCritico;
        this.multiplicadorCritico = multiplicadorCritico;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public void setDanoBase(int danoBase) {
        this.danoBase = danoBase;
    }

    public double getChanceAcerto() {
        return chanceAcerto;
    }

    public void setChanceAcerto(double chanceAcerto) {
        this.chanceAcerto = chanceAcerto;
    }

    public double getChanceCritico() {
        return chanceCritico;
    }

    public void setChanceCritico(double chanceCritico) {
        this.chanceCritico = chanceCritico;
    }

    public double getMultiplicadorCritico() {
        return multiplicadorCritico;
    }

    public void setMultiplicadorCritico(double multiplicadorCritico) {
        this.multiplicadorCritico = multiplicadorCritico;
    }

    /**
     * Calcula o dano baseado em chance de acerto e chance de crítico.
     * Retorna 0 se o ataque falhar.
     */
    public int calcularDano() {
        if (Math.random() > chanceAcerto) {
            return 0; // Errou o ataque
        }

        boolean critico = Math.random() < chanceCritico;
        if (critico) {
            return (int) (danoBase * multiplicadorCritico);
        } else {
            return danoBase;
        }
    }

    @Override
    public String toString() {
        return getNome() + " [Dano base: " + danoBase +
                ", Acerto: " + (int)(chanceAcerto * 100) + "%" +
                ", Crítico: " + (int)(chanceCritico * 100) + "% x" + multiplicadorCritico + "]";
    }
}
