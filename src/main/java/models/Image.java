package models;

public class Image{
    private String fileId;
    private String fileName;
    private int generatedTime;
    private int regionId;
    private int scale;


    private boolean read;

    /**
     * Construtor base
     */
    public Image() {
        this.read = false;
    }

    /**
     * Construtor com os parametros do File
     * @param fileId o id do arquivo
     * @param fileName nome do arquivo
     * @param generatedTime data de quando foi gerado
     */
    public Image(String fileId, String fileName, int generatedTime) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.generatedTime = generatedTime;
        this.read = false;
    }

    /**
     * Getter para o fileID
     *
     * @return
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Setter para o fileId
     *
     * @param fileId recebe o id do arquivo para setar ele
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


    /**
     * Getter para o FileName
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * Setter para o FileName
     *
     * @param fileName recebe o nome do arquivo para setar ele
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Getter para o generatedTime
     *
     * @return
     */
    public int getGeneratedTime() {
        return generatedTime;
    }


    /**
     * Setter para o generatedTime
     *
     * @param generatedTime recebe a data de quando foi gerado
     */
    public void setGeneratedTime(int generatedTime) {
        this.generatedTime = generatedTime;
    }


    /**
     * Getter para o read
     *
     * @return
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Getter para o regionId
     *
     * @return
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * Setter para o regionId
     *
     * @param regionId o id da regiao
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    /**
     * Getter para o scale
     *
     * @return
     */
    public int getScale() {
        return scale;
    }


    /**
     * Setter para o scale
     *
     * @param scale a escala da imagem
     */
    public void setScale(int scale) {
        this.scale = scale;
    }


    /**
     * Setter para o read
     *
     * @param read
     */
    public void setRead(boolean read) {
        this.read = read;
    }



}
