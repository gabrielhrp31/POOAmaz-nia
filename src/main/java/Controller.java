import DAO.Region;
import DAO.Squad;
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
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {


    //ELEMENTOS DOS FXMLS


    @FXML
    private BorderPane borderPane;


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
    private void teste(){
        JOptionPane.showMessageDialog(null,"AAA");
    }

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
        borderPane.setCenter(root);
    }

    //diretorio , esquadrão(pode ser null), regiao     opc se ta salvo
    @FXML
    private void salvarJSON(String dir, Squad squadButton, Region regionButton, int opcSave) throws FileNotFoundException {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dirFile = new File(dir);//diretorio onde irá o arquivo
        List<Squad> listaSquad = null;
        List<Region> listaRegion = null;
        //cuidar o que vai salvar
        boolean docExist = false;
        String gsonString = "";


        if (dirFile.exists()) {//Se já existir uma arquivo ele carrega as informações dele para um List e adiciona as novas na List
            Reader reader = new FileReader(dir);
            if (opcSave == 0) {//OPC =0 , é para salvar os dados do esquadrão
                docExist = true;
                //listaSquad = gson.fromJson(reader, List.class);
                listaSquad.add(squadButton);//adiciona na lista do Esquadrão os dados
            } else {//OPC =1, é para salvar os dados da region
                docExist = true;
                //listaRegion = gson.fromJson(reader, List.class);
                listaRegion.add(regionButton);
            }
        } else {//Nao existir
            //gera só um arquivo mesmo
            if (opcSave == 0) {//SQUAD
                gsonString = "teste";
                //gsonString = gson.toJson(squadButton);
            } else {//REGION
                //gsonString = gson.toJson(regionButton);
            }
        }

        //E AQUI JÁ SALVA O ARQUIVO NO DIRETORIO
        try {
            FileWriter salvaArq = new FileWriter(dirFile); //Cria um fileWriter no diretorio passado pelo dir(obs: o diretorio tem que existir)
            if (docExist) {
                salvaArq.write(String.valueOf(listaSquad));//escreve a Lista no documento
            } else {
                salvaArq.write(String.valueOf(gsonString));//escreve a String no documento
            }
            salvaArq.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    //A FUNÇÃO QUE CARREGA O NOVO FXML \/ , O FXML TEM QUE TER O ID COM O MESMO NOME TBM
    @FXML
    private void telaPessoasMain() {
        loadUI("pessoasMain");
    }


    @FXML
    private void telaDadosTeste(){
        loadUI("dadosMain");

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




    @FXML
    void botaoEnviarRegistroRegion() throws FileNotFoundException {
        Region regionButton = new Region();
        //String squadResponsable; -> TEM QUE APARECER O VALOR NA CHECKBOX PRIMEIRO
        String nomeRegion = comboBoxRegion.getValue();
        regionButton.setName(nomeRegion);
        if(checkBoxProtegida.isSelected()){
            regionButton.setProtectedArea(true);
        }
        else{
            regionButton.setProtectedArea(false);
        }

        regionButton.setSquadResponsable(comboBoxRegion.getValue()); //aqui precisa pegar o valor do checkbox
        //AÍ AQUI CHAMA O SALVAR ARQUIVO PARA REGISTRO, SQUAD NULL, REGISTRO
        //  salvarJSON("information\\registrationRegion.json", null, regionButton, 0);
    }

    @FXML
    void botaoEnviarRegistroSquad() throws FileNotFoundException {
        Squad squadButton = new Squad();
        String nomeSquad = textRegistrationSquadName.getText();
        String numberSquad = textRegistrationSquadQuantitySoldiers.getText();
        int numberSquadInt = Integer.parseInt(numberSquad);
        String region = comboBoxSquad.getValue();

        squadButton.setName(nomeSquad);
        squadButton.setQuantityOfSoldiers(numberSquadInt);
        squadButton.setRegionResponsable(region);
        //AÍ AQUI JÁ ENVIA OS DADOS P SALVAR O ARQUIVO
        salvarJSON("information\\registrationSquad.json", squadButton, null, 0);
    }





}
