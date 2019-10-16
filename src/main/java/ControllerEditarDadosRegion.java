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

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerEditarDadosRegion extends ControllerUtil {

    @FXML
    private ComboBox<String> comboBoxRegionEditar;

    @FXML
    private ComboBox<String> checkBSqudEditarRegion;

    @FXML
    private CheckBox checkBoxProtegidaEditarRegion;


    /**
     * Carrega para a CheckBox as regiões já cadastradas
     * @throws FileNotFoundException
     */
    @FXML
    public void carregarCheckBoxRegion() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "region.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegions;
        listaRegions = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        ObservableList<java.lang.String> olcomboBoxSquadResponsable;

        List<String> listaNomes = new ArrayList<String>();

        for (int i = 0; i < listaRegions.size(); i++) {
            listaNomes.add(listaRegions.get(i).getName());
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);
        comboBoxRegionEditar.setItems(olcomboBoxSquadResponsable);
    }


    /**
     * Carrega os dados dos Esquadrões para uma CheckBox
     * @throws FileNotFoundException
     */
    @FXML
    public void carregarCheckBoxSquad() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        Reader reader = new FileReader(dir);
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquads;
        listaSquads = gson.fromJson(reader, listType);
        ObservableList<java.lang.String> olcomboBoxSquadResponsable;
        List<String> listaNomes = new ArrayList<>();

        for (int i = 0; i < listaSquads.size(); i++) {
            listaNomes.add(listaSquads.get(i).getName());
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);
        checkBSqudEditarRegion.setItems(olcomboBoxSquadResponsable);

    }


    /**
     * Salva as modificações feitas naquela region
     * @throws IOException
     */
    @FXML
    public void botaoSalvarRegion() throws IOException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "region.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegion;
        listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        //squad
        dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquad = new ArrayList<Squad>();
        listaSquad = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo


        for (int i = 0; i < listaRegion.size(); i++) {
            if (comboBoxRegionEditar.getValue().equals(listaRegion.get(i).getName())) {//forem iguais

                for (int u = 0; u < listaSquad.size(); u++) {
                    if (listaSquad.get(u).getName().equals(checkBSqudEditarRegion.getValue())) {
                        listaRegion.get(i).setSquadResponsable(listaSquad.get(u).getId());
                    }
                }

                if (checkBoxProtegidaEditarRegion.isSelected()) {
                    listaRegion.get(i).setProtectedArea(true);
                } else {
                    listaRegion.get(i).setProtectedArea(false);
                }

                 int id = listaRegion.get(i).getId();


                salvarJSON(System.getProperty("user.dir") + File.separator + "data" + File.separator + "region.json", null, listaRegion.get(i), 1, 1, 0, id);
                JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            }

        }


    }


}
