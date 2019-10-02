import DAO.Region;
import DAO.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.beans.Visibility;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {


    //ELEMENTOS DOS FXMLS


    @FXML
    private HBox hbox;


    @FXML
    private BorderPane borderPanel;

    @FXML
    private TextField textRegistrationRegionName;

    @FXML
    private TextField textRegistrationSquadName;

    @FXML
    private TextField textRegistrationSquadQuantitySoldiers;

    @FXML
    private ComboBox<String> comboBoxSquad;

    @FXML
    private ComboBox<String> comboBoxRegion;

    @FXML
    private CheckBox checkBoxProtegida;


    //FUNÇÕES

    //CRIA UMA NOVA TELA


    @FXML
    private void creatUI(String ui) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
            Stage stage = new Stage();
            stage.setTitle("Tela");
            stage.setScene(new Scene(root, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //CARREGA UMA NOVA TELA NA MESMA SCENE
    @FXML
    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        borderPanel.setCenter(root);
    }

    //diretorio , esquadrão(pode ser null), regiao     opc se ta salvo
    @FXML
    private void salvarJSON(String dir, Squad squadButton, Region regionButton, int opcSave) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dirFile = new File(dir);//diretorio onde irá o arquivo
        List<Squad> listaSquad = null;
        List<Region> listaRegion = null;
        String gsonString = "";


        if (dirFile.exists()) {//SE JÁ EXISTIR O ARQUIVO
            Reader reader = new FileReader(dir);//le os dados do arquivo
            if (opcSave == 0) {//OPC =0 , salvar os dados do esquadrão
                Type listType = new TypeToken<List<Squad>>() {
                }.getType();
                listaSquad = gson.fromJson(reader, listType);
                listaSquad.add(squadButton);//adiciona na lista do Esquadrão os dados
            } else {//OPC =1, salvar os dados da region
                Type listType = new TypeToken<List<Region>>() {
                }.getType();
                listaRegion = gson.fromJson(reader, listType);
                listaRegion.add(regionButton);
            }
        } else {//SE NAO EXISTIR
            //AQUI TEM CRIAR A PASTA
            
            new File("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\teste.txt");
            if (opcSave == 0) {//SQUAD
                gsonString = gson.toJson(squadButton);
            } else {//REGION
                gsonString = gson.toJson(regionButton);
            }

        }


        //E AQUI SALVA O ARQUIVO NO DIRETORIO
        try {
            //TERMINAR SA MERDA P SALVAR AQUI
            FileWriter salvaArq = new FileWriter(dirFile); //Cria um fileWriter no diretorio passado pelo dir(obs: o diretorio tem que existir)
            //₢riar a pasta data aqui
            salvaArq.write(gsonString);//escreve a String no documento
            salvaArq.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }


    }


    //A FUNÇÃO QUE CARREGA O NOVO FXML \/ , O FXML TEM QUE TER O ID COM O MESMO NOME TBM
    @FXML
    private void telaCadastroMain() {
        loadUI("cadastroMain");
    }


    @FXML
    private void telaAnalisesMain() {
        loadUI("analisesMain");
    }

    @FXML
    private void telaRegistrationRegion() {
        //SÓ PODE ABRIR SE JÁ EXISTIR UM SQUAD REGISTRADO
        File squadRegistration = new File("information\\registrationSquad.json");
        if (squadRegistration != null) { //SE JÁ EXISTIR UM ESQUADRÃO REGISTRADO AO MENOS
            creatUI("registroRegion");
        } else {
            JOptionPane.showMessageDialog(null, "Primeiro registre um Esquadrão");
        }

    }


    @FXML
    private void telaRegistrationSquad() {
        creatUI("registroSquad");
    }


    //AQUI TEM QUE ARRUAMR PARA SALVAR O JSON
    @FXML
    void botaoEnviarRegistroRegion() throws FileNotFoundException {
        Region regionButton = new Region();
        //String squadResponsable; -> TEM QUE APARECER O VALOR NA CHECKBOX PRIMEIRO
        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);
        if (checkBoxProtegida.isSelected()) {
            regionButton.setProtectedArea(true);
        } else {
            regionButton.setProtectedArea(false);
        }

        regionButton.setSquadResponsable(comboBoxRegion.getValue()); //aqui precisa pegar o valor do checkbox
        //AÍ AQUI CHAMA O SALVAR ARQUIVO PARA REGISTRO, SQUAD NULL, REGISTRO
        //  salvarJSON("information\\registrationRegion.json", null, regionButton, 0);
    }

    //AQUI TEM QUE ARRUAMR PARA SALVAR O JSON TBM
    @FXML
    void botaoEnviarRegistroSquad() throws IOException {
        Squad squadButton = new Squad();
        String nomeSquad = textRegistrationSquadName.getText();
        String numberSquad = textRegistrationSquadQuantitySoldiers.getText();
        int numberSquadInt = Integer.parseInt(numberSquad);
        String region = comboBoxSquad.getValue();

        squadButton.setName(nomeSquad);
        squadButton.setQuantityOfSoldiers(numberSquadInt);
        squadButton.setRegionResponsable(region);
        //AÍ AQUI JÁ ENVIA OS DADOS P SALVAR O ARQUIVO
        salvarJSON("\\src\\main\\java\\Data", squadButton, null, 0);
    }


}
