package analysis;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tools.Firebase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main extends Application {


    /**
     * O método start inicia o sistema, criando e exibindo a tela inicial.
     * @param stage recebe um stage para abrir ele no javaFX
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Index.fxml"));
            Scene scene = new Scene(root);
            BorderPane borderPane = (BorderPane) scene.lookup("#borderPane");
            stage.setTitle("Amazonia Analysis");
            stage.setScene(scene);
            stage.show();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Roda a classe executando o start e conectando com o Firebase
     * @param args padrão da sintaxe
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
