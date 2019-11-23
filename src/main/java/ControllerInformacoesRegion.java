import DAO.RegionDAO;
import com.google.cloud.firestore.QueryDocumentSnapshot;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ControllerInformacoesRegion extends ControllerUtil {


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
    public void atualizarTabbleViewRegion() throws FileNotFoundException, ExecutionException, InterruptedException {
        //Lê do arquivo e adiciona no comboBox

        RegionDAO regionDAO = new RegionDAO();

        ObservableList<Region> olcomboBoxRegionResponsable;
        olcomboBoxRegionResponsable = FXCollections.observableList(regionDAO.carregarTabbleViewRegion(ControllerAnalisesMain.firebase));;
        //aqui pega dos campos dentro do observableList
        tabbleViewRegion.setEditable(true);
        tabbleColumnRegionNOME.setEditable(true);
        tabbleColumnRegionID.setCellValueFactory(new PropertyValueFactory<Region, String>("id"));
        tabbleColumnRegionNOME.setCellValueFactory(new PropertyValueFactory<Region, String>("name"));
        tabbleColumnRegionSQUADRESPONSABLE.setCellValueFactory(new PropertyValueFactory<Region, String>("squadResponsable"));

        tabbleColumnRegionPROTECTEDAREA.setCellValueFactory(new PropertyValueFactory<Region, String>("protectedArea"));


        tabbleViewRegion.getColumns().clear();
        tabbleViewRegion.setItems(olcomboBoxRegionResponsable);
        tabbleViewRegion.getColumns().addAll(tabbleColumnRegionID, tabbleColumnRegionNOME, tabbleColumnRegionSQUADRESPONSABLE, tabbleColumnRegionPROTECTEDAREA);
        tabbleViewRegion.setEditable(true);
        tabbleColumnRegionNOME.setEditable(true);

    }


    /**
     * Abre a tela para Registrar Regions
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @FXML
    private void telaRegistrationRegion() throws IOException, ExecutionException, InterruptedException {
          //firebase;
        List<QueryDocumentSnapshot> documents = ControllerAnalisesMain.firebase.read(1);
        if (documents != null) {
            creatUI("registroRegion");
            } else {
            JOptionPane.showMessageDialog(null, "Primeiro registre um Esquadrao");
        }

    }

    /**
     * Abre a tela de Editar Regions
     */
    @FXML
    private void telaEditarRegion() {
            creatUI("telaEditarDadosRegion");
    }


}
