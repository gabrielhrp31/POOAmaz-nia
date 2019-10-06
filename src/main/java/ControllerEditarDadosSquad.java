import DAO.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerEditarDadosSquad extends ControllerUtil {


    //CARREGAR OS ARQUIVOS DO SQUAD NO CHECKBOX P DEPOIS ALTERAR:

    //ATRIBUTOS


    @FXML
    private ComboBox<java.lang.String> comboBoxSquadEditar;

    @FXML
    private TextField txtNomeEditarSquad;

    @FXML
    private TextField txtNumeroEditarSquad;

    @FXML
    private ComboBox<java.lang.String> checkBregiaoEditarSquad;


    //METODOS
    @FXML
    public void carregarCheckBoxSquad() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquads = new ArrayList<Squad>();
        listaSquads = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        ObservableList<java.lang.String> olcomboBoxSquadResponsable = null;

        List<String> listaNomes = new ArrayList<String>();

        for (int i = 0; i < listaSquads.size(); i++) {
            listaNomes.add(listaSquads.get(i).getName());
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);
        //no listaSquads tem todos os dados, aí da o .get(posicao) p pegar o campo
        //aí tenho que carregar os dados quando o cara clicar em atualizar p ele alterar
        comboBoxSquadEditar.setItems(olcomboBoxSquadResponsable);
    }

    @FXML
    public void atualizarCheckBoxSquad() throws FileNotFoundException {
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquads = new ArrayList<Squad>();
        listaSquads = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        ObservableList<java.lang.String> olcomboBoxSquadResponsable = null;

        List<java.lang.String> listaNomes = new ArrayList<java.lang.String>();

        for (int i = 0; i < listaSquads.size(); i++) {
            listaNomes.add(listaSquads.get(i).getName());
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);
        //no listaSquads tem todos os dados, aí da o .get(posicao) p pegar o campo
        //aí tenho que carregar os dados quando o cara clicar em atualizar p ele alterar
        comboBoxSquadEditar.setItems(olcomboBoxSquadResponsable);

        for (int i = 0; i < listaSquads.size(); i++) {
            if (comboBoxSquadEditar.getValue().equals(listaSquads.get(i).getName())) {//for igual o do checkbox
                  java.lang.String quantify=Integer.toString(listaSquads.get(i).getQuantityOfSoldiers());
                  txtNomeEditarSquad.setText(listaSquads.get(i).getName());
                  txtNumeroEditarSquad.setText(quantify);
                  //ARRUMAR A FUNÇÃO DE CARREGAR P ELE FICAR NA CONTROLLER UTIL E SER GENERICA
            }
        }


    }


    @FXML
    public void botaoSalvarSquad() throws IOException {
    //carregar os dados em memória, dps pegar os valores do txtField e substiruir e dps salvar

    //carregando os dados em memória (TORNAR ESSE MÉTODO GENERICO)
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquads = new ArrayList<Squad>();
        listaSquads = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        ObservableList<java.lang.String> olcomboBoxSquadResponsable = null;

        List<java.lang.String> listaNomes = new ArrayList<java.lang.String>();

        for (int i = 0; i < listaSquads.size(); i++) {
            listaNomes.add(listaSquads.get(i).getName());
        }

        olcomboBoxSquadResponsable = FXCollections.observableList(listaNomes);

        //ALTERANDO
        //BUSCA PELO NOME

      //  java.lang.String removerSquad=comboBoxSquadEditar.getValue();

        for (int i = 0; i < listaSquads.size(); i++) {
            if (comboBoxSquadEditar.getValue().equals(listaSquads.get(i).getName())) {//forem iguais
              listaSquads.get(i).setName(txtNomeEditarSquad.getText());
              int quantity=Integer.parseInt(txtNumeroEditarSquad.getText());
              listaSquads.get(i).setQuantityOfSoldiers(quantity);
              listaSquads.get(i).setRegionResponsable(checkBregiaoEditarSquad.getValue());

                //ARRUMAR A FUNÇÃO DE CARREGAR P ELE FICAR NA CONTROLLER UTIL E SER GENERICA


                //salvar


                //TERMINAR ESSE EDITAR DADOS, TA PRATICAMENTE TUDO PRONTO
                  //      SÓ TENHO Q FAZER COM QUE ELE DA O REMOVE NO NOME DA LISTA Q EU PASSEI
                    //    E ADICIONA O NOVO
                String nomeComboBox=comboBoxSquadEditar.getValue(); //pega o nome selecionado na comboBox

                salvarJSON(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"squadJ.json", listaSquads.get(i), null, 0,1,nomeComboBox,null);
                //ver se vai remover direito e adicionar certo


            }

        }





    }



}
