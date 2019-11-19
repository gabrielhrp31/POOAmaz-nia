import DAO.RegionDAO;
import DAO.SquadDAO;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import models.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import models.Squad;
import tools.Firebase;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
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


    /**
     * Cadastra uma nova Region
     * @throws IOException
     */
    @FXML
    void botaoEnviarRegistroRegion() throws IOException, GeneralSecurityException, ExecutionException, InterruptedException {
        Region regionButton = new Region();
        SquadDAO squadButton= new SquadDAO();

        List<QueryDocumentSnapshot> documentsSquads = ControllerAnalisesMain.firebase.read(1);

        //vou ter aqui a lista de esquadroes
        //lógica vai ser bem parecida, mas na minha lista region vai ter é os nomes dos squads do firebase

        List<Squad> listaSquad = squadButton.carregarComboBoxSquad(ControllerAnalisesMain.firebase);
       

        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);
        if (checkBoxProtegida.isSelected()) {
            regionButton.setProtectedArea(true);
        } else {
            regionButton.setProtectedArea(false);
        }


        for (int i = 0; i < listaSquad.size(); i++) {
            if (listaSquad.get(i).getName().equals(comboBoxSquadResponsable.getValue())) {
                regionButton.setSquadResponsable(listaSquad.get(i).getId());
            }
        }




        // vou precisar de duas leituras, essa do setSquadResonsable por exemplo
        //n posso pegar da minha lista de squads locais

        //id é a qntd de itens


        //para o ID da region
        List<QueryDocumentSnapshot> documents = ControllerAnalisesMain.firebase.read(0);
        if (documents != null) {
            documents.size();
            regionButton.setId((documents.size()));
        }


        ControllerAnalisesMain.firebase.write(0,regionButton.getId(),regionButton.getName(),regionButton.getProtectedArea(),regionButton.getSquadResponsable(),0," ");
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
        olcomboBoxSquadResponsable = FXCollections.observableList(regionDAO.carregarComboBoxRegion(ControllerAnalisesMain.firebase));

        comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
        //OBS: O PUBLIC STATIC PARA A FIREBASE PARECE TER FUNCO
    }


    @FXML
    void telaInformacoesRegion(){
        creatUI("informacoesRegion");
    }

}
