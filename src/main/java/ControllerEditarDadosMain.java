import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class ControllerEditarDadosMain extends ControllerUtil {


    //Ó , VAI CARREGAR OS DADOS DO DOCUMENTO PARA A MEMÓRIA, E MOSTRAR NA CHECKBOX
    //AÍ ELE ESCOLHE QUAL QUER EDITAR NA CHECKBOX, E CLICA EM ATUALIZAR, AÍ APARECE OS CAMPOS
    //ELE ALTERA O CAMPO E CLICA EM SALVAR. AÍ ALTERA NO ARQUIVO



    @FXML
    private BorderPane borderPanel;




    @FXML
    private void telaEditarDadosSquad() {
        creatUI("telaEditarDadosSquad");
    }

    @FXML
    private void telaEditarDadosRegion() {
        creatUI("telaEditarDadosRegion");
    }


}
