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
        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);
        if (checkBoxProtegida.isSelected()) {
            regionButton.setProtectedArea(true);
        } else {
            regionButton.setProtectedArea(false);
        }
        regionButton.setSquadResponsable(comboBoxSquadResponsable.getValue());

        salvarJSON(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"regionJ.json", null, regionButton, 1,0,0,0);
        JOptionPane.showMessageDialog(null,"Acao Concluidada","SUCESSO",JOptionPane.INFORMATION_MESSAGE);
    }


    @FXML
    void carregarDadosCheckBox() throws FileNotFoundException {
        RegionDAO regionDAO=new RegionDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(regionDAO.carregarComboBoxRegion());
        comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
    }

}
