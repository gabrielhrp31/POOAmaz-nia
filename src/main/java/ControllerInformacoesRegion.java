import DAO.SquadDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Region;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ControllerInformacoesRegion extends ControllerUtil{

    @FXML
    private ComboBox<String> comboBoxSquadResponsable = new ComboBox<>();

    @FXML
    private BorderPane borderPanel;

    @FXML
    public TableView<Region> tabbleViewRegion;

    @FXML
    private TableColumn<Region, String> tabbleColumnRegionID;

    @FXML
    private TableColumn<Region, String> tabbleColumnRegionNOME;

    @FXML
    private TableColumn<Region, String> tabbleColumnRegionSQUADRESPONSABLE;

    @FXML
    private TableColumn<Region, String> tabbleColumnRegionPROTECTEDAREA;


    public void teste() {
        JOptionPane.showMessageDialog(null, "TESTE");
    }


    /**
     * Atualiza os campos para que possam ser alterados
     *
     * @throws FileNotFoundException
     */
    @FXML
    public void atualizarTabbleViewRegion() throws FileNotFoundException {


        //Lê do arquivo e adiciona no comboBox
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "region.json");
        Reader reader = new FileReader(dir);
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegions;
        listaRegions = gson.fromJson(reader, listType);

        ObservableList<Region> olcomboBoxRegionResponsable;
        olcomboBoxRegionResponsable = FXCollections.observableList(listaRegions);
        tabbleViewRegion.setEditable(true);
        tabbleColumnRegionNOME.setEditable(true);
        //AQUI ELE PEGA TODOS OS CAMPOS QUE EXISTEM NO GET, E LÁ EM BAIXO ADICIONA EM ORDEM NA TABBLE VIEW
        tabbleColumnRegionID.setCellValueFactory(new PropertyValueFactory<Region, String>("id"));
        tabbleColumnRegionNOME.setCellValueFactory(new PropertyValueFactory<Region, String>("name"));
        tabbleColumnRegionSQUADRESPONSABLE.setCellValueFactory(new PropertyValueFactory<Region,String>("squadResponsable"));

        tabbleColumnRegionPROTECTEDAREA.setCellValueFactory(new PropertyValueFactory<Region,String>("protectedArea"));


        tabbleViewRegion.getColumns().clear();
        tabbleViewRegion.setItems(olcomboBoxRegionResponsable);
        tabbleViewRegion.getColumns().addAll(tabbleColumnRegionID, tabbleColumnRegionNOME, tabbleColumnRegionSQUADRESPONSABLE,tabbleColumnRegionPROTECTEDAREA);
        tabbleViewRegion.setEditable(true);
        tabbleColumnRegionNOME.setEditable(true);

    }


    @FXML
    private void telaRegistrationRegion() throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        if (file.exists()) {
            SquadDAO squadDAO = new SquadDAO();
            ObservableList<String> olcomboBoxSquadResponsable;
            olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxSquad());
            comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
            creatUI("registroRegion");
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro registre um Esquadrao");
        }

    }

    @FXML
    private void telaEditarRegion(){

        File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "region.json");
        if (file.exists()) {
            creatUI("telaEditarDadosRegion");
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro tenha uma regiao ja registrada");
        }

    }


}
