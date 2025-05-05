package com.KauaReis.zombie_survival_api.personagens;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Personagem {
    @Id
    private Long id;
    private String nome;
    private int vida;
    private int energia;  // Nova propriedade de energia
    private int nivel;    // Nível do personagem (pode representar experiência)


    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    // Método para o personagem receber dano
    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            System.out.println(this.nome + " foi derrotado!");
            this.vida = 0;  // Impede que a vida fique negativa
        }
    }

    // Método para atacar
    public void atacar(Personagem outro) {
        if (energia >= 10) {
            outro.setVida(outro.getVida() - 10);  // Ataque básico
            this.setEnergia(this.getEnergia() - 5);  // Consome energia ao atacar
            System.out.println(nome + " atacou " + outro.getNome());
        } else {
            System.out.println(nome + " está sem energia suficiente para atacar.");
        }
    }

    // Método para descansar e recuperar energia
    public void descansar() {
        this.setEnergia(this.getEnergia() + 10);  // Recupera energia
        System.out.println(nome + " descansou e recuperou energia.");
    }
}
