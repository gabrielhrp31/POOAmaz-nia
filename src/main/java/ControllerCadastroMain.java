import javafx.fxml.FXML;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class ControllerCadastroMain extends ControllerUtil {
    @FXML
    private void telaRegistrationRegion() throws FileNotFoundException {
        //SÓ PODE ABRIR SE JÁ EXISTIR UM SQUAD REGISTRADO
        File file = new File("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\squadJ.json");
        if (file.exists()) { //SE JÁ EXISTIR UM ESQUADRÃO REGISTRADO AO MENOS
//            //CARREGAR OS DADOS DO ESQUADRÃO PARA A CHECKBOX
//            Region regionButton = new Region();
//            File dir = new File("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\squadJ.json");
//            Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
//            Type listType = new TypeToken<ArrayList<Region>>() {
//            }.getType();
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            List<Region> listaRegion = new ArrayList<Region>();
//            listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
//            ObservableList<String> olcomboBoxSquadResponsable;
//            List<String> listaRegionNomes = new ArrayList<String>();
//
//            for (int i = 0; i < listaRegion.size(); i++) {
//                listaRegionNomes.add(listaRegion.get(i).getName());
//            }
//            //olcomboBoxSquadResponsable = FXCollections.observableList(listaRegionNomes);
//
//            //comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);
//


            //OBS: TEM QUE INICIAR OS VALORES DO ARRAYLIST, NO CHECKBOX ANTES
            creatUI("registroRegion");
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro registre um Esquadrao");
        }

    }
    @FXML
    private void telaRegistrationSquad() {
        creatUI("registroSquad");
    }




}
