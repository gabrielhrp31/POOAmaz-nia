import DAO.SquadDAO;
import Models.Region;
import Models.Squad;
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
import java.util.ArrayList;
import java.util.List;

public class ControllerEditarDadosRegion extends ControllerUtil {

    //atributos
    @FXML
    private ComboBox<String> comboBoxRegionEditar;

    @FXML
    private ComboBox<String> checkBSqudEditarRegion;

    @FXML
    private CheckBox checkBoxProtegidaEditarRegion;


    //metodos
    @FXML
    public void carregarCheckBoxRegion() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "regionJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegions = new ArrayList<Region>();
        listaRegions = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        ObservableList<java.lang.String> olcomboBoxSquadResponsable = null;

        List<String> listaNomes = new ArrayList<String>();

        for (int i = 0; i < listaRegions.size(); i++) {
            listaNomes.add(listaRegions.get(i).getName());
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);
        comboBoxRegionEditar.setItems(olcomboBoxSquadResponsable);
    }


    @FXML
    public void carregarCheckBoxSquad() throws FileNotFoundException {
        SquadDAO squadDAO = new SquadDAO();
        ObservableList<java.lang.String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxSquad());
        checkBSqudEditarRegion.setItems(olcomboBoxSquadResponsable);
    }


    @FXML
    public void botaoSalvarRegion() throws IOException {
        //TENTAR ENTENDER Q Q ISSO FAZ PRIMEIRO
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "regionJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegion = new ArrayList<Region>();
        listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo



        for (int i = 0; i < listaRegion.size(); i++) {
            if (comboBoxRegionEditar.getValue().equals(listaRegion.get(i).getName())) {//forem iguais
                listaRegion.get(i).setSquadResponsable(checkBoxProtegidaEditarRegion.getText());

                if (checkBoxProtegidaEditarRegion.isSelected()) {
                    listaRegion.get(i).setProtectedArea(true);
                } else {
                    listaRegion.get(i).setProtectedArea(false);
                }

                String nomeComboBox = comboBoxRegionEditar.getValue(); //pega o nome selecionado na comboBox
                listaRegion.get(i).setSquadResponsable(checkBSqudEditarRegion.getValue());
                salvarJSON(System.getProperty("user.dir") + File.separator + "data" + File.separator + "regionJ.json", null, listaRegion.get(i), 1, 1, null, nomeComboBox);
            }

        }


    }


}
