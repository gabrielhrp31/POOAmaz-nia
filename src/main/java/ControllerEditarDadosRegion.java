import DAO.RegionDAO;
import DAO.SquadDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import models.Region;
import models.Squad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import static analysis.Main.firebase;

public class ControllerEditarDadosRegion extends ControllerUtil {

    @FXML
    private ComboBox<String> comboBoxRegionEditar;

    @FXML
    private ComboBox<String> checkBSqudEditarRegion;

    @FXML
    private CheckBox checkBoxProtegidaEditarRegion;

    @FXML
    private CheckBox checkboxRegiaoUrbana;

    @FXML
    private TextField textBoxRegiaoUrbana;

    @FXML
    private CheckBox checkBoxProtecaoAmbiental;

    @FXML
    private TextField textBoxProtecaoAmbiental;


    /**
     * Carrega para a CheckBox as regiões já cadastradas
     *
     * @throws FileNotFoundException
     */
    @FXML
    public void carregarCheckBoxRegion() throws FileNotFoundException, ExecutionException, InterruptedException {
        SquadDAO squadDAO = new SquadDAO();
        ObservableList<String> olcomboBoxSquadResponsable = null;
        //dentro da .carregarComboBox tem o try catch já
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBox(firebase));
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
        //dentro da .carregarComboBox tem o try catch já
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxEditarDados(firebase));
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
        //dentro da .carregarComboBox tem o try catch já
        olcomboBoxRegionResponsable = FXCollections.observableList(regionDAO.carregarComboBoxEditarDados(firebase));
        olcomboBoxSquadResponsable = FXCollections.observableList(squadDAO.carregarComboBoxSquad(firebase));


        String nomeRegion = " ", nomeSquad = " ";
        String idText = " ";
        int t = 0;

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

                if (checkBoxProtecaoAmbiental.isSelected()) {
                    olcomboBoxRegionResponsable.get(i).setEnvironmentalProtection(textBoxProtecaoAmbiental.getText());
                } else {
                    olcomboBoxRegionResponsable.get(i).setEnvironmentalProtection("Nao e uma area de Protecao Ambiental");
                }
                if (checkboxRegiaoUrbana.isSelected()) {
                    olcomboBoxRegionResponsable.get(i).setUrbanRegion(textBoxRegiaoUrbana.getText());
                } else {
                    olcomboBoxRegionResponsable.get(i).setUrbanRegion("Nao e uma regiao Urbana");
                }

                //no write tem o try catch
                firebase.write(0, olcomboBoxRegionResponsable.get(i).getId(), olcomboBoxRegionResponsable.get(i).getName(), olcomboBoxRegionResponsable.get(i).getProtectedArea(), olcomboBoxRegionResponsable.get(i).getSquadResponsable(), olcomboBoxRegionResponsable.get(i).getEnvironmentalProtection(), olcomboBoxRegionResponsable.get(i).getUrbanRegion(), 0, null);
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

        //dentro da .carregarComboBox tem o try catch já
        olcomboBoxRegionResponsable = FXCollections.observableList(regionDAO.carregarComboBoxEditarDados(firebase));


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
                firebase.remove("regions", nomeRegionAux);
                JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }


    }
}
