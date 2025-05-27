package com.KauaReis.zombie_survival_api.personagens;

import com.KauaReis.zombie_survival_api.armas.Arma;
import com.KauaReis.zombie_survival_api.armas.ArmaDeFogo;
import com.KauaReis.zombie_survival_api.itens.Item;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Sobrevivente extends Personagem {

    // STATUS
    @Setter @Getter private boolean infectado;
    @Setter @Getter private boolean mordido;
    @Setter @Getter private boolean temAntidoto;
    @Setter @Getter private boolean curado;
    @Setter @Getter private LocalDateTime tempoInfeccao;
    private boolean vivo = true;

    // BARRAS
    @Setter
    @Getter
    private int saude;
    @Getter
    private int fome;
    @Getter
    private int sede;
    private int ataque;
    private int velocidade;
    @Getter
    private int bandagens;

    // GETTERS EXTRAS
    // INVENTÁRIO E EQUIPAMENTO
    @Getter
    private List<Item> inventario = new ArrayList<>();
    @Getter
    private Arma armaEquipada;

    // CONSTRUTORES
    public Sobrevivente(String nome, int vida) {
        super(nome, 100);
        this.saude = 100;
        this.fome = 100;
        this.sede = 100;
        this.ataque = 15;
        this.velocidade = 10;
        this.bandagens = 2;
    }

    public Sobrevivente(String nome) {
        this(nome, 100);
    }

    // MÉTODOS DE BARRAS
    public void restaurarFome(int quantidade) {
        this.fome = Math.min(100, this.fome + quantidade);
    }

    public void restaurarSede(int quantidade) {
        this.sede = Math.min(100, this.sede + quantidade);
    }

    public void curarSaude(int cura) {
        this.saude = Math.min(100, this.saude + cura);
    }

    public void removerInfeccao() {
        this.infectado = false;
    }

    // INVENTÁRIO
    public void procurarItem(Item item) {
        System.out.println(this.getNome() + " encontrou um(a) " + item.getNome() + "!");
        inventario.add(item);
    }

    // EQUIPAR ARMA
    public void equiparArma(Arma arma) {
        if (inventario.contains(arma)) {
            this.armaEquipada = arma;
            System.out.println(this.getNome() + " equipou " + arma.getNome());
        } else {
            System.out.println("Arma não está no inventário!");
        }
    }

    // ATAQUE COM ARMA EQUIPADA
    public void atacar(Zumbi zumbi) {
        if (armaEquipada == null) {
            System.out.println(this.getNome() + " está desarmado e socou " + zumbi.getNome());
            atacarCorpoACorpo(zumbi);
            return;
        }

        int danoCausado = armaEquipada.calcularDano();

        if (armaEquipada instanceof ArmaDeFogo armaDeFogo) {
            if (!armaDeFogo.temMunicao()) {
                System.out.println(this.getNome() + " tentou atirar com " + armaDeFogo.getNome() + ", mas está sem munição!");
                return;
            }
            armaDeFogo.calcularDano(); // já decrementa munição
        }

        if (danoCausado == 0) {
            System.out.println(this.getNome() + " errou o ataque!");
        } else {
            System.out.println(this.getNome() + " acertou " + zumbi.getNome() + " com " + armaEquipada.getNome() + " causando " + danoCausado + " de dano!");
            zumbi.receberDano(danoCausado);
        }

        if (this.estaVivo() && zumbi.estaVivo()) {
            zumbi.receberDano(ataque);
            System.out.println(getNome() + " atacou " + zumbi.getNome() + " causando " + ataque + " de dano.");
        }
    }

    public void curar() {
        if (bandagens > 0 && getVida() < 100) {
            int cura = 20;
            setVida(Math.min(getVida() + cura, 100));
            bandagens--;
            System.out.println(getNome() + " usou uma bandagem e recuperou " + cura + " de vida! Vida atual: " + getVida());
        }
    }

    // ATAQUE SEM ARMA
    public void atacarCorpoACorpo(Zumbi zumbi) {
        Random random = new Random();
        int dano = random.nextInt(10) + 5;
        System.out.println(this.getNome() + " deu um soco em " + zumbi.getNome() + " causando " + dano + " de dano!");
        zumbi.receberDano(dano);
    }

    // ANTÍDOTO
    public void tomarAntidoto() {
        if (infectado && !temAntidoto) {
            temAntidoto = true;
            infectado = false;
            System.out.println(this.getNome() + " tomou o antídoto e se curou!");
        } else {
            System.out.println(this.getNome() + " não está infectado ou já tomou o antídoto.");
        }
    }

    // VERIFICAR PRAZO DE INFECÇÃO
    public void verificarPrazoInfecao() {
        if (infectado && tempoInfeccao != null) {
            LocalDateTime agora = LocalDateTime.now();
            if (tempoInfeccao.plusHours(24).isBefore(agora) && !temAntidoto) {
                System.out.println(this.getNome() + " não conseguiu tomar o antídoto a tempo e morreu!");
                this.setVida(0);
            }
        }
    }

    public boolean estaVivo() {
        return this.vivo;
    }

    // INFECÇÃO AO SER MORDIDO
    public void serAtacadoPorZumbi(Zumbi zumbi) {
        Random random = new Random();
        if (random.nextInt(100) < 50) {
            this.mordido = true;
            System.out.println(this.getNome() + " foi mordido por " + zumbi.getNome());

            if (random.nextInt(100) < 70) {
                this.infectado = true;
                this.tempoInfeccao = LocalDateTime.now();
                System.out.println(this.getNome() + " foi infectado!");
            }
        }
    }

    // MORTE E TRANSFORMAÇÃO
    public void morrer() {
        if (this.getVida() <= 0) {
            this.vivo = false;
            Zumbi zumbi = new Zumbi(this.getNome());
            zumbi.setNome(this.getNome());
            zumbi.setVida(100);
            zumbi.setInfectado(true);
            System.out.println(this.getNome() + " morreu e virou um zumbi!");
        }
    }

}


