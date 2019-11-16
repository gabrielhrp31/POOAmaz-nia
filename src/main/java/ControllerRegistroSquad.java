import DAO.RegionDAO;
import DAO.SquadDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Region;
import models.Squad;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ControllerRegistroSquad extends ControllerUtil {


    //ATRIBUTOS
    @FXML
    private TextField textRegistrationSquadName;

    @FXML
    private TextField textRegistrationSquadQuantitySoldiers;

    @FXML
    private ComboBox<String> comboBoxSquad;


    /**
     * Cadastra um novo Esquadrão
     * @throws IOException
     */
    @FXML
    void botaoEnviarRegistroSquad() throws IOException, GeneralSecurityException {
        Squad squadButton = new Squad();

        if (!(isAlpha(textRegistrationSquadName.getText()))) {
            JOptionPane.showMessageDialog(null,"Insira apenas Letras no nome");
            return;
        }
        if(!soContemNumeros(textRegistrationSquadQuantitySoldiers.getText())){
            JOptionPane.showMessageDialog(null,"Insira apenas Numeros na quantidade");
            return;
        }


        //ATRIBUI OS VALORES AO OBJETO
        java.lang.String nomeSquad = textRegistrationSquadName.getText();
        java.lang.String numberSquad = textRegistrationSquadQuantitySoldiers.getText();
        int numberSquadInt = Integer.parseInt(numberSquad);
        java.lang.String region = comboBoxSquad.getValue();
        squadButton.setName(nomeSquad);
        squadButton.setQuantityOfSoldiers(numberSquadInt);
        squadButton.setRegionResponsable(region);
        salvarJSON(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json", squadButton, null, 0, 0, 0, 0);
        JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
    }


    @FXML
    public void carregarCheckBoxRegion() throws FileNotFoundException {
        SquadDAO squadDAO = new SquadDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxRegion());
        comboBoxSquad.setItems(olcomboBoxSquadResponsable);
    }


}
