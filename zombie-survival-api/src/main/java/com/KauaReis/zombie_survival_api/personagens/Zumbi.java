package com.KauaReis.zombie_survival_api.personagens;

import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

public class Zumbi extends Personagem{
    private int ataque;
    private String nome;
    private String tipo;
    private int vida;
    private int forca;
    private int velocidade;
    private int nivel;
    private boolean vivo;
    private List<String> efeitos;
    private boolean infectado;
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

    public TipoZumbi getTipoZumbi() {
        return tipoZumbi;
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

    // Getters caso precise usar externamente
    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getForca() {
        return forca;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setInfectado(boolean infectado) {
        this.infectado = infectado;
    }
}
