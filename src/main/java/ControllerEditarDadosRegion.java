import DAO.RegionDAO;
import DAO.SquadDAO;
import javafx.event.ActionEvent;
import models.Region;
import models.Squad;
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
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ControllerEditarDadosRegion extends ControllerUtil {

    @FXML
    private ComboBox<String> comboBoxRegionEditar;

    @FXML
    private ComboBox<String> checkBSqudEditarRegion;

    @FXML
    private CheckBox checkBoxProtegidaEditarRegion;


    /**
     * Carrega para a CheckBox as regiões já cadastradas
     *
     * @throws FileNotFoundException
     */
    @FXML
    public void carregarCheckBoxRegion() throws FileNotFoundException, ExecutionException, InterruptedException {
        SquadDAO squadDAO = new SquadDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxRegion(ControllerAnalisesMain.firebase));
        comboBoxRegionEditar.setItems(olcomboBoxSquadResponsable);
    }


    /**
     * Carrega os dados dos Esquadrões para uma CheckBox
     *
     * @throws FileNotFoundException
     */
    @FXML
    public void carregarCheckBoxSquad() throws FileNotFoundException, ExecutionException, InterruptedException {
        SquadDAO squadDAO = new SquadDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxSquadEditarDados(ControllerAnalisesMain.firebase));
        checkBSqudEditarRegion.setItems(olcomboBoxSquadResponsable);
    }


    /**
     * Salva as modificações feitas naquela region
     *
     * @throws IOException
     */
    @FXML
    public void botaoSalvarRegion() throws IOException, GeneralSecurityException, ExecutionException, InterruptedException {
        RegionDAO regionDAO = new RegionDAO();
        SquadDAO squadDAO = new SquadDAO();
        ObservableList<Region> olcomboBoxRegionResponsable;
        ObservableList<Squad> olcomboBoxSquadResponsable;
        olcomboBoxRegionResponsable = FXCollections.observableList(regionDAO.carregarComboBoxRegionEditarDados(ControllerAnalisesMain.firebase));
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxSquad(ControllerAnalisesMain.firebase));


        String nomeRegion = " ", nomeSquad = " ", sQuantityOfSoldiers;
        String idText = " ";
        int quantityOfSoldiers = 0, t = 0, tnomeSquad = 0;

        for (int i = 0; i < olcomboBoxRegionResponsable.size(); i++) {
            nomeRegion = String.valueOf(olcomboBoxRegionResponsable.get(i).getName());
            nomeRegion = nomeRegion.concat("  -> id: ");
            idText = Integer.toString(i);
            nomeRegion = nomeRegion.concat(idText);

            if (comboBoxRegionEditar.getValue().equals(nomeRegion)) {//se o olcomboBox get String name == ao comboBox, atribui os dados
                //p pegar a region responsable :
                for (t = 0; t < olcomboBoxSquadResponsable.size(); t++) {
                    nomeSquad = String.valueOf(olcomboBoxRegionResponsable.get(t).getName());
                    nomeSquad = nomeRegion.concat("  -> id: ");
                    idText = Integer.toString(i);
                    nomeSquad = nomeSquad.concat(idText);

                    //concat com o id, p verificar e na hr de add só o nome normal o puro por isso
                    if (checkBSqudEditarRegion.getValue().equals(nomeSquad)) {
                        olcomboBoxRegionResponsable.get(i).setSquadResponsable(olcomboBoxSquadResponsable.get(t).getId());
                    }
                }

                ControllerAnalisesMain.firebase.write(0, olcomboBoxRegionResponsable.get(i).getId(), olcomboBoxRegionResponsable.get(i).getName(), olcomboBoxRegionResponsable.get(i).getProtectedArea(), olcomboBoxRegionResponsable.get(i).getSquadResponsable(),olcomboBoxRegionResponsable.get(i).getProtecaoAmbiente(),olcomboBoxRegionResponsable.get(i).getRegioesUrbana(),0, null);
                JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }


    /**
     * Remove uma regiaõ
     *
     * @param actionEvent chama o evento para remover
     * @throws FileNotFoundException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void removerRegion(ActionEvent actionEvent) throws FileNotFoundException, ExecutionException, InterruptedException {
        RegionDAO regionDAO = new RegionDAO();

        ObservableList<Region> olcomboBoxRegionResponsable;

        olcomboBoxRegionResponsable = FXCollections.observableList(regionDAO.carregarComboBoxRegionEditarDados(ControllerAnalisesMain.firebase));


        String nomeRegion = " ", nomeRegionAux = " ";
        String idText = " ";
        int t = 0, nomeRegionIntAux = 0;

        for (int i = 0; i < olcomboBoxRegionResponsable.size(); i++) {
            nomeRegion = String.valueOf(olcomboBoxRegionResponsable.get(i).getName());
            nomeRegion = nomeRegion.concat("  -> id: ");
            idText = Integer.toString(i);
            nomeRegion = nomeRegion.concat(idText);


            if (comboBoxRegionEditar.getValue().equals(nomeRegion)) {
                nomeRegionIntAux = olcomboBoxRegionResponsable.get(i).getId();
                nomeRegionAux = Integer.toString(nomeRegionIntAux);
                ControllerAnalisesMain.firebase.remove("regions", nomeRegionAux);
                JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }


    }
}
