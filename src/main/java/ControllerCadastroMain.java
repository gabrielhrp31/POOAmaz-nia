import DAO.Region;
import DAO.Squad;
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
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ControllerCadastroMain extends ControllerUtil {

    //ATRIBUTOS

    @FXML
    private ComboBox<String> comboBoxSquadResponsable= new ComboBox<>();


    //FUNÇÕES
    @FXML
    private void telaRegistrationRegion() throws FileNotFoundException {
//SE JÁ EXISTIR UM ESQUADRÃO REGISTRADO AO MENOS
        File file = new File(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"squadJ.json");
        if (file.exists()) {
            //CARREGAR OS DADOS DO ESQUADRÃO PARA A CHECKBOX
            Region regionButton = new Region();
            File dir = new File(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"squadJ.json");
            Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
            Type listType = new TypeToken<ArrayList<Region>>() {
            }.getType();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<Region> listaRegion = new ArrayList<Region>();
            listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo

            ObservableList<String> olcomboBoxSquadResponsable;
            List<String> listaRegionNomes = new ArrayList<String>();
            //  listaRegionNomes agora tem todos os dados do squadJson
            for (int i = 0; i < listaRegion.size(); i++) {
                listaRegionNomes.add(listaRegion.get(i).getName());
            }

            olcomboBoxSquadResponsable = FXCollections.observableList(listaRegionNomes);

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
