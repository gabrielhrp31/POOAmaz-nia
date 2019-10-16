package DAO;

import models.Squad;
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

    /**
     * Carregar os dados dos esquadrões para a comboBox
     *
     * @return
     * @throws FileNotFoundException
     */
    public List<String> carregarComboBoxSquad() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
        Reader reader = new FileReader(dir);
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquads = new ArrayList<Squad>();
        listaSquads = gson.fromJson(reader, listType);

        List<String> listaNomes = new ArrayList<>();
        String nomeSquad = "", quantidadeSoldados = "", idText = "";
        for (int i = 0; i < listaSquads.size(); i++) {
            nomeSquad = listaSquads.get(i).getName();
            quantidadeSoldados = Integer.toString(listaSquads.get(i).getQuantityOfSoldiers());
            nomeSquad = nomeSquad.concat(" : Quantidade de Soldados: ");
            nomeSquad = nomeSquad.concat(quantidadeSoldados);
            nomeSquad = nomeSquad.concat("  -> id: ");
            idText = Integer.toString(listaSquads.get(i).getId());
            nomeSquad = nomeSquad.concat(idText);

            listaNomes.add(nomeSquad);
        }


        return listaNomes;
    }


}
