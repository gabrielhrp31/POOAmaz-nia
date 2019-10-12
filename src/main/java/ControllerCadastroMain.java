import DAO.SquadDAO;
import Models.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastroMain extends ControllerUtil {

    //ATRIBUTOS

    @FXML
    private ComboBox<String> comboBoxSquadResponsable = new ComboBox<>();


    //FUNÇÕES
    @FXML
    private void telaRegistrationRegion() throws FileNotFoundException {
//SE JÁ EXISTIR UM ESQUADRÃO REGISTRADO AO MENOS
        File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json");
        if (file.exists()) {
            //CARREGAR OS DADOS DO ESQUADRÃO PARA A CHECKBOX
            SquadDAO squadDAO = new SquadDAO();
            ObservableList<String> olcomboBoxSquadResponsable;
            olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxSquad());
            comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
            creatUI("registroRegion");
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro registre um Esquadrao");
        }

    }

    @FXML
    private void telaRegistrationSquad() {
        creatUI("registroSquad");
    }


}
