package projeto.projetofinalgui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import projeto.projetofinalgui.carregador.CarregadorPersonagens;
import projeto.projetofinalgui.conversor.Conversor;
import projeto.projetofinalgui.exercito.Exercito;
import projeto.projetofinalgui.models.dto.PersonagemDTO;
import projeto.projetofinalgui.models.personagens.bestas.Besta;
import projeto.projetofinalgui.models.personagens.herois.Heroi;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SceneSelecaoController {

    private final String MUSICA_PATH_URI =
            Path.of("src/main/resources/projeto/projetofinalgui/musica/tema_selecao.mp3").toUri().toString();

    @FXML
    private ListView<PersonagemDTO> listHeroisDisponiveis;

    @FXML
    private ListView<PersonagemDTO> listBestasDisponiveis;

    @FXML
    private ListView<PersonagemDTO> listHeroisSelecionados;

    @FXML
    private ListView<PersonagemDTO> listBestasSelecionados;

    @FXML
    private Button botaoSelecionarHeroi;

    @FXML
    private Button botaoSelecionarBesta;

    @FXML
    private Button botaoRemoverHeroi;

    @FXML
    private Button botaoRemoverBesta;

    @FXML
    private Button botaoBatalha;

    private MediaPlayer mediaPlayer;

    private ObservableList<PersonagemDTO> heroisDisponiveis;
    private ObservableList<PersonagemDTO> bestasDisponiveis;
    private ObservableList<PersonagemDTO> heroisSelecionados;
    private ObservableList<PersonagemDTO> bestasSelecionados;


    public void initialize() {
        // Instanciar as ObservableLists
        heroisDisponiveis = FXCollections.observableList(CarregadorPersonagens.carregarPersonagens("src/main/resources/projeto/projetofinalgui/personagens_ser/herois.ser"));
        bestasDisponiveis = FXCollections.observableList(CarregadorPersonagens.carregarPersonagens("src/main/resources/projeto/projetofinalgui/personagens_ser/bestas.ser"));
        heroisSelecionados = FXCollections.observableArrayList();
        bestasSelecionados = FXCollections.observableArrayList();
        // Passar os items das ObservableLists às ListViews
        listHeroisDisponiveis.setItems(heroisDisponiveis.filtered(Objects::nonNull)); // Lista que filtra objetos null automaticamente
        listBestasDisponiveis.setItems(bestasDisponiveis.filtered(Objects::nonNull)); // Lista que filtra objetos null automaticamente
        listHeroisSelecionados.setItems(heroisSelecionados);
        listBestasSelecionados.setItems(bestasSelecionados);
        // Criar listas dos componentes para facilitar inicialização
        List<ListView<PersonagemDTO>> listViews = new ArrayList<>();
        listViews.add(listHeroisDisponiveis);
        listViews.add(listBestasDisponiveis);
        listViews.add(listHeroisSelecionados);
        listViews.add(listBestasSelecionados);

        List<Button> buttons = new ArrayList<>();
        buttons.add(botaoSelecionarHeroi);
        buttons.add(botaoSelecionarBesta);
        buttons.add(botaoRemoverHeroi);
        buttons.add(botaoRemoverBesta);
        // Métodos auxiliares de inicialização
        definirCellFactoryListViews(listViews);
        definirChangeListenerListViews(listViews, buttons);
        iniciarMusica();
    }

    private void definirCellFactoryListViews(List<ListView<PersonagemDTO>> listViews) {
        for (ListView<PersonagemDTO> listView : listViews) {
            listView.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(PersonagemDTO pDTO, boolean empty) {
                    super.updateItem(pDTO, empty);
                    if (empty || pDTO == null) {
                        setText(null);
                    } else {
                        setText(
                                String.format(
                                        "Nome: %-14s Vida: %-4d Armadura: %-3d Tipo: %-8s Unico: %-6s%n",
                                        pDTO.nome(), pDTO.vida(), pDTO.armadura(), pDTO.tipoClass().getSimpleName(), pDTO.unico()
                                )
                        );
                    }
                }
            });
        }
    }

    private void definirChangeListenerListViews(List<ListView<PersonagemDTO>> listViews, List<Button> buttons) {
        Iterator<ListView<PersonagemDTO>> itListView = listViews.iterator();
        Iterator<Button> itButton = buttons.iterator();
        while (itListView.hasNext() && itButton.hasNext()) {
            ListView<PersonagemDTO> listView = itListView.next();
            Button button = itButton.next();
            listView.getSelectionModel().selectedItemProperty().addListener((obs, persAntiga, persNova) -> {
                if (persNova != null) {
                    button.setDisable(false);
                    for (ListView<PersonagemDTO> otherListView : listViews) {
                        if (otherListView != listView) {
                            otherListView.getSelectionModel().clearSelection();
                        }
                    }
                    for (Button otherButton : buttons) {
                        if (otherButton != button) {
                            otherButton.setDisable(true);
                        }
                    }
                } else {
                    button.setDisable(true);
                }
            });
        }
    }

    private void iniciarMusica() {
        Media media = new Media(MUSICA_PATH_URI);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void selecionarHeroi() {
        PersonagemDTO selecionado = listHeroisDisponiveis.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            heroisSelecionados.add(selecionado);
            if (selecionado.unico()) {
                heroisDisponiveis.set(selecionado.id() - 1, null);
            }
        }
        gerirBotaoBatalha();
    }

    public void selecionarBesta() {
        PersonagemDTO selecionado = listBestasDisponiveis.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            bestasSelecionados.add(selecionado);
            if (selecionado.unico()) {
                bestasDisponiveis.set(selecionado.id() - 1, null);
            }
        }
        gerirBotaoBatalha();
    }

    public void removerHeroi() {
        PersonagemDTO removido = listHeroisSelecionados.getSelectionModel().getSelectedItem();
        if (removido != null) {
            heroisSelecionados.remove(removido);
            if (removido.unico()) {
                heroisDisponiveis.set(removido.id() - 1, removido);
            }
        }
        gerirBotaoBatalha();
    }

    public void removerBesta() {
        PersonagemDTO removido = listBestasSelecionados.getSelectionModel().getSelectedItem();
        if (removido != null) {
            bestasSelecionados.remove(removido);
            if (removido.unico()) {
                bestasDisponiveis.set(removido.id() - 1, removido);
            }
        }
        gerirBotaoBatalha();
    }

    private void gerirBotaoBatalha() {
        if (!heroisSelecionados.isEmpty() && !bestasSelecionados.isEmpty()) {
            botaoBatalha.setDisable(false);
        } else {
            botaoBatalha.setDisable(true);
        }
    }

    public void mudarParaSceneBatalha(ActionEvent event) {
        Exercito<Heroi> herois = new Exercito<>();
        herois.addAll(Conversor.converterPersonagens(Heroi.class, heroisSelecionados));
        Exercito<Besta> bestas = new Exercito<>();
        bestas.addAll(Conversor.converterPersonagens(Besta.class, bestasSelecionados));
        FXMLLoader fxmlLoader = null;
        Parent root = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/projeto/projetofinalgui/fxml/scene-batalha.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a scene root. Verifique a integridade do ficheiro.");
        }
        SceneBatalhaController sceneBatalhaController = fxmlLoader.getController();
        if (root != null && sceneBatalhaController != null) {
            sceneBatalhaController.setHerois(herois);
            sceneBatalhaController.setBestas(bestas);
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);

            sceneBatalhaController.batalhar();
        }
        mediaPlayer.stop();
    }
}
