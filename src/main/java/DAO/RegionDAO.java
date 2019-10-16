package DAO;

import models.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import tools.GsonTool;

import java.io.*;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {

    /**
     * Carrega todos os dados dos esquadrões para o comboBox usado no region
     *
     * @return
     * @throws FileNotFoundException
     */
    public List<String> carregarComboBoxRegion() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        Reader reader = new FileReader(dir);
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegions = new ArrayList<Region>();
        listaRegions = gson.fromJson(reader, listType);
        List<String> listaNomes = new ArrayList<String>();
        String nomeRegion = "", idText = "";
        for (int i = 0; i < listaRegions.size(); i++) {
            nomeRegion = listaRegions.get(i).getName();
            nomeRegion = nomeRegion.concat("  -> id: ");
            idText = Integer.toString(listaRegions.get(i).getId());
            nomeRegion = nomeRegion.concat(idText);
            listaNomes.add(nomeRegion);
        }

        return listaNomes;
    }


    private static ArrayList<Object> regions;

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
    public static void addRegion(Region region) throws IOException, GeneralSecurityException {
        GsonTool.read("region.json", 1);
        RegionDAO.regions.add(region);
        GsonTool.write("regions.json", regions);
    }

    public static void setRegions(ArrayList<Region> list) {
        RegionDAO.regions = regions;
    }


}
