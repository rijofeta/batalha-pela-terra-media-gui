package projeto.projetofinalgui.batalha;


import javafx.scene.control.TextArea;
import projeto.projetofinalgui.models.personagens.Personagem;

import java.io.PrintStream;

public class BatalhaDialogo {

    private final PrintStream printer = System.out;
    private TextArea textAreaBatalha;

    public void lutaEntre(Personagem personagem1, Personagem personagem2) {
        printer.format("\tLuta entre %s e %s:%n", personagem1, personagem2);
        textAreaBatalha.appendText(String.format("\tLuta entre %s e %s:%n", personagem1, personagem2));
    }

    public void informarAtaque(String atacante, String atacado, int potenciaOfensiva, int danoEfetivo) {
        printer.format(
                "\t\t%s obteve %d pontos de ataque e tirou %d a %s%n",
                atacante,
                potenciaOfensiva,
                danoEfetivo,
                atacado);
        textAreaBatalha.appendText(String.format("\t\t%s obteve %d pontos de ataque e tirou %d a %s%n",
                atacante,
                potenciaOfensiva,
                danoEfetivo,
                atacado));
    }

    public void print(String message) {
        printer.println(message);
        textAreaBatalha.appendText(message + "\n");
    }

    public void informarMorte(Personagem personagem) {
        printer.format("\tMorreu o %s %s!%n", personagem.getClass().getSimpleName(), personagem.getNome());
        textAreaBatalha.appendText(String.format("\tMorreu o %s %s!%n", personagem.getClass().getSimpleName(), personagem.getNome()));
    }

    public void setTextArea(TextArea textArea) {
        this.textAreaBatalha = textArea;
    }
}
