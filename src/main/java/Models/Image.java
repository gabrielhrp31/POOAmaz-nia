package Models;

public class Image extends Object{
    private String fileId;
    private String fileName;
    private int generatedTime;
    private boolean read;

    public Image() {
        this.read = false;
    }

    public Image(String fileId, String fileName, int generatedTime) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.generatedTime = generatedTime;
        this.read = false;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(int generatedTime) {
        this.generatedTime = generatedTime;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
