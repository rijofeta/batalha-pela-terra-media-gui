package projeto.projetofinalgui.models.personagens;

public abstract class Personagem {
    protected final String nome;
    protected int vida;
    protected int armadura;

    public Personagem(String nome, int vida, int armadura) {
        this.nome = nome;
        this.vida = vida;
        this.armadura = armadura;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getArmadura() {
        return armadura;
    }

    protected abstract int obterPotenciaOfensivaBase();

    public abstract int obterPotenciaOfensivaEspecial(Personagem atacado);

    public abstract int serAtacado(int potenciaOfensiva, Personagem atacante);

    @Override
    public String toString() {
        return nome + " (Vida = " + vida + " Armadura = " + armadura + ")";
    }

}
