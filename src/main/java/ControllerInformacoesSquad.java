import DAO.SquadDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Squad;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

public class ControllerInformacoesSquad extends ControllerUtil {

    @FXML
    private TableView<Squad> tabbleViewSquad;

    @FXML
    private TableColumn<Squad, String> tabbleColumnSquadID;

    @FXML
    private TableColumn<Squad, String> tabbleColumnSquadNOME;

    @FXML
    private TableColumn<Squad, String> tabbleColumnSquadREGIONRESPONSABLE;

    @FXML
    private TableColumn<Squad, String> tabbleColumnSquadQUANTIDADEDESOLDADOS;

    /**
     * Cria a tela para Registrar um Squad
     */
    @FXML
    private void telaRegistrationSquad() {
        creatUI("registroSquad");
    }


    /**
     * Carrega os dados dos Squads para a TabbleView dos Squads
     *
     * @throws FileNotFoundException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @FXML
    public void atualizarTabbleViewSquad() throws FileNotFoundException, ExecutionException, InterruptedException {
        //Lê do arquivo e adiciona no comboBox
        new Thread(ControllerUtil.t1).start();
        SquadDAO squadDAO = new SquadDAO();

        ObservableList<Squad> olcomboBoxSquadResponsable;
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarTabbleViewSquad(ControllerAnalisesMain.firebase));;


        tabbleViewSquad.setEditable(true);
        tabbleColumnSquadNOME.setEditable(true);
        tabbleColumnSquadID.setCellValueFactory(new PropertyValueFactory<Squad, String>("id"));
        tabbleColumnSquadNOME.setCellValueFactory(new PropertyValueFactory<Squad, String>("name"));
        tabbleColumnSquadREGIONRESPONSABLE.setCellValueFactory(new PropertyValueFactory<Squad, String>("regionResponsable"));
        tabbleColumnSquadQUANTIDADEDESOLDADOS.setCellValueFactory(new PropertyValueFactory<Squad, String>("quantityOfSoldiers"));


        tabbleViewSquad.getColumns().clear();
        tabbleViewSquad.setItems(olcomboBoxSquadResponsable);
        tabbleViewSquad.getColumns().addAll(tabbleColumnSquadID, tabbleColumnSquadNOME, tabbleColumnSquadREGIONRESPONSABLE, tabbleColumnSquadQUANTIDADEDESOLDADOS);
        tabbleViewSquad.setEditable(true);


    }


    /**
     * Abre a tela de Editar Squad
     *
     */
    @FXML
    private void telaEditarSquad() {
        creatUI("telaEditarDadosSquad");

    }

}
