package DAO;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import models.Image;
import models.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Squad;
import tools.Firebase;
import tools.GsonTool;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegionDAO {

    private List<String> listaNomes;

    /**
     * Carrega todos os dados dos esquadrões para o comboBox usado no region
     *
     * @return
     * @throws FileNotFoundException
     */

    public List<String> carregarComboBoxRegion(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaRegions = firebase.read(1);


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


    public List<Region> carregarComboBoxRegionEditarDados(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaRegions = firebase.read(0);

        Region region;
        List<Region> lista = new ArrayList<>();

        String nomeRegion = "", idText = "";

        for (int i = 0; i < listaRegions.size(); i++) {
             region= new Region();
            nomeRegion = listaRegions.get(i).getString("name");
            region.setName(nomeRegion);
            region.setId(i);

            lista.add(region);
        }

        return lista;
    }


    public List<Region> carregarTabbleViewRegion(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaRegions = firebase.read(0);

        List<Region> lista = new ArrayList<>();

        String id = "", name = "", sSquadResponsavel = "";
        Boolean protegido = false;
        int squadResponsavel = 0;
        Region regionMod;

        for (int i = 0; i < listaRegions.size(); i++) {
            regionMod = new Region();
            regionMod.setId(i);
            regionMod.setName(listaRegions.get(i).getString("name"));
            sSquadResponsavel = String.valueOf(listaRegions.get(i).get("squadResponsable"));//tem q arrumar isso, ta convertendo errado
            squadResponsavel = Integer.parseInt(sSquadResponsavel);//erro aqui n era p subir o nome no squadresponsavel

            regionMod.setSquadResponsable(squadResponsavel);
            protegido = listaRegions.get(i).getBoolean("protectedArea");
            regionMod.setProtectedArea(protegido);


            lista.add(regionMod);
        }
//arrumar a função p carregar a tabble view (atualizar), se retornar uma lista direito do outro lado já trata
        return lista;
    }


    private static ArrayList<Image> regions;

    public RegionDAO() {
        regions = new ArrayList<>();
    }


    /**
     * Adiciona uma nova region no region.json
     *
     * @param region
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static void addRegion(Image region) throws IOException, GeneralSecurityException {
        GsonTool.read("region.json", 1);
        RegionDAO.regions.add(region);
        GsonTool.write("regions.json", regions);
    }

    public static void setRegions(ArrayList<Region> list) {
        RegionDAO.regions = regions;
    }


}
