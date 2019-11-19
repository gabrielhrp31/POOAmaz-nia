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
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main extends Application {



    @Override
    public void  start(Stage stage) throws Exception {
        downloadDadosGerais();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Index.fxml"));
        Scene scene = new Scene(root);
        BorderPane borderPane = (BorderPane) scene.lookup("#borderPane");
        stage.setTitle("Amazonia Analysis");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Baixa os dados do drive
     * @throws IOException
     * @throws GeneralSecurityException
     */
    @FXML
    public static void downloadDadosGerais() throws IOException, GeneralSecurityException {
        /*
        GoogleDrive gd=new GoogleDrive();
        try {
            gd.downloadFile("application/json", "region.json", System.getProperty("user.dir") + File.separator + "data" + File.separator);
        } catch (IOException e) {
            return;
        }

        try {
            gd.downloadFile("application/json", "squad.json", System.getProperty("user.dir") + File.separator + "data" + File.separator);
        } catch (IOException e) {
            return;
        }

        try {
            gd.downloadFile("application/json", "pictures.json", System.getProperty("user.dir") + File.separator + "data" + File.separator+"relatorio"+File.separator);
        } catch (IOException e) {
            return;
        }


        */
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
