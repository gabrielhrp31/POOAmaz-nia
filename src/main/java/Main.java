import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import views.components.SectorGrid;

public class Main extends Application {

    @Override
    public void  start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Index.fxml"));
        Scene scene = new Scene(root);
        BorderPane borderPane = (BorderPane) scene.lookup("#borderPane");
        stage.setTitle("Amazonia Analysis");
        stage.setScene(scene);
        stage.show();
    }







    public static void main(String[] args) {
        launch(args);
    }
}
