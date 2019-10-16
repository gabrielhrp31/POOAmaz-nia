import DAO.SquadDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ControllerCadastroMain extends ControllerUtil {


    @FXML
    private ComboBox<String> comboBoxSquadResponsable = new ComboBox<>();


    /**
     * Primeiro verifica se já existe um esquadrão registrado e após isso carrega a tela do Region
     * @throws FileNotFoundException
     */
    @FXML
    private void telaRegistrationRegion() throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        if (file.exists()) {
            SquadDAO squadDAO = new SquadDAO();
            ObservableList<String> olcomboBoxSquadResponsable;
            olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxSquad());
            comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
            creatUI("registroRegion");
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro registre um Esquadrao");
        }

    }


    /**
     * Carrega a tela do registroSquad
     */
    @FXML
    private void telaRegistrationSquad() {
        creatUI("registroSquad");
    }


}
