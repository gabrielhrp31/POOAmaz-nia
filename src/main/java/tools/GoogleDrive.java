package tools;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleDrive {
    private static final String APPLICATION_NAME = "POO Amazônia Satélite";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static Drive driveService;

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleDrive.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Sobe um novo arquivo para a pasta do Drive
     * @param name
     * @param dir
     * @param type
     * @return
     * @throws IOException
     */
    public String uploadFile(String name, String dir, String type) throws IOException {

        System.out.println(
                name
        );

        File folder = getFile("application/vnd.google-apps.folder", "analise");
        File fileMetadata = new File();
        fileMetadata.setName(name);
        fileMetadata.setParents(Collections.singletonList(folder.getId()));
        java.io.File filePath = new java.io.File(dir + name);
        FileContent mediaContent = new FileContent(type, filePath);
        File file = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        return file.getId();
    }

    /**
     * Baixa um arquivo da pasta do Drive
     * @param mimeType
     * @param name
     * @param path
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public void downloadFile(String mimeType, String name, String path) throws IOException, GeneralSecurityException {
        File file = null;
        GoogleDrive googleDrive=new GoogleDrive();
        try {
            file = googleDrive.getFile(mimeType, name); //COLOCA O ID DO ARQUIVO DO DRIVE (DPS TEM Q VER COMO PEGAR AUTOMATICO)
        } catch (Exception e) {
            return;
        }

        java.io.File theDir = new java.io.File(path);

        // se o diretorio não existir cria ele
        if (!theDir.exists()) {
            boolean result = false;

            try {
                theDir.mkdirs();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
        }


        OutputStream outputStream = new FileOutputStream(path + name);

        driveService.files().get(file.getId())
                .executeMediaAndDownloadTo(outputStream);
    }


    /**
     * Baixa o arquivo do Drive pelo ID
     * @param mimeType
     * @param fileId
     * @param path
     * @param nome
     * @throws FileNotFoundException
     */
    public void downloadFileById(String mimeType, String fileId, String path, String nome) throws FileNotFoundException {
        java.io.File theDir = new java.io.File(path);
        // se o diretorio não existir cria ele
        if (!theDir.exists()) {
            boolean result = false;
            try {
                theDir.mkdirs();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
        }
        OutputStream outputStream = new FileOutputStream(path + nome);
        try {
            driveService.files().get(fileId)
                    .executeMediaAndDownloadTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Localiza um arquivo no Drive
     * @param mimeType
     * @param name
     * @return
     * @throws IOException
     */
    public File getFile(String mimeType, String name) throws IOException {
        List<com.google.api.services.drive.model.File> files;
        String pageToken = null;
        do {
            FileList result;
            if (mimeType.isEmpty()) {
                result = driveService.files().list()
                        .setQ("name='" + name + "' ")
                        .setSpaces("drive")
                        .setFields("nextPageToken, files(id, name)")
                        .setPageToken(pageToken)
                        .execute();
            } else {
                result = driveService.files().list()
                        .setQ("mimeType='" + mimeType + "' and name='" + name + "' ")
                        .setSpaces("drive")
                        .setFields("nextPageToken, files(id, name)")
                        .setPageToken(pageToken)
                        .execute();
            }
            files = result.getFiles();

            pageToken = result.getNextPageToken();
        } while (pageToken != null);

        return files.get(0);
    }


    /**
     * Apaga um arquivo da pasta do Drive
     * @param name
     * @throws IOException
     */
    public void deleteFile(String name) throws IOException {
        String fileId = getFile("application/json", name + ".json").getId();
        if (fileId != null) {
            driveService.files().delete(fileId).execute();
        }
    }


    public GoogleDrive() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        this.driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}