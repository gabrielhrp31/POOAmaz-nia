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

public class SquadDAO {

    /**
     * Carregar os dados dos esquadrões para a comboBox
     *
     * @return
     * @throws FileNotFoundException
     */


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


    public List<Squad> carregarComboBoxSquad(Firebase firebase) throws ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaSquads = firebase.read(1);

        List<Squad> lista = new ArrayList<>();
        Squad squad;
        String nomeSquad = "", idText = "", sQuantityOfSoldiers = " ";
        int quantityOfSoldiers = 0;
        for (int i = 0; i < listaSquads.size(); i++) {
            squad = new Squad();
            nomeSquad = listaSquads.get(i).getString("name");
            sQuantityOfSoldiers = String.valueOf(listaSquads.get(i).get("quantityOfSoldiers"));
            quantityOfSoldiers = Integer.parseInt(sQuantityOfSoldiers);//erro aqui n era p subir o nome no squadresponsavel
            squad.setQuantityOfSoldiers(quantityOfSoldiers);
            squad.setId(i);
            squad.setName(nomeSquad);
            lista.add(squad);
        }

        return lista;
    }

    public List<String> carregarComboBoxSquadEditarDados(Firebase firebase) throws ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaSquads = firebase.read(1);

        List<String> lista = new ArrayList<>();
        Squad squad;
        String nomeSquad = "", idText = "";

        for (int i = 0; i < listaSquads.size(); i++) {
            nomeSquad = listaSquads.get(i).getString("name");
            nomeSquad = nomeSquad.concat("  -> id: ");
            idText = Integer.toString(i);
            nomeSquad = nomeSquad.concat(idText);
            lista.add(nomeSquad);
        }

        return lista;
    }


    public List<Squad> carregarTabbleViewSquad(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaRegions = firebase.read(1);

        List<Squad> lista = new ArrayList<>();

        String id = "", name = "", sRegionResponsavel = "", sQuantityOfSoldiers = " ";

        int quantityOfSoldiers = 0;
        Squad squadMod;

        for (int i = 0; i < listaRegions.size(); i++) {
            squadMod = new Squad();
            squadMod.setId(i);
            squadMod.setName(listaRegions.get(i).getString("name"));
            sRegionResponsavel = listaRegions.get(i).getString("regionResponsable");//tem q arrumar isso, ta convertendo errado
            sQuantityOfSoldiers = String.valueOf(listaRegions.get(i).get("quantityOfSoldiers"));

            quantityOfSoldiers = Integer.parseInt(sQuantityOfSoldiers);//erro aqui n era p subir o nome no squadresponsavel

            squadMod.setRegionResponsable(sRegionResponsavel);

            squadMod.setQuantityOfSoldiers(quantityOfSoldiers);


            lista.add(squadMod);
        }
//arrumar a função p carregar a tabble view (atualizar), se retornar uma lista direito do outro lado já trata
        return lista;
    }


}
