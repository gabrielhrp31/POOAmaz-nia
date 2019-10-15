import Models.Image;
import Models.Region;
import Models.Squad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import tools.GoogleDrive;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ControllerAnalisesMain extends ControllerUtil {

    @FXML
    private void telaCadastroMain() {
        loadUI("cadastroMain");
    }

    @FXML
    private void telaManipularMain() {
        loadUI("manipularMain");
    }

    @FXML
    private void gerarRelatorio() throws IOException, GeneralSecurityException {
        GoogleDrive gd = new GoogleDrive();
        Image image = new Image();
        File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator + "pictures.json");
        if (dir.exists()) {
            dir.delete();
            gd.downloadFile("application/json", "pictures.json", System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator);
        } else {
            gd.downloadFile("application/json", "pictures.json", System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator);
        }
        Reader reader = new FileReader(dir);//LE OS DADOS DO ARQUIVO
        Type listType = new TypeToken<ArrayList<Image>>() {
        }.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Image> listaImage = new ArrayList<Image>();
        listaImage = gson.fromJson(reader, listType);//carrega para a lista os dados do arquivo
        for (int i = 0; i <= listaImage.size(); i++) {
            gd.getFile("image/jpeg", listaImage.get(i).getFileName());
            gd.downloadFile("image/jpeg", listaImage.get(i).getFileName(), System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator);
        }

    }
}
