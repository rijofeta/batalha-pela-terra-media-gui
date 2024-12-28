package projeto.projetofinalgui.models.personagens.herois;


import projeto.projetofinalgui.models.personagens.Personagem;
import projeto.projetofinalgui.models.personagens.bestas.Troll;

public class Hobbit extends Heroi {

    public Hobbit(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    public int obterPotenciaOfensivaEspecial(Personagem atacado) {
        // Calcular potencia ofensiva base
        int potenciaOfensiva = obterPotenciaOfensivaBase();
        // Lógica especial
        if (atacado instanceof Troll) potenciaOfensiva -= 5;

        return potenciaOfensiva;
    }

    @Override
    public int serAtacado(int potenciaOfensiva, Personagem atacante) {
        // Calcular multiplicador
        double multiplicador = 1;
        if (atacante instanceof Troll) multiplicador = 0.90;
        // Calcular dano efetivo
        int danoEfetivo = 0;
        if (potenciaOfensiva > multiplicador * this.armadura) {
            danoEfetivo = (int) (potenciaOfensiva - multiplicador * this.armadura);
        }
        // Retirar pontos de vida
        this.vida -= danoEfetivo;
        return danoEfetivo;
    }


}
