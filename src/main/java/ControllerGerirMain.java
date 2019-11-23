import DAO.SquadDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ControllerGerirMain extends ControllerUtil {


    @FXML
    private ComboBox<String> comboBoxSquadResponsable = new ComboBox<>();



    @FXML
    void telaInformacoesRegion() throws FileNotFoundException {
        creatUI("informacoesRegion");
    }


    @FXML
    void telaInformacoesSquad() throws FileNotFoundException {
        creatUI("informacoesSquad");
    }

}
