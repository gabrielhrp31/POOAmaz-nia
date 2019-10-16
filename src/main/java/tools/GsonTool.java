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
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class GsonTool {

    private static final String JSON_PATH = "data"+File.separator+"json"+File.separator;

    /*
    @param int type (1 IMAGE, 2 REGION);
     */
    public static String read(String name, int type) throws IOException, GeneralSecurityException {
    GoogleDrive gd=new GoogleDrive();
        File dirDataFile = new File(System.getProperty("user.dir") + File.separator + JSON_PATH);

        if(!dirDataFile.exists()) {

            switch (type) {
                case 1:
                    try {
                       gd.downloadFile("application/json", "pictures.json", JSON_PATH);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case 2:
                    try {
                        gd.downloadFile("application/json", "regions.json", JSON_PATH);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

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
            return reader.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean write(String name, ArrayList<Object> list){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        File dirDataFile = new File(System.getProperty("user.dir") + File.separator + JSON_PATH);

        if(!dirDataFile.exists()){
            dirDataFile.mkdirs();
        }

        try {
            Writer writer = new FileWriter(JSON_PATH+name);
            gson.toJson(list, writer);
            writer.flush(); //flush data to file   <---
            writer.close(); //close write          <
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
