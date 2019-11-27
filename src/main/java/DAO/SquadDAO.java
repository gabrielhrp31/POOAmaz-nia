package DAO;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import models.Squad;
import tools.Firebase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SquadDAO implements InterfaceDAO{


    /**
     * Carrega os dados para a ComboBoxRegion
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws FileNotFoundException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> carregarComboBox(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

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


    /**
     * Carrega os dados para a ComboBoxSquad
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
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

    /**
     * Carrega os dados para a ComboBoxSquad do EditarDados da Squad
     *
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> carregarComboBoxEditarDados(Firebase firebase) throws ExecutionException, InterruptedException {

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


    /**
     * Carrega os dados dos Squads para a TabbleView para exibir
     *
     * @param firebase Recebe um ojbeto do Firebase para poder conectar com o Firebase para baixar os dados
     * @return
     * @throws FileNotFoundException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<Squad> carregarTabbleView(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException {

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
