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
public class ZumbiService {

    private final List<Zumbi> zumbis = new ArrayList<>();
    private final List<Sobrevivente> sobreviventes = new ArrayList<>();
    private final SobreviventeService sobreviventeService;

    public ZumbiService(@Lazy SobreviventeService sobreviventeService) {
        this.sobreviventeService = sobreviventeService;
    }

    // Simulação: você pode usar repositórios futuramente
    public void adicionarZumbi(Zumbi zumbi) {
        zumbis.add(zumbi);
    }

    public void adicionarSobrevivente(Sobrevivente s) {
        sobreviventes.add(s);
    }

    public void zerar() {
        zumbis.clear(); // ou sobreviventes.clear();
    }

    public void realizarTurno() {
        List<Zumbi> zumbisVivos = zumbis.stream().filter(Zumbi::estaVivo).toList();
        List<Sobrevivente> sobreviventesVivos = sobreviventeService.listarSobreviventes()
                .stream().filter(Sobrevivente::estaVivo).toList();

        for (Zumbi z : zumbisVivos) {
            if (!sobreviventesVivos.isEmpty()) {
                Sobrevivente alvo = sobreviventesVivos.get(new Random().nextInt(sobreviventesVivos.size()));
                z.atacar(alvo);
            }
        }
    }


    private Sobrevivente escolherSobreviventeAleatorio() {
        List<Sobrevivente> vivos = sobreviventes.stream()
                .filter(Sobrevivente::estaVivo)
                .toList();
        if (vivos.isEmpty()) return null;
        return vivos.get(new Random().nextInt(vivos.size()));
    }

    public List<Zumbi> listarZumbis() {
        return new ArrayList<>(zumbis);
    }

    public List<Sobrevivente> listarSobreviventes() {
        return new ArrayList<>(sobreviventes);
    }

    @Scheduled(fixedRate = 5000) // Executa a cada 5 segundos
    public void atacarSobreviventes() {
        System.out.println("\n🧟 Rodada de ataque dos zumbis!");

        for (Zumbi z : zumbis) {
            if (z.estaVivo()) {
                // Pega um sobrevivente vivo aleatório
                List<Sobrevivente> vivos = sobreviventes.stream()
                        .filter(Sobrevivente::estaVivo)
                        .toList();

                if (!vivos.isEmpty()) {
                    Sobrevivente alvo = escolherSobreviventeAleatorio();
                    z.atacar(alvo);
                    if (!alvo.estaVivo()) {
                        // sobreviveu -> morreu -> virou zumbi
                        Zumbi novoZumbi = new Zumbi(alvo.getNome(), Zumbi.TipoZumbi.NORMAL);
                        zumbis.add(novoZumbi);
                        System.out.println("⚠️ Novo zumbi foi adicionado: " + novoZumbi.getNome());
                    }
                } else {
                    System.out.println("Nenhum sobrevivente restante!");
                }
            }
        }
    }
}
