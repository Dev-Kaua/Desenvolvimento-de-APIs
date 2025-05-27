package com.KauaReis.zombie_survival_api.itens;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {
    private String nome;
    private String tipo;

    // Construtores, getters e setters
    public Item(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

}
