import javafx.fxml.FXML;
import tools.GoogleDrive;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ControllerManipularMain extends ControllerUtil{

  //FUNÇÃO PARA ENVIAR JSON, FUNÇÃO PARA EDITAR DADOS



    @FXML
    private void enviarDados() throws IOException, GeneralSecurityException {
         GoogleDrive gd= new GoogleDrive();
        //primeiro envia o squad dps o region
         gd.uploadFile(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"squadJ","squad");
        gd.uploadFile(System.getProperty("user.dir")+ File.separator+"data"+File.separator+"regionJ","region");
    }


}
