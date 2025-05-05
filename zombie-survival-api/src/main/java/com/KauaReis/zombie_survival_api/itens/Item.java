package com.KauaReis.zombie_survival_api.itens;

public class Item {
    private String nome;
    private String tipo;

    // Construtores, getters e setters
    public Item(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
