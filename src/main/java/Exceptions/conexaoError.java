package Exceptions;

public class conexaoError extends Exception {

    public conexaoError(){
        super("Nao foi possivel conectar ao Gson");
    }

}
