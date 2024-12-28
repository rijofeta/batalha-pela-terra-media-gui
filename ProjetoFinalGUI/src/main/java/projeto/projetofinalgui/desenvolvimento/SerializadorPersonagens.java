package projeto.projetofinalgui.desenvolvimento;



import projeto.projetofinalgui.models.dto.PersonagemDTO;
import projeto.projetofinalgui.models.personagens.bestas.Orque;
import projeto.projetofinalgui.models.personagens.bestas.Troll;
import projeto.projetofinalgui.models.personagens.herois.Elfo;
import projeto.projetofinalgui.models.personagens.herois.Hobbit;
import projeto.projetofinalgui.models.personagens.herois.Humano;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 Esta classe foi apenas usada na fase de desenvolvimento para serializar as personagens com os valores definidos
 dos atributos e criar os ficheiros herois.ser e bestas.ser.
 Deixei-a no projeto para mostrar o processo, mas não tem utilidade no código final.
 */
public class SerializadorPersonagens {

    private static String FICHEIRO_HEROIS = "src/main/resources/projeto/projetofinalgui/personagens_ser/herois.ser";
    private static String FICHEIRO_BESTAS = "src/main/resources/projeto/projetofinalgui/personagens_ser/bestas.ser";

    public static void serializarHerois() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FICHEIRO_HEROIS))) {
            List<PersonagemDTO> herois = new ArrayList<>(List.of(
                    new PersonagemDTO(1, "Legolas", 150, 30, Elfo.class, true),
                    new PersonagemDTO(2, "Aragorn", 150, 50, Humano.class, true),
                    new PersonagemDTO(3, "Boromir", 100, 60, Humano.class, true),
                    new PersonagemDTO(4, "Gandalf", 300, 30, Humano.class, true),
                    new PersonagemDTO(5, "Frodo", 20, 15, Hobbit.class, true),
                    new PersonagemDTO(6, "Elfo Comum", 100, 20, Elfo.class, false),
                    new PersonagemDTO(7, "Humano Comum", 90, 40, Humano.class, false),
                    new PersonagemDTO(8, "Hobbit Comum", 18, 10, Hobbit.class, false)
            ));
            objectOutputStream.writeObject(herois);
        } catch (IOException e) {
            System.err.println("Não foi possível realizar a operação I/O.");
            e.printStackTrace();
        }
    }

    public static void serializarBestas() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FICHEIRO_BESTAS))) {
            List<PersonagemDTO> herois = new ArrayList<>(List.of(
                    new PersonagemDTO(1, "Lurtz", 200, 60, Orque.class, true),
                    new PersonagemDTO(2, "Shagrat", 170, 50, Orque.class, true),
                    new PersonagemDTO(3, "Uglúk", 220, 45, Orque.class, true),
                    new PersonagemDTO(4, "Mauhúr", 250, 40, Orque.class, true),
                    new PersonagemDTO(5, "Orque Comum", 150, 35, Orque.class, false),
                    new PersonagemDTO(6, "Olog", 400, 30, Troll.class, false),
                    new PersonagemDTO(7, "Troll da Mtn.", 300, 20, Troll.class, false)
            ));
            objectOutputStream.writeObject(herois);
        } catch (IOException e) {
            System.err.println("Não foi possível realizar a operação I/O.");
            e.printStackTrace();
        }
    }
}
