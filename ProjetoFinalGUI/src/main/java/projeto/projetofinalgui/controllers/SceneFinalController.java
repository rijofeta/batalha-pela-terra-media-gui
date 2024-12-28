package projeto.projetofinalgui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class SceneFinalController {

    @FXML
    private Label labelFinal;

    private Vencedor vencedor;

    private MediaPlayer mediaPlayer;

    public enum Vencedor {
        HEROIS("Vitória dos Heróis", "src/main/resources/projeto/projetofinalgui/musica/tema_final_herois.mp3"),
        BESTAS("Vitória das Bestas", "src/main/resources/projeto/projetofinalgui/musica/tema_final_bestas.mp3"),
        DEFAULT("Fim", "src/main/resources/projeto/projetofinalgui/musica/tema_final_herois.mp3");

        private final String label;
        private final String MUSICA_PATH_URI;

        Vencedor(String label, String relPath) {
            this.label = label;
            this.MUSICA_PATH_URI = Path.of(relPath).toUri().toString();
        }
    }

    public void initialize() {
        if (vencedor != null) {
            labelFinal.setText(vencedor.label);
            iniciarMusica();
        }
    }

    private void iniciarMusica() {
        Media media = new Media(vencedor.MUSICA_PATH_URI);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void mudarParaSceneSelecao(ActionEvent event) {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projeto/projetofinalgui/fxml/scene-selecao.fxml"));
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a scene root. Verifique a integridade do ficheiro.");
        }
        if (root != null) {
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }
        mediaPlayer.stop();
    }

    public void sair() {
        Platform.exit();
    }
    public void setVencedor(Vencedor vencedor) {
        this.vencedor = vencedor;
        initialize();
    }
}
