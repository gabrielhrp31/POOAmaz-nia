import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args)  throws IOException, GeneralSecurityException {
        GoogleDrive googleDrive = new GoogleDrive();

        googleDrive.uploadFile();

    }
}
