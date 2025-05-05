package com.KauaReis.zombie_survival_api.armas;

public class ArmaDeFogo extends Arma {
    private int capacidadeMunicao;
    private int municaoAtual;

    public ArmaDeFogo(String nome, int danoBase, double chanceAcerto, double chanceCritico, double multiplicadorCritico, int capacidadeMunicao) {
        super(nome, danoBase, chanceAcerto, chanceCritico, multiplicadorCritico);
        this.capacidadeMunicao = capacidadeMunicao;
        this.municaoAtual = capacidadeMunicao; // Começa carregada
    }

    public int getCapacidadeMunicao() {
        return capacidadeMunicao;
    }

    public void setCapacidadeMunicao(int capacidadeMunicao) {
        this.capacidadeMunicao = capacidadeMunicao;
    }

    public int getMunicaoAtual() {
        return municaoAtual;
    }

    public void setMunicaoAtual(int municaoAtual) {
        this.municaoAtual = Math.min(municaoAtual, capacidadeMunicao);
    }

    public boolean temMunicao() {
        return municaoAtual > 0;
    }

    public void recarregar() {
        this.municaoAtual = capacidadeMunicao;
        System.out.println(getNome() + " recarregada!");
    }

    @Override
    public int calcularDano() {
        if (municaoAtual <= 0) {
            System.out.println(getNome() + " está sem munição!");
            return 0;
        }

        municaoAtual--;

        return super.calcularDano();
    }

    @Override
    public String toString() {
        return super.toString() + " | Munição: " + municaoAtual + "/" + capacidadeMunicao;
    }
}
