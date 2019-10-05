import DAO.Squad;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ControllerRegistroSquad extends ControllerUtil {


    //ATRIBUTOS
    @FXML
    private TextField textRegistrationSquadName;

    @FXML
    private TextField textRegistrationSquadQuantitySoldiers;

    @FXML
    private ComboBox<String> comboBoxSquad;



    //FUNÇÕES
    @FXML
    void botaoEnviarRegistroSquad() throws IOException {
         Squad squadButton = new Squad();
        String nomeSquad = textRegistrationSquadName.getText();
        String numberSquad = textRegistrationSquadQuantitySoldiers.getText();
        int numberSquadInt = Integer.parseInt(numberSquad);
        String region = comboBoxSquad.getValue();

        squadButton.setName(nomeSquad);
        squadButton.setQuantityOfSoldiers(numberSquadInt);
        squadButton.setRegionResponsable(region);
        salvarJSON("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\squadJ.json", squadButton, null, 0);
    }

}
