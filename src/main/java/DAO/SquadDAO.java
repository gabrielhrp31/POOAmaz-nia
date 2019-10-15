package DAO;

import Models.Region;
import Models.Squad;
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

public class SquadDAO {
    //TUDO RELACIONADO A DADOS DO REGION


    public List<String> carregarComboBoxSquad() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquad = new ArrayList<Squad>();
        listaSquad = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        List<String> listaSquadNomes = new ArrayList<String>();
        for (int i = 0; i < listaSquad.size(); i++) {
            listaSquadNomes.add(listaSquad.get(i).getName());
        }


        return listaSquadNomes;
    }




}
