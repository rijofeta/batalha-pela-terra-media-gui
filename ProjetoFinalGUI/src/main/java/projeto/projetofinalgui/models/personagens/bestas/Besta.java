package projeto.projetofinalgui.models.personagens.bestas;


import projeto.projetofinalgui.models.personagens.Personagem;

public abstract class Besta extends Personagem {

    public Besta(String nome, int vida, int armadura) {
        super(nome, vida, armadura);
    }

    @Override
    protected int obterPotenciaOfensivaBase() {
        // Potência base de Besta é um número aleatório de 0 a 90 inclusive
        return (int) (Math.random() * 91);
    }
}
