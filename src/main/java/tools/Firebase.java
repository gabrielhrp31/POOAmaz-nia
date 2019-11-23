package tools;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Firebase {
    private GoogleCredentials credentials;
    private FirebaseOptions options;
    private Firestore db;

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
                .setCredentials(credentials)
                .build();

        FirebaseApp.initializeApp(options);
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
            data.put("protecaoAmbiente", protecaoAmbiente);
            data.put("regioesUrbana", regioesUrbana);
        }

        if (qualSalvar == 1) {//SQUAD
            docRef = db.collection("squads").document(idString);
            data.put("name", name);
            data.put("regionResponsable", regionResponsable);
            data.put("quantityOfSoldiers", quantityOfSoldiers);
        }


        // grava dados de forma assíncrona
        ApiFuture<WriteResult> result = docRef.set(data);

        // ...
        // result.get() blocks on response

        try {
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
    public List<QueryDocumentSnapshot> read(int qualLer) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = null;
        if (qualLer == 0) {
            query = db.collection("regions").get();
        }
        if (qualLer == 1) {
            query = db.collection("squads").get();
        }

        // AQUI É O DIRETORIO DO BANCO DE DADOS
        // ...
        // query.get() blocks on response
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
        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = db.collection(tabela).document(coluna).delete();
// ...
        System.out.println("Update time : " + writeResult.get().getUpdateTime());
    }


}