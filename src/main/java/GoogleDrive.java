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
    private Drive driveService;

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
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

    public void uploadFile() throws IOException {
        //buscando a pasta satelite no drive
        File folder = this.getFile("application/vnd.google-apps.folder", "satellite");
        File fileMetadata = new File();
        fileMetadata.setName("photo.ppm");
        fileMetadata.setParents(Collections.singletonList(folder.getId()));
        java.io.File filePath = new java.io.File("photos/photo.ppm");
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        File file = this.driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());
    }

    public void uploadInformationSquad() throws IOException {
        //encontra a pasta, aqui vai fazer o upload do txt do squad, DEPOIS VEJO SE VOU FAZER UMA FUNÇÃO SÓ
        //QUE UPA TUDO
        File folderSquad = this.getFile("application/vnd.google-apps.folder", "satellite");
        File fileMetadataSquad = new File();
        fileMetadataSquad.setName("registrationSquad.txt");
        fileMetadataSquad.setParents(Collections.singletonList(folderSquad.getId()));
        java.io.File filePath = new java.io.File("information/registrationSquad.txt");
        FileContent mediaContent = new FileContent("text/plain", filePath); //tipo de dado
        File file = this.driveService.files().create(fileMetadataSquad, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());

    }


    public void uploadInformationRegion() throws IOException {
        //encontra a pasta, aqui vai fazer o upload do txt do squad, DEPOIS VEJO SE VOU FAZER UMA FUNÇÃO SÓ
        //QUE UPA TUDO
        File folderRegion = this.getFile("application/vnd.google-apps.folder", "satellite");
        File fileMetadataRegion = new File();
        fileMetadataRegion.setName("registrationRegion.txt");
        fileMetadataRegion.setParents(Collections.singletonList(folderRegion.getId()));
        java.io.File filePath = new java.io.File("information/registrationRegion.txt");
        FileContent mediaContent = new FileContent("text/plain", filePath); //tipo de dado
        File file = this.driveService.files().create(fileMetadataRegion, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());

    }


    public void downloadFile() throws IOException {
        String fileId = getFile("", "photo.ppm").getId(); //COLOCA O ID DO ARQUIVO DO DRIVE (DPS TEM Q VER COMO PEGAR AUTOMATICO)

        java.io.File theDir = new java.io.File("photos" + java.io.File.separator + "downloads");

// se o diretorio não existir cria ele
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("new Directory created");
            }
        }


        OutputStream outputStream = new FileOutputStream("photos/downloads/photo.ppm");


        driveService.files().get(fileId)
                .executeMediaAndDownloadTo(outputStream);
    }


    /*

        @return File
     */

    private File getFile(String mimeType, String name) throws IOException {
        String pageToken = null;

        List<File> files;

        do {
            FileList result;
            if (mimeType.isEmpty()) {
                result = this.driveService.files().list()
                        .setQ("name='" + name + "' ")
                        .setSpaces("drive")
                        .setFields("nextPageToken, files(id, name)")
                        .setPageToken(pageToken)
                        .execute();
            } else {
                result = this.driveService.files().list()
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


    GoogleDrive() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        this.driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}