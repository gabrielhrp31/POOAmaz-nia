import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import tools.GoogleDrive;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ControllerManipularMain extends ControllerUtil {

    //FUNÇÃO PARA ENVIAR JSON, FUNÇÃO PARA EDITAR DADOS


    @FXML
    private BorderPane borderPanel;


    //METODOS

    @FXML
    private void telaEditarDadosMain() {
        loadUI("editarDadosMain");
    }


    @FXML
    private void enviarDados() throws IOException, GeneralSecurityException {
        GoogleDrive gd = new GoogleDrive();
        int segure = 0;

        //deleta os velhos primeiros (só deleta caso o arquivo existir no drive <- ARRUMAR ISSO)
        try {
            gd.getFile("application/json", "region.json");
        } catch (IOException e) {
            segure = 1;
        }
        if (segure == 0) {
            gd.deleteFile("region");
            segure=0;
        }

        try {
            gd.getFile("application/json", "squad.json");
        } catch (IOException e) {
            segure = 2;
        }
        if (segure == 0) {
            gd.deleteFile("squad");
        }

        //substitui os novos
        //primeiro envia o squad dps o region
        gd.uploadFile("region.json", System.getProperty("user.dir") + File.separator + "data" + File.separator, "application/json");
        gd.uploadFile("squad.json", System.getProperty("user.dir") + File.separator + "data" + File.separator, "application/json");

    }


}
