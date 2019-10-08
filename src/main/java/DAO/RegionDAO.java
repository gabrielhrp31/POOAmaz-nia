package DAO;

import Models.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {
    //TUDO RELACIONADO A DADOS DO REGION


    public List<String> carregarComboBoxRegion() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Region>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Region> listaRegion = new ArrayList<Region>();
        listaRegion = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        List<String> listaRegionNomes = new ArrayList<String>();
        for (int i = 0; i < listaRegion.size(); i++) {
            listaRegionNomes.add(listaRegion.get(i).getName());
        }


        return listaRegionNomes;
    }






}
