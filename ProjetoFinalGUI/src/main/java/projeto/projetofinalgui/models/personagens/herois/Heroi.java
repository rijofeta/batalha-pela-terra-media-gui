package projeto.projetofinalgui.models.personagens.herois;


import projeto.projetofinalgui.models.personagens.Personagem;

public abstract class Heroi extends Personagem {

    public Heroi(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    protected int obterPotenciaOfensivaBase() {
        // Potência base de Heroi é o máximo entre dois números aleatórios de 0 a 100 inclusive
        return Math.max((int) (Math.random() * 101), (int) (Math.random() * 101));
    }
}
