import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Squad;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private void telaRegistrationSquad() {
        creatUI("registroSquad");
    }

    @FXML
    public void atualizarTabbleViewSquad() throws FileNotFoundException {
        //Lê do arquivo e adiciona no comboBox
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        Reader reader = new FileReader(dir);
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquads;
        listaSquads = gson.fromJson(reader, listType);

        ObservableList<Squad> olcomboBoxSquadResponsable;
        olcomboBoxSquadResponsable = FXCollections.observableList(listaSquads);
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


    @FXML
    private void telaEditarSquad() {
        creatUI("telaEditarDadosSquad");

    }

}
