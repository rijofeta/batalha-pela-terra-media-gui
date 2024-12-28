package projeto.projetofinalgui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Path;

public class SceneInicialController {

    private final String MUSICA_PATH_URI =
            Path.of("src/main/resources/projeto/projetofinalgui/musica/tema_inicial.mp3").toUri().toString();

    private MediaPlayer mediaPlayer;

    public void initialize() {
        Media media = new Media(MUSICA_PATH_URI);
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
}
