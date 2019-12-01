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
import Exceptions.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static analysis.Main.firebase;


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


    @FXML
    private TableColumn<Region, String> tabbleColumnRegionENVIRONMENTALPROTECTION;

    @FXML
    private TableColumn<Region, String> tabbleColumnRegionURBANREGION;


    /**
     * Atualiza os campos para que possam ser alterados
     *
     * @throws FileNotFoundException
     */
    @FXML
    public void atualizarTabbleViewRegion() throws FileNotFoundException, ExecutionException, InterruptedException {
        //Lê do arquivo e adiciona no comboBox
        new Thread(ControllerUtil.t1).start();

        RegionDAO regionDAO = new RegionDAO();

        ObservableList<Region> olcomboBoxRegionResponsable;
        //dentro da .carregarComboBox tem o try catch já
        olcomboBoxRegionResponsable = FXCollections.observableList(regionDAO.carregarTabbleView(firebase));
        ;
        //aqui pega dos campos dentro do observableList
        tabbleViewRegion.setEditable(true);
        tabbleColumnRegionNOME.setEditable(true);
        tabbleColumnRegionID.setCellValueFactory(new PropertyValueFactory<Region, String>("id"));
        tabbleColumnRegionNOME.setCellValueFactory(new PropertyValueFactory<Region, String>("name"));
        tabbleColumnRegionSQUADRESPONSABLE.setCellValueFactory(new PropertyValueFactory<Region, String>("squadResponsable"));
        tabbleColumnRegionPROTECTEDAREA.setCellValueFactory(new PropertyValueFactory<Region, String>("protectedArea"));
        tabbleColumnRegionENVIRONMENTALPROTECTION.setCellValueFactory(new PropertyValueFactory<Region, String>("environmentalProtection"));
        tabbleColumnRegionURBANREGION.setCellValueFactory(new PropertyValueFactory<Region, String>("urbanRegion"));

        tabbleViewRegion.getColumns().clear();
        tabbleViewRegion.setItems(olcomboBoxRegionResponsable);
        tabbleViewRegion.getColumns().addAll(tabbleColumnRegionID, tabbleColumnRegionNOME, tabbleColumnRegionSQUADRESPONSABLE, tabbleColumnRegionPROTECTEDAREA, tabbleColumnRegionENVIRONMENTALPROTECTION, tabbleColumnRegionURBANREGION);
        tabbleViewRegion.setEditable(true);
        tabbleColumnRegionNOME.setEditable(true);


    }


    /**
     * Abre a tela para Registrar Regions
     *
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @FXML
    private void telaRegistrationRegion() throws IOException, ExecutionException, InterruptedException {
        //firebase;
        List<QueryDocumentSnapshot> documents=null;
        try{
            documents= firebase.read(1);
        }
        catch (conexaoError e){
            e.getMessage();
        } if (documents != null) {
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
