import DAO.SquadDAO;
import Models.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
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
        comboBoxSquadEditar.setItems(olcomboBoxSquadResponsable);

        for (int i = 0; i < listaSquads.size(); i++) {
            if (comboBoxSquadEditar.getValue().equals(listaSquads.get(i).getName())) {//for igual o do checkbox
                java.lang.String quantify = Integer.toString(listaSquads.get(i).getQuantityOfSoldiers());
                txtNomeEditarSquad.setText(listaSquads.get(i).getName());
                txtNumeroEditarSquad.setText(quantify);
            }
        }


    }

    @FXML
    public void removerSquad() throws IOException {
        SquadDAO squadDAO = new SquadDAO();
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json");
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Squad>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Squad> listaSquads = new ArrayList<>();
        listaSquads = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo

        List<java.lang.String> listaNomes = new ArrayList<java.lang.String>();

        for (int i = 0; i < listaSquads.size(); i++) {
            listaNomes.add(listaSquads.get(i).getName());
        }

        for (int i = 0; i < listaSquads.size(); i++) {

            if (comboBoxSquadEditar.getValue().equals(listaSquads.get(i).getName())) {//forem iguais
                //   String nomeComboBox = comboBoxSquadEditar.getValue(); //pega o nome selecionado na comboBox
                int id = listaSquads.get(i).getId();

                Object[] options = {"Sim", "Nao"};
                int w = JOptionPane.showOptionDialog(null, "Tem certeza que deseja remover este esquadrao?", "ATENCAO",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        options, options[0]);
                if (w == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Ent");
                    salvarJSON(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json", null, null, 2, 1, id, 0);
                    JOptionPane.showMessageDialog(null, "Acao Concluidada", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                }


            }

        }


    }


    @FXML
    public void botaoSalvarSquad() throws IOException {
        SquadDAO squadDAO = new SquadDAO();
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

        for (int i = 0; i < listaSquads.size(); i++) {
            if (comboBoxSquadEditar.getValue().equals(listaSquads.get(i).getName())) {//forem iguais

                if (!isAlpha(txtNomeEditarSquad.getText())) {
                    JOptionPane.showMessageDialog(null, "Insira apenas Letras no nome");
                    return;
                }
                if (!soContemNumeros(txtNumeroEditarSquad.getText())) {
                    JOptionPane.showMessageDialog(null, "Insira apenas Numeros na quantidade");
                    return;
                }
                listaSquads.get(i).setName(txtNomeEditarSquad.getText());
                int quantity = Integer.parseInt(txtNumeroEditarSquad.getText());
                listaSquads.get(i).setQuantityOfSoldiers(quantity);
                listaSquads.get(i).setRegionResponsable(checkBregiaoEditarSquad.getValue());

                String nomeComboBox = comboBoxSquadEditar.getValue(); //pega o nome selecionado na comboBox
                int id = listaSquads.get(i).getId();
                salvarJSON(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squadJ.json", listaSquads.get(i), null, 0, 1, id, 0);
                JOptionPane.showMessageDialog(null, "Acao Concluidada", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            }

        }


    }


}
