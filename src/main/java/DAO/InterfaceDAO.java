package DAO;

import Exceptions.conexaoError;
import models.Region;
import tools.Firebase;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface InterfaceDAO {

    public List<String> carregarComboBox(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException;

    public List<?> carregarComboBoxEditarDados(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException, conexaoError;

    public List<?> carregarTabbleView(Firebase firebase) throws FileNotFoundException, ExecutionException, InterruptedException, conexaoError;





}
