import models.Region;
import models.Squad;
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


import java.io.*;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerUtil {

    @FXML
    private BorderPane borderPanel;


    /**
     * Cria uma nova tela do FXML
     *
     * @param ui
     */
    @FXML
    void creatUI(java.lang.String ui) {
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


    /**
     * Carrega uma nova tela do FXML
     *
     * @param ui
     */
    @FXML
    void loadUI(java.lang.String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ControllerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        borderPanel.setCenter(root);
    }


    /**
     * Verifica se contém letras no input
     *
     * @param name
     * @return
     */
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }


    /**
     * Verifica se só contém numeros no input
     *
     * @param texto
     * @return
     */
    public static boolean soContemNumeros(String texto) {
        if (texto == null)
            return false;
        for (char letra : texto.toCharArray())
            if (letra < '0' || letra > '9')
                return false;
        return true;
    }


    /**
     * Funçaõ para o salvamento de todos os arquivos Json
     *
     * @param dir
     * @param squadButton
     * @param regionButton
     * @param opcSave
     * @param editar
     * @param idSquadRemover
     * @param idRegionRemover
     * @throws IOException
     */
    @FXML
    private void salvarJSONaux(String dir, Squad squadButton, Region regionButton, int opcSave, int editar, int idSquadRemover, int idRegionRemover) throws IOException, GeneralSecurityException {
       //NAO USA ISSO MAIS P PORRA NENHUMA
        /*
        try {
            gd.downloadFile("application/json", "region.json", System.getProperty("user.dir") + File.separator + "data" + File.separator);
        } catch (IOException e) {
            return;
        }

        try {
            gd.downloadFile("application/json", "squad.json", System.getProperty("user.dir") + File.separator + "data" + File.separator);
        } catch (IOException e) {
            return;
        }

        File dirFile = new File(dir);

        if (dirFile.exists()) {//SE JÁ EXISTIR O ARQUIVO (O .JSON)
            Reader reader = new FileReader(dirFile);//LE OS DADOS DO ARQUIVO

            if (opcSave == 0 && editar == 0) {//OPC =0 -> salvar os dados do esquadrão
                Type listType = new TypeToken<ArrayList<Squad>>() {
                }.getType();
                listaSquad = gson.fromJson(reader, listType);
                squadButton.setId((listaSquad.get(listaSquad.size() - 1).getId()) + 1);
            }
            if (opcSave == 1 && editar == 0) {//OPC =1 -> salvar os dados da region
                Type listType = new TypeToken<ArrayList<Region>>() {
                }.getType();
                listaRegion = gson.fromJson(reader, listType);
                regionButton.setId((listaRegion.get(listaRegion.size() - 1).getId()) + 1);
            }
            if (opcSave == 0 && editar == 1) {//editar arquivo SQUAD
                Type listType = new TypeToken<ArrayList<Squad>>() {
                }.getType();
                listaSquad = gson.fromJson(reader, listType);//nesse momento minha lista tem os valores

                //REMOVE A ANTIGA
                for (int i = 0; i < listaSquad.size(); i++) {
                    if (listaSquad.get(i).getId() == idSquadRemover) {
                        squadButton.setId(listaSquad.get(i).getId());
                        listaSquad.remove(i);
                    }
                }

            }

            if (opcSave == 2 && editar == 1) {//OPC =1 -> REMOVER SQUAD
                Type listType = new TypeToken<ArrayList<Squad>>() {
                }.getType();
                listaSquad = gson.fromJson(reader, listType);//nesse momento minha lista tem os valores

                //REMOVE A ANTIGA
                for (int i = 0; i < listaSquad.size(); i++) {
                    if (listaSquad.get(i).getId() == idSquadRemover) {
                        listaSquad.remove(i);
                    }
                }

            }

            if (opcSave == 1 && editar == 1) {//editar arquivo REGION
                Type listType = new TypeToken<ArrayList<Region>>() {
                }.getType();
                listaRegion = gson.fromJson(reader, listType);


                for (int i = 0; i < listaRegion.size(); i++) {
                    if (listaRegion.get(i).getId() == idRegionRemover) {
                        regionButton.setId(listaRegion.get(i).getId());
                        listaRegion.remove(i);
                    }
                }
            }
        } else {
            if (squadButton != null) {
                squadButton.setId(1);//ARQUIVO NAO EXISTIR
            }
            if (regionButton != null) {
                regionButton.setId(1);
            }

        }
        //arquivo nao existir vem p cá direto, caso existir trata antes e dps vem
        if (opcSave == 0) {//SQUAD
            listaSquad.add(squadButton);//aqui manipulo o id
            gsonString = gson.toJson(listaSquad);
        }
        if (opcSave == 1) {//REGION
            listaRegion.add(regionButton);
            gsonString = gson.toJson(listaRegion);
        }
        if (opcSave == 2) {//REMOVER SQUAD
            gsonString = gson.toJson(listaSquad);
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
        */
    }

    /**
     * Chama a função para salvar apenas verifica antes se precisa criar uma nova pasta ou não
     *
     * @param dir
     * @param squadButton
     * @param regionButton
     * @param opcSave
     * @param editar
     * @param idSquadRemover
     * @param idRegionRemover
     * @throws IOException
     */
    @FXML
    void salvarJSON(String dir, Squad squadButton, Region regionButton, int opcSave, int editar, int idSquadRemover, int idRegionRemover) throws IOException, GeneralSecurityException {
        File dirDataFile = new File(System.getProperty("user.dir") + File.separator + "data");

        if (dirDataFile.exists()) {//SE A PASTA EXISTIR
            salvarJSONaux(dir, squadButton, regionButton, opcSave, editar, idSquadRemover, idRegionRemover);//SÓ CHAMA A FUNÇÃO
        } else {
            dirDataFile.mkdir();//CRIA UMA NOVA PASTA NO DIRETORIO E DPS CHAMA A FUNÇÃO
            salvarJSONaux(dir, squadButton, regionButton, opcSave, editar, idSquadRemover, idRegionRemover);
        }

    }

}
