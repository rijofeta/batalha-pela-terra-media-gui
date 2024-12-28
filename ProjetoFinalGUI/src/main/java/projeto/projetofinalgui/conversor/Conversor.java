package projeto.projetofinalgui.conversor;




import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import projeto.projetofinalgui.models.dto.PersonagemDTO;
import projeto.projetofinalgui.models.personagens.Personagem;

import java.io.InvalidClassException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Conversor {

    @Nullable
    public static <T extends Personagem> T converterPersonagem(Class<T> classeAlvo, PersonagemDTO dto) {
        try {
            Personagem personagem = dto.tipoClass()
                    .getDeclaredConstructor(String.class, int.class, int.class)
                    .newInstance(dto.nome(), dto.vida(), dto.armadura());
            if (classeAlvo.isInstance(personagem)) {
                return classeAlvo.cast(personagem);

            } else {
                String mensagem = String.format(
                        "A classe alvo para conversão do DTO (%s) não é instância da classe alvo do método (%s).",
                        dto.tipoClass().getSimpleName(), classeAlvo.getSimpleName());
                throw new InvalidClassException(mensagem);
            }
        } catch (InstantiationException e) {
            System.err.println("Não foi possível criar uma instância de " + dto.tipoClass().getSimpleName());
            System.out.println("Verifique se a classe é instanciável.");
        } catch (IllegalAccessException e) {
            System.err.println("Não foi possível criar uma instância de " + dto.tipoClass().getSimpleName());
            System.out.println("Verifique se o construtor obtido por reflexão é acessível.");
        } catch (InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Não foi possível criar uma instância de " + dto.tipoClass().getSimpleName());
            System.out.println("Verifique se o construtor obtido por reflexão coincide com o construtor da classe.");
        } catch (InvalidClassException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Nonnull
    public static <T extends Personagem> List<T> converterPersonagens(Class<T> classeAlvo, List<PersonagemDTO> dtos) {
        ArrayList<T> list = new ArrayList<>();
        for (PersonagemDTO dto : dtos) {
            T perConv = converterPersonagem(classeAlvo, dto);
            if (perConv != null) list.add(perConv);
        }
        return list;
    }
}
