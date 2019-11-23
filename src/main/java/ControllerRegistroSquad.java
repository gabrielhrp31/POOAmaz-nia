import DAO.SquadDAO;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Squad;

import javax.swing.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ControllerRegistroSquad extends ControllerUtil {


    //ATRIBUTOS
    @FXML
    private TextField textRegistrationSquadName;

    @FXML
    private TextField textRegistrationSquadQuantitySoldiers;

    @FXML
    private ComboBox<String> comboBoxSquad;


    SquadDAO squadDAO;

    /**
     * Cadastra um novo Esquadrão
     *
     * @throws IOException
     */
    @FXML
    void botaoEnviarRegistroSquad() throws IOException, GeneralSecurityException, ExecutionException, InterruptedException {

        if (!(isAlpha(textRegistrationSquadName.getText()))) {
            JOptionPane.showMessageDialog(null, "Insira apenas Letras no nome");
            return;
        }
        if (!soContemNumeros(textRegistrationSquadQuantitySoldiers.getText())) {
            JOptionPane.showMessageDialog(null, "Insira apenas Numeros na quantidade");
            return;
        }

        Squad squadButton = new Squad();
        List<QueryDocumentSnapshot> documents = ControllerAnalisesMain.firebase.read(1);

        //id é a qntd de itens
        if (documents != null) {
            documents.size();
            squadButton.setId((documents.size()));
        }

        //ATRIBUI OS VALORES AO OBJETO
        java.lang.String nomeSquad = textRegistrationSquadName.getText();
        java.lang.String numberSquad = textRegistrationSquadQuantitySoldiers.getText();
        int numberSquadInt = Integer.parseInt(numberSquad);
        java.lang.String region = comboBoxSquad.getValue();
        squadButton.setName(nomeSquad);
        squadButton.setQuantityOfSoldiers(numberSquadInt);
        squadButton.setRegionResponsable(region);

        ControllerAnalisesMain.firebase.write(1, squadButton.getId(), squadButton.getName(), null, 0, " ", " ", squadButton.getQuantityOfSoldiers(), squadButton.getRegionResponsable());
        JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Carrega os dados das Regions para o CheckBox
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @FXML
    public void carregarCheckBoxRegion() throws IOException, ExecutionException, InterruptedException {

        squadDAO = new SquadDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxRegion(ControllerAnalisesMain.firebase));
        comboBoxSquad.setItems(olcomboBoxSquadResponsable);

    }


}
