package projeto.projetofinalgui.exercito;

import projeto.projetofinalgui.models.personagens.Personagem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Exercito<T extends Personagem> implements Iterable {
    private List<T> listaExercito;

    public Exercito() {
        listaExercito = new ArrayList<>();
    }

    public void removerMortos() {
        listaExercito.removeIf(t -> t.getVida() <= 0);
    }

    public List<T> getListaExercito() {
        return listaExercito;
    }

    public void addAll(Collection<T> selecionados) {
        listaExercito.addAll(selecionados);
    }

    @Override
    public String toString() {
        return listaExercito.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return listaExercito.iterator();
    }
}
