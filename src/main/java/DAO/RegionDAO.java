package DAO;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import models.Image;
import models.Region;
import tools.Firebase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegionDAO {


    /**
     * Carrega os dados para a ComboBox da Region
     *
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws FileNotFoundException
     * @throws ExecutionException
     * @throws InterruptedException
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


    /**
     * Carrega o comboBoxRegion com os dados necessários para a region
     * na aba de editar dados da Region
     *
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws FileNotFoundException
     * @throws ExecutionException
     * @throws InterruptedException
     */
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


    /**
     * Carrega os daados das Regions para a TabbleView para exibir
     *
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws FileNotFoundException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<Region> carregarTabbleViewRegion(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

        List<QueryDocumentSnapshot> listaRegions = firebase.read(0);

        List<Region> lista = new ArrayList<>();

        String id = "", name = "", sSquadResponsavel = "";
        String protecaoAmbiente=" ",regioesUrbanas=" ";
        Boolean protegido = false;
        int squadResponsavel = 0;
        Region regionMod;

        for (int i = 0; i < listaRegions.size(); i++) {
            regionMod = new Region();
            regionMod.setId(i);
            regionMod.setName(listaRegions.get(i).getString("name"));
            sSquadResponsavel = String.valueOf(listaRegions.get(i).get("squadResponsable"));
            squadResponsavel = Integer.parseInt(sSquadResponsavel);
            regionMod.setSquadResponsable(squadResponsavel);
            protegido = listaRegions.get(i).getBoolean("protectedArea");
            regionMod.setProtectedArea(protegido);
            regionMod.setUrbanRegion(listaRegions.get(i).getString("urbanRegion"));
            regionMod.setEnvironmentalProtection(listaRegions.get(i).getString("environmentalProtection"));
            lista.add(regionMod);
        }
        return lista;
    }


    private static ArrayList<Image> regions;

    /**
     * Construtor da classe
     */
    public RegionDAO() {
        regions = new ArrayList<>();
    }


    /**
     * Seta a regiao
     * @param list recebe uma lista de regions para adicionar
     */
    public static void setRegions(ArrayList<Region> list) {
        RegionDAO.regions = regions;
    }


}
