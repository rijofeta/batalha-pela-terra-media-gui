package projeto.projetofinalgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Jogo extends Application {

    private final Path FONTES_DIR = Path.of("src/main/resources/projeto/projetofinalgui/fontes");

    @Override
    public void start(Stage stage) {
        Scene scene = null;
        try {
            // Carregar Scene Inicial
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/projeto/projetofinalgui/fxml/scene-inicial.fxml"));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Erro ao carregar a scene root. Verifique a integridade do ficheiro.");
        }
        if (scene != null) {
            // Carregar Fonts e folhas de estilo
            carregarFonts();
            scene.getStylesheets().add(getClass().getResource("/projeto/projetofinalgui/css/style.css").toExternalForm());
            // Adicionar scene à janela
            stage.setScene(scene);
        }
        // Definir propriedades da janela
        stage.setTitle("BpTM");
        stage.getIcons().add(new Image(getClass().getResource("/projeto/projetofinalgui/imagens/icon.png").toExternalForm()));
        stage.setFullScreen(true);
        // Mostrar janela
        stage.show();
    }

    private void carregarFonts() {
        try (DirectoryStream<Path> dirstrm = Files.newDirectoryStream(FONTES_DIR)) {
            for (Path path : dirstrm) {
                if (Files.isRegularFile(path)) {
                    String fontURL = path.toUri().toURL().toString().replace("%20", " ");
                    Font.loadFont(fontURL, 0);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro na operação I/O de leitura do diretório das fonts."); // Pode prosseguir com fonts standard
        }
    }

    public static void main(String[] args) {
        launch();
    }
}