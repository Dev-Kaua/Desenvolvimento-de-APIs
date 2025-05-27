package com.KauaReis.zombie_survival_api.personagens;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Zumbi extends Personagem{
    private int ataque;
    // Getters caso precise usar externamente
    @Setter
    @Getter
    private String nome;
    private String tipo;
    @Setter
    @Getter
    private int vida;
    @Getter
    private int forca;
    @Getter
    private int velocidade;
    @Getter
    private int nivel;
    private boolean vivo;
    private List<String> efeitos;
    @Setter
    private boolean infectado;
    @Getter
    private TipoZumbi tipoZumbi;


    // ✅ Construtor principal
    public Zumbi(String nome, TipoZumbi tipoZumbi, int vida, int ataque, int velocidade, boolean infectado) {
        super(nome, vida);
        this.tipoZumbi = tipoZumbi;
        this.ataque = ataque;
        this.velocidade = velocidade;
        this.infectado = infectado;
        this.efeitos = new ArrayList<>();
    }

    // 🧱 Construtor simplificado para zumbis convertidos
    public Zumbi(String nomeOriginal) {
        this(nomeOriginal + " (Zumbi)", TipoZumbi.NORMAL, 100, 10, 5, true);
    }

    // 🧱 Construtor rápido para testes
    public Zumbi(String nome, TipoZumbi tipoZumbi) {
        this(nome, tipoZumbi, 100, 10, 5, false);
    }

    public enum TipoZumbi {
        NORMAL,
        AVANCADO,
        MUTANTE
    }

    public void atacar(Sobrevivente alvo) {
        if (!vivo) {
            System.out.println(nome + " está morto e não pode atacar.");
            return;
        }

        System.out.println(nome + " ataca " + alvo.getNome() + " com força " + forca + "!");
        alvo.receberDano(forca);
    }

    public void receberDano(int dano) {
        if (!vivo) return;

        vida -= dano;
        System.out.println(nome + " recebeu " + dano + " de dano! Vida restante: " + vida);

        if (vida <= 0) {
            morrer();
        }
    }

    public void morrer() {
        vivo = false;
        vida = 0;
        System.out.println(nome + " foi destruído!");
    }

    public void evoluir() {
        if (!vivo) return;

        nivel++;
        forca += 2;
        vida += 10;
        velocidade += 1;

        System.out.println(nome + " evoluiu para o nível " + nivel + "! Mais forte e mais rápido!");
    }

    public void aplicarEfeito(String efeito) {
        if (!efeitos.contains(efeito)) {
            efeitos.add(efeito);
            System.out.println(nome + " foi afetado por: " + efeito);
        }
    }

    public void decidirAcao(Sobrevivente alvo) {
        if (!vivo) return;

        if (vida < 20 && nivel < 3) {
            evoluir();
        } else {
            atacar(alvo);
        }
    }

    public boolean estaVivo() {
        return vivo;
    }



    public String getStatus() {
        return "Zumbi: " + nome + "\n" +
                "Vida: " + vida + "\n" +
                "Ataque: " + ataque + "\n" +
                "Nível: " + nivel + "\n" +
                "Status: " + (vivo ? "Vivo" : "Morto");
    }

}
