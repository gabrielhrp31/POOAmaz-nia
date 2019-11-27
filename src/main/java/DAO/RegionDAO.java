package DAO;

import Exceptions.conexaoError;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import models.Image;
import models.Region;
import tools.Firebase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegionDAO implements InterfaceDAO {


    /**
     * Carrega os dados para a ComboBox da Region
     *
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> carregarComboBox(Firebase firebase) throws ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> listaRegions = null;

        //Tenta conectar e baixar os dados
        try {
            listaRegions = firebase.read(1);
        } catch (conexaoError e) {
            e.getMessage();
        }

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
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<Region> carregarComboBoxEditarDados(Firebase firebase) throws
            ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> listaRegions = null;


        //Tenta conectar e baixar os dados
        try {
            listaRegions = firebase.read(0);
        } catch (conexaoError e) {
            e.getMessage();
        }

        Region region;
        List<Region> lista = new ArrayList<>();

        String nomeRegion = "";

        for (int i = 0; i < listaRegions.size(); i++) {
            region = new Region();
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
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<Region> carregarTabbleView(Firebase firebase) throws
            ExecutionException, InterruptedException {
        List<QueryDocumentSnapshot> listaRegions = null;
        try {
            listaRegions = firebase.read(0);
        } catch (conexaoError e) {
            e.getMessage();
        }

        List<Region> lista = new ArrayList<>();

        String sSquadResponsavel = "";
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
     *
     * @param list recebe uma lista de regions para adicionar
     */
    public static void setRegions(ArrayList<Region> list) {
        RegionDAO.regions = regions;
    }


}
