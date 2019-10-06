
import DAO.Squad;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class ControllerRegistroSquad extends ControllerUtil {


    //ATRIBUTOS
    @FXML
    private TextField textRegistrationSquadName;

    @FXML
    private TextField textRegistrationSquadQuantitySoldiers;

    @FXML
    private ComboBox<java.lang.String> comboBoxSquad;



    //FUNÇÕES
    @FXML
    void botaoEnviarRegistroSquad() throws IOException {
        Squad squadButton = new Squad();
        java.lang.String nomeSquad = textRegistrationSquadName.getText();
        java.lang.String numberSquad = textRegistrationSquadQuantitySoldiers.getText();
        int numberSquadInt = Integer.parseInt(numberSquad);
        java.lang.String region = comboBoxSquad.getValue();

        squadButton.setName(nomeSquad);
        squadButton.setQuantityOfSoldiers(numberSquadInt);
        squadButton.setRegionResponsable(region);
        salvarJSON(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"squadJ.json", squadButton, null, 0,null,null);
    }

}
