import DAO.Region;
import DAO.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerUtil{


    //ATRIBUTOS
    @FXML
    private BorderPane borderPanel;


    //FUNÇÕES
    @FXML
    void creatUI(String ui) {
        Parent root = null;
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



    @FXML
    void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ControllerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        borderPanel.setCenter(root);
    }


    //diretorio , esquadrão(pode ser null), regiao(pode ser null),     opc (0,1)
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
    void salvarJSON(String dir, Squad squadButton, Region regionButton, int opcSave) throws IOException {
        //AQUI VERIFICA SE A PASTA EXISTE ANTES DE CHAMAR A FUNÇÃO QUE VAI GERAR O ARQUIVO
        File dirDataFile = new File(System.getProperty("user.dir")+ File.separator+"data");

        if (dirDataFile.exists()) {//SE A PASTA EXISTIR
            salvarJSONaux(dir, squadButton, regionButton, opcSave);//SÓ CHAMA A FUNÇÃO
        } else {
            dirDataFile.mkdir();//CRIA UMA NOVA PASTA NO DIRETORIO E DPS CHAMA A FUNÇÃO
            salvarJSONaux(dir, squadButton, regionButton, opcSave);
        }

    }






}
