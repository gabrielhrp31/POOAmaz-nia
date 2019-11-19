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

public class ControllerCadastroMain extends ControllerUtil {


    @FXML
    private ComboBox<String> comboBoxSquadResponsable = new ComboBox<>();



    /**
     * Carrega a tela do registroSquad
     */
    @FXML
    private void telaRegistrationSquad() {
        creatUI("registroSquad");
    }

    @FXML
    void telaInformacoesRegion() throws FileNotFoundException {
        creatUI("informacoesRegion");
    }


    @FXML
    void telaInformacoesSquad() throws FileNotFoundException {
        creatUI("informacoesSquad");
    }

}
