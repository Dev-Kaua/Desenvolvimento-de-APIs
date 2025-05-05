package com.KauaReis.zombie_survival_api.service;

import com.KauaReis.zombie_survival_api.personagens.Sobrevivente;
import com.KauaReis.zombie_survival_api.personagens.Zumbi;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SobreviventeService {

    private final List<Sobrevivente> sobreviventes = new ArrayList<>();
    private final List<Zumbi> zumbis = new ArrayList<>();
    private final Random random = new Random();
    private final ZumbiService zumbiService;

    public SobreviventeService(@Lazy ZumbiService zumbiService) {
        this.zumbiService = zumbiService;
    }

    public void adicionarSobrevivente(Sobrevivente s) {
        sobreviventes.add(s);
    }

    public void adicionarZumbi(Zumbi z) {
        zumbis.add(z);
    }

    private Zumbi escolherZumbiAleatorio() {
        List<Zumbi> vivos = zumbis.stream().filter(Zumbi::estaVivo).toList();
        if (vivos.isEmpty()) return null;
        return vivos.get(random.nextInt(vivos.size()));
    }

    public void zerar() {
        zumbis.clear(); // ou sobreviventes.clear();
    }

    public void realizarTurno() {
        List<Sobrevivente> vivos = sobreviventes.stream().filter(Sobrevivente::estaVivo).toList();
        List<Zumbi> zumbisVivos = zumbiService.listarZumbis()
                .stream().filter(Zumbi::estaVivo).toList();

        for (Sobrevivente s : vivos) {
            if (!zumbisVivos.isEmpty()) {
                Zumbi alvo = zumbisVivos.get(new Random().nextInt(zumbisVivos.size()));
                s.atacar(alvo);
            }
        }
    }


    @Scheduled(fixedRate = 5000)
    public void rodadaDosSobreviventes() {
        System.out.println("\n🧍 Ação dos sobreviventes:");

        for (Sobrevivente s : sobreviventes) {
            if (s.estaVivo()) {
                // Se a vida estiver abaixo de 50%, tenta se curar
                if (s.getVida() < 50 && s.getBandagens() > 0) {
                    s.curar();
                } else {
                    Zumbi alvo = escolherZumbiAleatorio();
                    if (alvo != null) {
                        s.atacar(alvo);
                        if (!alvo.estaVivo()) {
                            System.out.println("☠️ Zumbi derrotado: " + alvo.getNome());
                        }
                    }
                }
            }
        }
    }

    public List<Sobrevivente> listarSobreviventes() {
        return new ArrayList<>(sobreviventes);
    }

    public List<Zumbi> listarZumbis() {
        return new ArrayList<>(zumbis);
    }
}
