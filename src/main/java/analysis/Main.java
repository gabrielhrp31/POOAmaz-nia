package analysis;
import DAO.ImageDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Image;
import tools.Firebase;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class Main extends Application {


    public static Firebase firebase;
    /**
     * O método start inicia o sistema, criando e exibindo a tela inicial.
     * @param stage recebe um stage para abrir ele no javaFX
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            firebase = new Firebase();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ImageDAO imageDAO = new ImageDAO();
        ArrayList<Image> images = imageDAO.getImages();
        for (Image image: images) {
            firebase.downloadFile(image.getFileName());
        }
        
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
