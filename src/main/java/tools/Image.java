package tools;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Image {
    private int[][] imageMatrix;
    private String image;
    private int width;
    private int height;
    private int[][] colors;
    private int scale;

    public Image(int[][] intensities,  int scale){
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
//
//        System.out.println(imageMatrix.length);
//        System.out.println(intensities.length);
//        System.out.println(imageMatrix[0].length);
//        System.out.println(intensities[0].length);

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

//        for(int[] rgbRow: this.imageMatrix){
//            System.out.print("|");
//            for(int colorPart: rgbRow){
//                System.out.print(" "+colorPart+" ");
//            }
//            System.out.println("|");
//        }
//
//        System.out.println(this.scale);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Teste teste =  new Teste();
        List<Teste> list = new ArrayList<Teste>();
        list.add(teste);
        list.add(teste);
        try {
            Writer writer = new FileWriter("image.json");
            gson.toJson(list, writer);
            writer.flush(); //flush data to file   <---
            writer.close(); //close write          <
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Reader reader= new FileReader("image.json");
            Type listType = new TypeToken<ArrayList<Teste>>(){}.getType();
            list = gson.fromJson(reader, listType);
            String string = reader.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            if(i!=imageMatrix.length-1){
                line.append("\n");
            }
            for(int scale=0;scale< this.scale;scale++){
                image.append(line);
            }
        }
//        System.out.print(image);

        this.image = String.valueOf(image);
    }

    public boolean generateImage(){
        createImage();
        try{
            FileOutputStream fos = new FileOutputStream("photo.ppm");
            fos.write(new String(this.image).getBytes());
            fos.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
