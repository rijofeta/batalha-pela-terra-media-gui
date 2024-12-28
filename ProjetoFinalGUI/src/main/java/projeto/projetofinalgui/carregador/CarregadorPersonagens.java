package projeto.projetofinalgui.carregador;


import projeto.projetofinalgui.models.dto.PersonagemDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class CarregadorPersonagens {

    public static List<PersonagemDTO> carregarPersonagens(String ficheiroSer) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ficheiroSer))) {
            @SuppressWarnings("unchecked")
            List<PersonagemDTO> herois = (List<PersonagemDTO>) objectInputStream.readObject();
            return herois;
        } catch (IOException e) {
            System.err.println("Não foi possível realizar a operação I/O.");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível encontrar a classe do objeto a desserializar.");
            System.err.println("Verifique se a classe PersonagemDTO existe no pojeto.");
            System.err.println("Verifique se os ficheiros de DTOs de personagens foram serializados corretamente.");
            System.exit(1);
        }
        return null;
    }
}
