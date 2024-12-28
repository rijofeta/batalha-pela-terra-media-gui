package projeto.projetofinalgui.batalha;


import projeto.projetofinalgui.exercito.Exercito;
import projeto.projetofinalgui.models.personagens.bestas.Besta;
import projeto.projetofinalgui.models.personagens.herois.Heroi;

import java.util.Iterator;

public class Batalha {

    private final BatalhaDialogo dialogo = new BatalhaDialogo();

    public void batalhar(Exercito<Heroi> herois, Exercito<Besta> bestas) throws InterruptedException {
        int turno = 0;
        int potOfensiva;
        int danoEfetivo;
        while (true) {
            turno += 1;
            dialogo.print("Turno " + turno + ":");
            Thread.sleep(1500);

            Iterator<Heroi> itHerois = herois.iterator();
            Iterator<Besta> itBestas = bestas.iterator();
            while (itHerois.hasNext() && itBestas.hasNext()) {
                Heroi heroi = itHerois.next(); // obter próximo Heroi na linha
                Besta besta = itBestas.next(); // obter próxima Besta na linha
                dialogo.lutaEntre(heroi, besta); // informar sobre a luta
                Thread.sleep(1500);
                // Heroi ataca Besta
                potOfensiva = heroi.obterPotenciaOfensivaEspecial(besta); // calcular potencia ofensiva
                danoEfetivo = besta.serAtacado(potOfensiva, heroi); // calcular dano efetivo
                dialogo.informarAtaque(heroi.getNome(), besta.getNome(), potOfensiva, danoEfetivo); // informar do ataque
                Thread.sleep(1500);
                if(besta.getVida() <= 0) { // se a besta não tiver vida
                    dialogo.informarMorte(besta); //informar morete
                    continue; // não pode atacar; continua para a próxima luta
                }
                // Besta ataca Heroi
                potOfensiva = besta.obterPotenciaOfensivaEspecial(heroi);
                danoEfetivo = heroi.serAtacado(potOfensiva, besta);
                dialogo.informarAtaque(besta.getNome(), heroi.getNome(), potOfensiva, danoEfetivo);
                Thread.sleep(1500);
                if(heroi.getVida() <= 0) { // se o heroi não tiver vida
                    dialogo.informarMorte(heroi); // informar morte
                }
            }
            herois.removerMortos();
            bestas.removerMortos();
            if (herois.getListaExercito().isEmpty()) {
                dialogo.print("VITORIA DAS BESTAS!!!");
                return;
            } else if (bestas.getListaExercito().isEmpty()) {
                dialogo.print("VITORIA DOS HERÓIS!!!");
                return;
            }
        }
    }

    public BatalhaDialogo getDialogo() {
        return dialogo;
    }
}
