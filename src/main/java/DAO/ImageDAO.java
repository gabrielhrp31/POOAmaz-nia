package DAO;

import models.Image;
import tools.GsonTool;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class ImageDAO {
    private static ArrayList<Object> images;

    public ImageDAO() {
        images = new ArrayList<>();
    }

    /**
     * Adiciona uma imagem ao pictures.json
     * @param image
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static void addImage(Image image) throws IOException, GeneralSecurityException {
        GsonTool.read("pictures.json",1);
        ImageDAO.images.add(image);
        GsonTool.write("pictures.json",images);
    }

    public static void setImages(ArrayList<Image> list) {
        ImageDAO.images = images;
    }
}
