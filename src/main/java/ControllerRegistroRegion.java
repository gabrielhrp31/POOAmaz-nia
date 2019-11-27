import DAO.RegionDAO;
import DAO.SquadDAO;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import javafx.scene.control.TextField;
import models.Region;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import models.Squad;

import javax.swing.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ControllerRegistroRegion extends ControllerUtil {


    //ATRIBUTOS


    @FXML
    private ComboBox<String> comboBoxRegion;

    @FXML
    private CheckBox checkBoxProtegida;

    @FXML
    public ComboBox<String> comboBoxSquadResponsable;


    @FXML
    private CheckBox checkBoxProtecaoAmbiental;

    @FXML
    private TextField textBoxProtecaoAmbiental;


    @FXML
    private CheckBox checkboxRegiaoUrbana;

    @FXML
    private TextField textBoxRegiaoUrbana;



    /**
     * Cadastra uma nova Region
     * @throws IOException
     */
    @FXML
    void botaoEnviarRegistroRegion() throws IOException, GeneralSecurityException, ExecutionException, InterruptedException {
        Region regionButton = new Region();
        SquadDAO squadButton= new SquadDAO();

        List<QueryDocumentSnapshot> documentsSquads = ControllerAnalisesMain.firebase.read(1);


        List<Squad> listaSquad = squadButton.carregarComboBoxSquad(ControllerAnalisesMain.firebase);
       

        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);
        if (checkBoxProtegida.isSelected()) {
            regionButton.setProtectedArea(true);
        } else {
            regionButton.setProtectedArea(false);
        }

        if(checkBoxProtecaoAmbiental.isSelected()){
            regionButton.setEnvironmentalProtection(textBoxProtecaoAmbiental.getText());
        }
        else{
        regionButton.setEnvironmentalProtection("Nao e uma area de Protecao Ambiental");
        }


        if(checkboxRegiaoUrbana.isSelected()){
            regionButton.setUrbanRegion(textBoxRegiaoUrbana.getText());
        }
        else{
            regionButton.setUrbanRegion("Nao e uma regiao Urbana");
        }


        for (int i = 0; i < listaSquad.size(); i++) {
            if (listaSquad.get(i).getName().equals(comboBoxSquadResponsable.getValue())) {
                regionButton.setSquadResponsable(listaSquad.get(i).getId());
            }
        }



        List<QueryDocumentSnapshot> documents = ControllerAnalisesMain.firebase.read(0);
        if (documents != null) {
            documents.size();
            regionButton.setId((documents.size()));
        }


        ControllerAnalisesMain.firebase.write(0,regionButton.getId(),regionButton.getName(),regionButton.getProtectedArea(),regionButton.getSquadResponsable(),regionButton.getEnvironmentalProtection(),regionButton.getUrbanRegion(),0," ");
        JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Carrega as region já cadastradas para a comboBox
     * @throws FileNotFoundException
     */
    @FXML
    void carregarDadosCheckBox() throws IOException, ExecutionException, InterruptedException {
        RegionDAO regionDAO = new RegionDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(regionDAO.carregarComboBox(ControllerAnalisesMain.firebase));

        comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
        //OBS: O PUBLIC STATIC PARA A FIREBASE PARECE TER FUNCO
    }


    /**
     * Abrea a tela de informacoesRegion
     */
    @FXML
    void telaInformacoesRegion(){
        creatUI("informacoesRegion");
    }

}
