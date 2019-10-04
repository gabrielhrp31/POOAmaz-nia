import DAO.Region;
import DAO.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
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

    @FXML
    public ComboBox<String> comboBoxSquadResponsable;


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

    //diretorio , esquadrão(pode ser null), regiao(pode ser null),     opc se ta salvo
    @FXML
    private void salvarJSONaux(String dir, Squad squadButton, Region regionButton, int opcSave) throws IOException {
        //AQUI É ONDE GERA O ARQUIVO
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dirFile = new File(dir);//diretorio onde irá o arquivo
        List<Squad> listaSquad = new ArrayList<Squad>();
        List<Region> listaRegion = new ArrayList<Region>();
        String gsonString = "";

        /*PRIMEIRO VERIFICA SE O ARQUIVO JÁ  EXISTE, E DPS SE VAI SALVAR UM SQUAD OU UMA REGION*/

        if (dirFile.exists()) {//SE JÁ EXISTIR O ARQUIVO (O .JSON)
            Reader reader = new FileReader(dirFile);//LE OS DADOS DO ARQUIVO

            if (opcSave == 0) {//OPC =0 -> salvar os dados do esquadrão
                Type listType = new TypeToken<ArrayList<Squad>>() {
                }.getType();
                listaSquad = gson.fromJson(reader, listType);//ERRO AQUI, NA HORA DE ADICIONAR NA LISTA OS DADOS LIDOS DO ARQUIVO
            } else {//OPC =1 -> salvar os dados da region
                Type listType = new TypeToken<ArrayList<Region>>() {
                }.getType();
                listaRegion = gson.fromJson(reader, listType);
            }
        }

        if (opcSave == 0) {//SQUAD
            listaSquad.add(squadButton);
            gsonString = gson.toJson(listaSquad);
        } else {//REGION
            listaRegion.add(regionButton);
            gsonString = gson.toJson(listaRegion);
        }

        //E AQUI GERA O ARQUIVO COM OS DADOS NO DIRETÓRIO
        try {
            FileWriter salvaArq = new FileWriter(dirFile); //Cria um fileWriter no diretorio passado pelo dir(obs: o diretorio tem que existir)
            salvaArq.write(gsonString);//escreve a String no documento
            salvaArq.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void salvarJSON(String dir, Squad squadButton, Region regionButton, int opcSave) throws IOException {
        //AQUI VERIFICA SE A PASTA EXISTE ANTES DE CHAMAR A FUNÇÃO QUE VAI GERAR O ARQUIVO
        File dirDataFile = new File("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data");

        if (dirDataFile.exists()) {//SE A PASTA EXISTIR
            salvarJSONaux(dir, squadButton, regionButton, opcSave);//SÓ CHAMA A FUNÇÃO
        } else {
            dirDataFile.mkdir();//CRIA UMA NOVA PASTA NO DIRETORIO E DPS CHAMA A FUNÇÃO
            salvarJSONaux(dir, squadButton, regionButton, opcSave);
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
    private void telaRegistrationRegion() throws FileNotFoundException {
        //SÓ PODE ABRIR SE JÁ EXISTIR UM SQUAD REGISTRADO
        File file = new File("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\squadJ.json");
        if (file.exists()) { //SE JÁ EXISTIR UM ESQUADRÃO REGISTRADO AO MENOS
            //CARREGAR OS DADOS DO ESQUADRÃO PARA A CHECKBOX
            Region regionButton = new Region();
            File dir = new File("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\squadJ.json");
            Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
            Type listType = new TypeToken<ArrayList<Region>>() {
            }.getType();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<Region> listaRegion = new ArrayList<Region>();
            listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
            ObservableList<String> olcomboBoxSquadResponsable;
            List<String> listaRegionNomes = new ArrayList<String>();

            for (int i = 0; i < listaRegion.size(); i++) {
                listaRegionNomes.add(listaRegion.get(i).getName());
            }
            //olcomboBoxSquadResponsable = FXCollections.observableList(listaRegionNomes);

            //comboBoxSquadResponsable.setItems(olcomboBoxSquadResponsable);



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


    //AQUI TEM QUE ARRUAMR PARA SALVAR O JSON
    @FXML
    void botaoEnviarRegistroRegion() throws IOException {
        //DEPOIS TIRAR ISSO DE CARREGAR PARA A CHECKBOX AQUI
        Region regionButton = new Region();
        File dir = new File("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\squadJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegion = new ArrayList<Region>();
        listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        ObservableList<String> olcomboBoxSquadResponsable = null;

        List<String> listaRegionNomes = new ArrayList<String>();

        for (int i = 0; i < listaRegion.size(); i++) {
            listaRegionNomes.add(listaRegion.get(i).getName());
        }

        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);
        if (checkBoxProtegida.isSelected()) {
            regionButton.setProtectedArea(true);
        } else {
            regionButton.setProtectedArea(false);
        }
        regionButton.setSquadResponsable(comboBoxSquadResponsable.getValue()); //aqui precisa pegar o valor do checkbox


        salvarJSON("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\regionJ.json", null, regionButton, 1);
    }




    @FXML
    void botaoEnviarRegistroSquad() throws IOException {
        //AQUI INSTANCIA E FAZ AS CONVERSÕES, E ATRIBUIÇÕES AO OBJETO, E DPS PASSA O OBJETO
        //COMO PARAMETRO
        Squad squadButton = new Squad();
        String nomeSquad = textRegistrationSquadName.getText();
        String numberSquad = textRegistrationSquadQuantitySoldiers.getText();
        int numberSquadInt = Integer.parseInt(numberSquad);
        String region = comboBoxSquad.getValue();

        squadButton.setName(nomeSquad);
        squadButton.setQuantityOfSoldiers(numberSquadInt);
        squadButton.setRegionResponsable(region);
        //AÍ AQUI JÁ ENVIA OS DADOS P SALVAR O ARQUIVO
        //DPS TROCAR ESSE DIR POR UM CAMINHO RELATIVO E NAO UM PATH COMPLETO
        //OPCSAVE=0 ->SQUAD
        salvarJSON("C:\\Users\\Atlas\\Documents\\POOamazoniaAnalysis\\src\\main\\java\\Data\\squadJ.json", squadButton, null, 0);
    }


}
