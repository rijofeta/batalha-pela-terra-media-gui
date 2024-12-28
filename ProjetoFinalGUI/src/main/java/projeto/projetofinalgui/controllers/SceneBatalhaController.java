package projeto.projetofinalgui.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import projeto.projetofinalgui.batalha.Batalha;
import projeto.projetofinalgui.exercito.Exercito;
import projeto.projetofinalgui.models.personagens.bestas.Besta;
import projeto.projetofinalgui.models.personagens.herois.Heroi;

import java.io.IOException;
import java.nio.file.Path;

public class SceneBatalhaController {

    private final String MUSICA_PATH_URI =
            Path.of("src/main/resources/projeto/projetofinalgui/musica/tema_batalha.mp3").toUri().toString();

    @FXML
    private ListView<Heroi> listExercitoHerois;

    @FXML
    private ListView<Besta> listExercitoBestas;

    @FXML
    private TextArea textAreaBatalha;

    private MediaPlayer mediaPlayer;

    private Batalha batalha;

    private Exercito<Heroi> herois;
    private Exercito<Besta> bestas;

    public void initialize() {
        construirBatalha();
        definirChangeListenerTextArea();
        iniciarMusica();
    }

    private void construirBatalha() {
        batalha = new Batalha();
        batalha.getDialogo().setTextArea(textAreaBatalha);
    }

    private void definirChangeListenerTextArea() {
        textAreaBatalha.textProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                listExercitoHerois.setItems(FXCollections.observableList(this.herois.getListaExercito()));
                listExercitoBestas.setItems(FXCollections.observableList(this.bestas.getListaExercito()));
            });
        });
    }

    private void iniciarMusica() {
        Media media = new Media(MUSICA_PATH_URI);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void setHerois(Exercito<Heroi> herois) {
        this.herois = herois;
        listExercitoHerois.setItems(FXCollections.observableList(this.herois.getListaExercito()));
    }

    public void setBestas(Exercito<Besta> bestas) {
        this.bestas = bestas;
        listExercitoBestas.setItems(FXCollections.observableList(this.bestas.getListaExercito()));
    }

    public void batalhar() {
        Task<Void> batalhar = new Task<>() {
            @Override
            protected Void call() {
                try {
                    batalha.batalhar(herois, bestas);
                    Thread.sleep(3000);
                    mudarParaSceneFinal();
                } catch (InterruptedException e) {
                    System.err.println("O método sleep() do thread de batalha foi interrompido!");
                }
                return null;
            }
        };

        Thread batalhaThread = new Thread(batalhar);
        batalhaThread.setDaemon(true);
        batalhaThread.start();
    }

    private void mudarParaSceneFinal() {
        FXMLLoader fxmlLoader = null;
        Parent root = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/projeto/projetofinalgui/fxml/scene-final.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a scene root. Verifique a integridade do ficheiro.");
        }
        SceneFinalController sceneFinalController = fxmlLoader.getController();
        if (root != null && sceneFinalController != null) {
            if (bestas.getListaExercito().isEmpty())
                sceneFinalController.setVencedor(SceneFinalController.Vencedor.HEROIS);
            else if (herois.getListaExercito().isEmpty())
                sceneFinalController.setVencedor(SceneFinalController.Vencedor.BESTAS);
            else sceneFinalController.setVencedor(SceneFinalController.Vencedor.DEFAULT);

            Scene scene = textAreaBatalha.getScene();
            scene.setRoot(root);
        }
        mediaPlayer.stop();
    }
}
