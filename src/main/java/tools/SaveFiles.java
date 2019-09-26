package tools;

import DAO.Region;
import DAO.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveFiles {

    public void saveJson(String dir, Squad squadButton, Region regionButton, int opcSave) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dirFile = new File(dir);
        List<Squad> listaSquad = null;
        List<Region> listaRegion = null;
        boolean docExist = false;
        String gsonString = "";


        if (dirFile.exists()) {//Se já existir uma arquivo ele carrega as informações dele para um List e adiciona as novas na List
            Reader reader = new FileReader(dir);
            if (opcSave == 0) {
                docExist = true;
                listaSquad = gson.fromJson(reader, List.class);
                listaSquad.add(squadButton);
            } else {
                docExist = true;
                listaRegion = gson.fromJson(reader, List.class);
                listaRegion.add(regionButton);
            }
        } else {//Nao existir
            if (opcSave == 0) {//SQUAD
                gsonString = gson.toJson(squadButton);
            } else {//REGION
                gsonString = gson.toJson(regionButton);
            }
        }


        try {
            FileWriter salvaArq = new FileWriter(dirFile); //Cria um fileWriter no diretorio passado pelo dir
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


}
