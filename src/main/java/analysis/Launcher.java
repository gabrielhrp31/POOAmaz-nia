package analysis;

import java.io.IOException;

public class Launcher {

    /**
     * Este Método é usado devido a conexão que há com o Firebase
     * ele chama a classe o método main da classe Main para executar o sistema
     *
     * @param args padrão da sintaxe
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Main.main(args);
    }

}
