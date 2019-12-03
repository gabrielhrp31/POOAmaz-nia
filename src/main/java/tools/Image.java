package tools;

import DAO.ImageDAO;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Ferramenta para gerar imagem ppm converter entre outra operações necessárias no trabalho
 */
public class Image {
    private int[][] imageMatrix;
    private String image;
    private int width;
    private int height;
    private int[][] colors;
    private int scale;
    private static final String IMAGE_PATH = "data"+File.separator+"pictures"+File.separator;
    private String dataFormatada;

    /**
     * Construtor da classe
     * @param length tamanho da area
     * @param scale qual escala a imagem será gerada
     */
    public Image(int length,  int scale){
        int[][] intensities = this.generateRandomImage(length);
        this.imageMatrix = new int[intensities.length][intensities[0].length*3];
        this.width= intensities[0].length;
        this.height= intensities.length;
        this.scale = scale;

        int i=0,j=0,rgb=0;

        colors = new int[4][3];

        colors[0][0]=0;
        colors[0][1]=255;
        colors[0][2]=0;
        colors[1][0]=255;
        colors[1][1]=255;
        colors[1][2]=0;
        colors[2][0]=255;
        colors[2][1]=155;
        colors[2][2]=0;
        colors[3][0]=255;
        colors[3][1]=0;
        colors[3][2]=0;

        for(int[] intensitiesRow: intensities){
            j=0;
            for(int intensity: intensitiesRow){
                imageMatrix[i][j]=colors[intensity][rgb];
                rgb++;
                j++;
                imageMatrix[i][j]=colors[intensity][rgb];
                rgb++;
                j++;
                imageMatrix[i][j]=colors[intensity][rgb];
                j++;
                rgb=0;
            }
            i++;
        }
    }

    /**
     * @return calcula media de incendio da região
     */
    public double getIntensitiesAverage(){
        int[][] matrix = this.getItensities();
        double result = 0 ;
        for (int[] row: matrix){
            for(int el: row){
                result+=el;
            }
        }
        result /= (matrix.length);
        return result;
    }

    /**
     * @return busca as intensidades na string carrgada no arquivo de imagem
     */
    private int[][] getItensities(){
        String[] lines = this.image.split("\n");
        int[][] matriz =  new int[this.scale][this.scale];

        String[] nums = null;
        int[] rgb= new int[3];
        int col=0,row=0;

        for(int i=5;i<lines.length; i+=this.scale){
             nums = lines[i].split("\\s");
             col=0;
             for(int j=0;j<nums.length;){
                 rgb[0] = Integer.parseInt(nums[j]);
                 j++;
                 rgb[1] = Integer.parseInt(nums[j]);
                 j++;
                 rgb[2] = Integer.parseInt(nums[j]);
                 j+=((this.scale-1)*3)+1;
                 boolean hasColor=false;
                 for(int k=0;k<3;k++){
                     if(this.compareColors(rgb,colors[k])){
                         matriz[col][row] = k++;
                         hasColor = true;
                     }
                 }
                 if(!hasColor){
                     matriz[col][row]=0;
                 }
                 col++;
             }
             row++;
        }

        return matriz;
    }

    /**
     * Compara dois vetores de rgb
     * @param rgb cor 1
     * @param color cor dois
     * @return se as cores são iguais
     */
    private boolean compareColors(int[] rgb, int[] color) {

        for(int i=0;i<3;i++){
            if(rgb[i]!=color[i]){
                return false;
            }
        }

        return true;
    }

    /**
     * Cria uma imagem com os dados de intensidade recebidos no instaciamento da classe
     */
    public void createImage(){
        StringBuilder image =
                new StringBuilder();
        image.append("P3\n");
        image.append("# Imagem Gerada por satélite na escala ");
        image.append(this.scale);
        image.append("X");
        image.append(this.scale);
        image.append("\n");
        image.append(this.width*this.scale);
        image.append(" ");
        image.append(this.height*this.scale);
        image.append("\n255\n");

        StringBuilder line;

        StringBuilder rgb =
                new StringBuilder();


        for(int i =0;i< this.imageMatrix.length;i++){
            line = new StringBuilder();
            for(int j=0;j<this.imageMatrix[i].length;j++){
                rgb.append(this.imageMatrix[i][j]);
                if(j!=imageMatrix[i].length){
                    rgb.append(" ");
                }
                if((j+1)%3.0==0 || j+1==this.imageMatrix[0].length){
                    for(int scale=0;scale< this.scale;scale++){
                        line.append(rgb);
                    }
                    rgb = new StringBuilder();
                }
            }
            line.append("\n");
            for(int scale=0;scale< this.scale;scale++){
                image.append(line);
            }
        }

        this.image = String.valueOf(image);
    }

    /**
     * Gerar numero aleatorio entre um valor mínimo e um máximo
     * @param min numero minimo
     * @param max  numero maximo
     * @return retorna um numero aleatoria
     */
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * gera as intensidades de incendio da tegião
     * @param n tamanho da imagem
     * @return imagem aleatoria
     */
    public int[][] generateRandomImage(int n){
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                matrix[i][j]=getRandomNumberInRange(0,3);
//                System.out.print(matrix[i][j]+" ");
            }
//            System.out.println();
        }
        return matrix;
    }

    /**
     * gera a imagem utilizando de algumas funções anteriores e faz upload do arquivo para o drive
     * @param id id da região
     */
    public void generateImageFile(int id){
        createImage();
        int time = (int) (new Date().getTime()/1000);
        String fileName = Integer.toString(time)+".ppm";
        String completeImagePath = System.getProperty("user.dir")+File.separator+IMAGE_PATH;

        File dirDataFile = new File(completeImagePath);

        if (!dirDataFile.exists()) {
            dirDataFile.mkdirs();
        }
        //SE A PASTA NÂO EXISTIR ENTÃO CRIA
        try{
            FileOutputStream fos = new FileOutputStream(IMAGE_PATH+fileName);
            fos.write(new String(this.image).getBytes());
            fos.close();

            models.Image image = new models.Image();
            image.setFileName(fileName);
            image.setGeneratedTime(time);
            image.setRegionId(id);
            image.setScale(this.scale);
            ImageDAO.addImage(image);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void lerImagem(String nome, int scale, int regionId, int generateTime) throws FileNotFoundException {
        String arquivo = "";
        try {
            FileReader arq = new FileReader(System.getProperty("user.dir") + File.separator + "data" + File.separator + "pictures" + File.separator + nome);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            arquivo = linha+"\n";
            while (linha != null) {
                arquivo += linha+"\n"; // lê da segunda até a última linha
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        this.scale = scale;
        this.image = arquivo;
    }

    public String getDataFormatada() {
        return dataFormatada;
    }
}
