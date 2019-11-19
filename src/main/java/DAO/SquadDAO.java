package DAO;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Region;
import models.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import tools.Firebase;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SquadDAO{

    /**
     * Carregar os dados dos esquadrões para a comboBox
     *
     * @return
     * @throws FileNotFoundException
     */
    public List<String> carregarComboBoxSquad(Firebase firebase) throws IOException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaSquads = firebase.read(1);


        List<String> listaNomes = new ArrayList<>();
        String nomeSquad = "", idText = "";
        for (int i = 0; i < listaSquads.size(); i++) {
            nomeSquad = listaSquads.get(i).getString("name");
            nomeSquad = nomeSquad.concat("  -> id: ");
            idText = Integer.toString(i);
            nomeSquad = nomeSquad.concat(idText);
            listaNomes.add(nomeSquad);
        }


        return listaNomes;
    }


    public List<String> carregarComboBoxRegion(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaRegions = firebase.read(0);


        List<String> listaNomes = new ArrayList<>();

        String nomeRegion = "", idText = "";

        for (int i = 0; i < listaRegions.size(); i++) {
            nomeRegion = listaRegions.get(i).getString("name");
            nomeRegion = nomeRegion.concat("  -> id: ");
            idText = Integer.toString(i);
            nomeRegion = nomeRegion.concat(idText);
            listaNomes.add(nomeRegion);
        }

        return listaNomes;
    }


}
