import DAO.RegionDAO;
import Models.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
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


        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegion = new ArrayList<Region>();
        listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo


        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);
        if (checkBoxProtegida.isSelected()) {
            regionButton.setProtectedArea(true);
        } else {
            regionButton.setProtectedArea(false);
        }


        for (int i = 0; i < listaRegion.size(); i++) {
            if (listaRegion.get(i).getName().equals(comboBoxSquadResponsable.getValue())) {
                regionButton.setSquadResponsable(listaRegion.get(i).getId());
            }
        }


        salvarJSON(System.getProperty("user.dir") + File.separator + "data" + File.separator + "region.json", null, regionButton, 1, 0, 0, 0);
        JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
    }


    @FXML
    void carregarDadosCheckBox() throws FileNotFoundException {
        RegionDAO regionDAO = new RegionDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(regionDAO.carregarComboBoxRegion());
        comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
    }

}
