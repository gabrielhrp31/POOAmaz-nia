import Models.Region;
import Models.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerListarDados extends ControllerUtil {


    @FXML
    private ComboBox<String> comboBoxSquadListar;

    @FXML
    private ComboBox<String> comboBoxRegionListar;


    /**
     * Carrega os dados dos Esquadrões para o CheckBox
     *
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
        ObservableList<String> olcomboBoxSquadResponsable;

        List<String> listaNomes = new ArrayList<>();
        String nomeSquad = "", quantidadeSoldados = "",idText = "";
        for (int i = 0; i < listaSquads.size(); i++) {
            nomeSquad = listaSquads.get(i).getName();
            quantidadeSoldados = Integer.toString(listaSquads.get(i).getQuantityOfSoldiers());
            nomeSquad = nomeSquad.concat(" : Quantidade de Soldados: ");
            nomeSquad = nomeSquad.concat(quantidadeSoldados);
            nomeSquad = nomeSquad.concat("  -> id: ");
            idText = Integer.toString(listaSquads.get(i).getId());
            nomeSquad = nomeSquad.concat(idText);

            listaNomes.add(nomeSquad);
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);
        comboBoxSquadListar.setItems(olcomboBoxSquadResponsable);
    }


    /**
     * Carregar os dados dos regions no checkBox
     *
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
        String nomeRegion = "", idText = "";
        for (int i = 0; i < listaRegions.size(); i++) {
            nomeRegion = listaRegions.get(i).getName();
            nomeRegion = nomeRegion.concat("  -> id: ");
            idText = Integer.toString(listaRegions.get(i).getId());
            nomeRegion = nomeRegion.concat(idText);
            listaNomes.add(nomeRegion);
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);
        comboBoxRegionListar.setItems(olcomboBoxSquadResponsable);
    }


}
