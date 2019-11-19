package tools;

import DAO.ImageDAO;
import DAO.RegionDAO;
import models.Image;
import models.Region;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Classe que realiza operações com json utilizando a biblioteca GSON do google
 */
public class GsonTool {

    private static final String JSON_PATH = "data"+File.separator+"json"+File.separator;

    /**
     * carrega os dados json do drive e coloca em uma lista de imagens ou regiões
     * @param name nome do arquivo
     * @param type type (1 IMAGE, 2 REGION);
     */
    public static void read(String name, int type){

        File dirDataFile = new File(System.getProperty("user.dir") + File.separator + JSON_PATH);
        dirDataFile.mkdirs();

        Gson gson =  new GsonBuilder().setPrettyPrinting().create();
        try {
            Reader reader= new FileReader(JSON_PATH+name);

            Type listType;
            switch (type){
                case 1:
                    listType = new TypeToken<ArrayList<Image>>(){}.getType();
                    ArrayList<Image> listImages = gson.fromJson(reader, listType);
                    ImageDAO.setImages(listImages);
                    break;
                case 2:
                    listType = new TypeToken<ArrayList<Region>>(){}.getType();
                    ArrayList<Region> listRegions = gson.fromJson(reader, listType);
                    RegionDAO.setRegions(listRegions);
                    break;
            }
        } catch (IOException e) {
            return;
        }
    }

    /**
     * @param name nome do arquivo
     * @param list lista a ser salva
     */
    public static void write(String name, ArrayList<Image> list){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File dirDataFile = new File(System.getProperty("user.dir") + File.separator + JSON_PATH);

        if(!dirDataFile.exists()){
            dirDataFile.mkdirs();
        }

        try {
            Writer writer = new FileWriter(JSON_PATH+name);
            gson.toJson(list, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
