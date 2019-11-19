import javafx.fxml.FXML;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ControllerManipularMain extends ControllerUtil {


    /**
     * Chama a tela para EditarDados
     */
    @FXML
    private void telaEditarDadosMain() {
        loadUI("editarDadosMain");
    }


    /**
     * Chama a tela para listar os dados
     */
    @FXML
    private void telaListarDados() {
        loadUI("listarDados");
    }

}
