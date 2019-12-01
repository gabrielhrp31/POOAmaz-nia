package DAO;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import models.Image;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static analysis.Main.firebase;

/**
 * Apenas o objeto de acesso aos dados e seus getters e setters vulgo DAO
 */
public class ImageDAO {
    private static ArrayList<Image> images;

    /**
     * @return retorna um array de imagens
     */
    public static ArrayList<Image> getImages() {
        return images;
    }

    /**
     * instancia um novo arraylist de imagens
     */
    public ImageDAO() {
        images = new ArrayList<>();
        readImages();
    }

    private void readImages(){
        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future =
                firebase.db.collection("images").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                images.add(document.toObject(Image.class));
            }
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (ExecutionException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }


    public static void writeImages(){
        //esse é apenas um teste
        CollectionReference collectionRef = firebase.db.collection("images");
        // Add document data  with id "alovelace" using a hashmap

        Map<String, Object> data = new HashMap<>();
        for (Image image:images) {
            data.put("fileName", image.getFileName());
            data.put("scale", image.getScale());
            data.put("regionId", image.getRegionId());
            data.put("generatedTime", image.getGeneratedTime());
            data.put("read", image.isRead());
        }

        ApiFuture<DocumentReference> result = result = collectionRef.add(data);
        try {
            result.get();
            JOptionPane.showMessageDialog(null, "Dados Enviados");
        } catch (InterruptedException | ExecutionException e) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar dados "+e.getMessage());
        }

    }

    /**
     * @param image adiciona apenas uma imagem a lista de imagens
     */
    public static void addImage(Image image) {
        ImageDAO.images.add(image);
        writeImages();
    }

    /**
     * @param list coloca as imagens no array de imagem
     */
    public static void setImages(ArrayList<Image> list) {
        ImageDAO.images = list;
    }
}