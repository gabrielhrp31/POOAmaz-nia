import DAO.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.io.*;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ControllerRegistroRegion extends ControllerUtil {


    //ATRIBUTOS


    @FXML
    private ComboBox<String> comboBoxRegion;

    @FXML
    private CheckBox checkBoxProtegida;

    @FXML
    public ComboBox<String> comboBoxSquadResponsable;


    //FUNÇÕES
    @FXML
    void botaoEnviarRegistroRegion() throws IOException {
        Region regionButton = new Region();
        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);

        if (checkBoxProtegida.isSelected()) {
            regionButton.setProtectedArea(true);
        } else {
            regionButton.setProtectedArea(false);
        }
        regionButton.setSquadResponsable(comboBoxSquadResponsable.getValue());


        salvarJSON(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"regionJ.json", null, regionButton, 1,0,null,null);


    }


    @FXML
    void carregarDadosCheckBox() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"squadJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegion = new ArrayList<Region>();
        listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        ObservableList<String> olcomboBoxSquadResponsable = null;

        List<String> listaRegionNomes = new ArrayList<String>();

        for (int i = 0; i < listaRegion.size(); i++) {
            listaRegionNomes.add(listaRegion.get(i).getName());
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaRegionNomes);

        comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
    }

}
