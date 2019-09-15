import java.io.IOException;
import java.security.GeneralSecurityException;

import Interface.*;


public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException {

        Swing gui = new Swing();
        gui.menuOptions();//chamar a tela



        /*GoogleDrive google= new GoogleDrive();
        google.uploadInformationSquad();
        google.uploadInformationRegion();*/
        //OBS: ESTOU PENSANDO SE VALE A PENA TORNAR OS UPDATES EM UM SÓ GENERICO
        //OBS2: O MESMO PARA AS FUNÇÕES QUE ESTÃO GERANDO OS TXTS


    }
}
