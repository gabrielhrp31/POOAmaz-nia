package tools;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import Exceptions.*;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Firebase {
    private GoogleCredentials credentials;
    private FirebaseOptions options;
    public Firestore db;
    public Storage storage ;
    private static final String IMAGE_PATH = "data"+File.separator+"pictures"+File.separator;

    /**
     * Construtor para iniciar a conexão com o FireBase
     *
     * @throws IOException
     */
    public Firebase() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("credentials.json");
        this.credentials = GoogleCredentials.fromStream(serviceAccount);
        this.options = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://poo-queimadas.firebaseio.com")
                .setStorageBucket("poo-queimadas.appspot.com")
                .setCredentials(credentials)
                .build();

        FirebaseApp.initializeApp(options);


        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build();

        storage = storageOptions.getService();

        this.db = FirestoreClient.getFirestore();
    }


    /**
     * Método para salvar os arquivos no FireBase
     *
     * @param qualSalvar         define se vai salvar um squad ou uma regiao
     * @param id                 o id do arquivo
     * @param name               o nome do arquivo
     * @param protectedArea      se a area é protegida
     * @param squadResponsable   o esquadrão responsável
     * @param quantityOfSoldiers a quantidade de soldados
     * @param regionResponsable  o responsável pela região
     */
    public void write(int qualSalvar, int id, String name, Boolean protectedArea, int squadResponsable, String protecaoAmbiente, String regioesUrbana, int quantityOfSoldiers, String regionResponsable) {

        DocumentReference docRef = null;
        Map<String, Object> data = new HashMap<>();
        String idString = " ";
        idString = Integer.toString(id);

        if (qualSalvar == 0) {//REGION
            docRef = db.collection("regions").document(idString);
            //Adiciona as informações ao documento com o id "nomeID" usando a hashMap
            //nome campo | valor
            data.put("name", name);
            data.put("protectedArea", protectedArea);
            data.put("squadResponsable", squadResponsable);
            data.put("environmentalProtection", protecaoAmbiente);
            data.put("urbanRegion", regioesUrbana);
        }

        if (qualSalvar == 1) {//SQUAD
            docRef = db.collection("squads").document(idString);
            data.put("name", name);
            data.put("regionResponsable", regionResponsable);
            data.put("quantityOfSoldiers", quantityOfSoldiers);
        }





        // grava dados de forma assíncrona
        try {
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Hora da atualizacao: " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * Função para baixar os arquivos do FireBase
     *
     * @param qualLer se vai ler dos Squads ou dos Regions
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<QueryDocumentSnapshot> read(int qualLer) throws ExecutionException, InterruptedException,conexaoError{
        ApiFuture<QuerySnapshot> query = null;
      //Aqui nao deu try catch, pois as funções que usam este método já tratam a exceção (conexaoError)
        if (qualLer == 0) {
            query = db.collection("regions").get();
        }
        if (qualLer == 1) {
            query = db.collection("squads").get();
        }

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();//documents esta com os dados

        return documents;
    }

    /**
     * Método para remover algo do Firebase
     *
     * @param tabela De qual Tabela vai deletar
     * @param coluna A coluna que será deletada
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void remove(String tabela, String coluna) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = db.collection(tabela).document(coluna).delete();
        // ...
        System.out.println("Hora de Update : " + writeResult.get().getUpdateTime());
    }

    public void downloadFile(String fileName){
        Blob blobFile = storage.get(options.getStorageBucket(),fileName);
        Path path = Paths.get(System.getProperty("user.dir") + File.separator + IMAGE_PATH );
        File file = new File(String.valueOf(path)+ File.separator+fileName);
        if(!file.exists()){
            File dirDataFile = new File(String.valueOf(path));
            if (!dirDataFile.exists()) {
                dirDataFile.mkdirs();
            }
            try {
                OutputStream output = new FileOutputStream(String.valueOf(path)+ File.separator+fileName);
                blobFile.downloadTo(output);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        else{
            return;
        }

    }


}